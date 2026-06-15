package io.github.addoncommunity.galactifun.util;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Resolves {@link XMaterial} constants to a {@link Material} that exists on the
 * running server. Keeps Galactifun loadable on legacy versions where modern
 * constants (e.g. {@code NETHERITE_INGOT}, {@code *_CONCRETE_POWDER}) are absent.
 */
public final class MaterialCompat {

    private MaterialCompat() {
        throw new UnsupportedOperationException("Utility Class");
    }

    @Nonnull
    public static Material safe(@Nonnull XMaterial material) {
        Material resolved = material.parseMaterial();
        return resolved != null ? resolved : Material.STONE;
    }

    /** Resolves an XMaterial to an ItemStack, preserving the legacy data value safe(XMaterial) drops on 1.8-1.12. */
    @javax.annotation.Nonnull
    public static org.bukkit.inventory.ItemStack stack(@javax.annotation.Nonnull XMaterial material) {
        org.bukkit.inventory.ItemStack item = material.parseItem();
        return item != null ? item : new org.bukkit.inventory.ItemStack(safe(material));
    }
}
