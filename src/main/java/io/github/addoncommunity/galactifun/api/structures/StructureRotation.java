package io.github.addoncommunity.galactifun.api.structures;

import javax.annotation.Nonnull;

import org.bukkit.block.BlockFace;

/**
 * Directions that a structure can be rotated in
 *
 * @author Mooy1
 */
public enum StructureRotation {

    DEFAULT, CLOCKWISE, OPPOSITE, COUNTER_CLOCKWISE;

    private static final StructureRotation[] ROTATIONS = values();
    private static final BlockFace[] FACES = {
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST,
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH
    };

    @Nonnull
    public StructureRotation rotationTo(@Nonnull StructureRotation rotation) {
        return ROTATIONS[Math.abs(rotation.ordinal() - this.ordinal())];
    }

    @Nonnull
    public BlockFace rotateFace(BlockFace face) {
        switch (face) {
            case NORTH: return FACES[this.ordinal()];
            case EAST: return FACES[this.ordinal() + 1];
            case SOUTH: return FACES[this.ordinal() + 2];
            case WEST: return FACES[this.ordinal() + 3];
            default: return face;
        }
    }

    @Nonnull
    public static StructureRotation fromFace(BlockFace face) {
        switch (face) {
            case NORTH: return DEFAULT;
            case EAST: return CLOCKWISE;
            case SOUTH: return OPPOSITE;
            case WEST: return COUNTER_CLOCKWISE;
            default: throw new IllegalArgumentException("BlockFace " + face + " cant be converted to StructureRotation!");
        }
    }

}
