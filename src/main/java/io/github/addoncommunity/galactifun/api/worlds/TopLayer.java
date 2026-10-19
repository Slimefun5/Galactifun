package io.github.addoncommunity.galactifun.api.worlds;

import javax.annotation.Nonnull;

import org.bukkit.Material;

/**
 * The material a world's surface is covered with and how many blocks deep that cover is.
 */
public final class TopLayer {

    private final Material material;
    private final int depth;

    public TopLayer(@Nonnull Material material, int depth) {
        this.material = material;
        this.depth = depth;
    }

    @Nonnull
    public Material material() {
        return material;
    }

    public int depth() {
        return depth;
    }
}
