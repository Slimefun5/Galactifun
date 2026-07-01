package io.github.addoncommunity.galactifun.compat;

import java.util.Optional;

import io.github.thebusybiscuit.slimefun5.libraries.dough.data.persistent.VersionedPdc;

/**
 * Load-safe PDC facade. dough's {@code PersistentDataAPI} class cannot load on MC 1.8 (its 1.14+
 * {@code PersistentDataType} references break class verification). {@code VersionedPdc} is fully
 * reflective (Object/String signatures) and loads on every version, persisting via PDC on 1.14+ and
 * item-NBT below. The key is accepted as {@code Object} (a String or a NamespacedKey shim) and
 * rendered to a "namespace:key" String.
 */
public final class Pdc {

    private Pdc() {}

    private static String key(Object k) { return String.valueOf(k); }

    public static void setString(Object h, Object k, String v) { VersionedPdc.setString(h, key(k), v); }
    public static String getString(Object h, Object k) { return VersionedPdc.getString(h, key(k)); }
    public static String getString(Object h, Object k, String def) { String v = VersionedPdc.getString(h, key(k)); return v != null ? v : def; }
    public static Optional<String> getOptionalString(Object h, Object k) { return Optional.ofNullable(VersionedPdc.getString(h, key(k))); }
    public static boolean hasString(Object h, Object k) { return VersionedPdc.has(h, key(k), "STRING"); }
    public static void setInt(Object h, Object k, int v) { VersionedPdc.setInt(h, key(k), v); }
    public static int getInt(Object h, Object k) { return VersionedPdc.getInt(h, key(k), -1); }
    public static int getInt(Object h, Object k, int def) { return VersionedPdc.getInt(h, key(k), def); }
    public static boolean hasInt(Object h, Object k) { return VersionedPdc.has(h, key(k), "INTEGER"); }
    public static void setLong(Object h, Object k, long v) { VersionedPdc.setLong(h, key(k), v); }
    public static long getLong(Object h, Object k) { return VersionedPdc.getLong(h, key(k), -1L); }
    public static long getLong(Object h, Object k, long def) { return VersionedPdc.getLong(h, key(k), def); }
    public static void setDouble(Object h, Object k, double v) { VersionedPdc.setDouble(h, key(k), v); }
    public static double getDouble(Object h, Object k) { return VersionedPdc.getDouble(h, key(k), -1D); }
    public static double getDouble(Object h, Object k, double def) { return VersionedPdc.getDouble(h, key(k), def); }
    public static void setBoolean(Object h, Object k, boolean v) { VersionedPdc.setByte(h, key(k), (byte) (v ? 1 : 0)); }
    public static boolean getBoolean(Object h, Object k) { return VersionedPdc.getByte(h, key(k), (byte) 0) != 0; }
    public static void remove(Object h, Object k) { VersionedPdc.remove(h, key(k)); }
}
