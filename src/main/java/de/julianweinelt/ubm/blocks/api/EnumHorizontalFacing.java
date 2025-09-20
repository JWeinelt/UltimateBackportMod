package de.julianweinelt.ubm.blocks.api;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum EnumHorizontalFacing implements IStringSerializable {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    public static EnumHorizontalFacing fromFacing(EnumFacing facing) {
        switch (facing) {
            case SOUTH: return SOUTH;
            case WEST:  return WEST;
            case EAST:  return EAST;
            default:    return NORTH;
        }
    }

    public EnumFacing toEnumFacing() {
        switch (this) {
            case NORTH: return EnumFacing.NORTH;
            case SOUTH: return EnumFacing.SOUTH;
            case WEST:  return EnumFacing.WEST;
            case EAST:  return EnumFacing.EAST;
            default:    return EnumFacing.NORTH;
        }
    }

    public static EnumHorizontalFacing fromEnumFacing(EnumFacing facing) {
        switch (facing) {
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case WEST:  return WEST;
            case EAST:  return EAST;
            default:    return NORTH;
        }
    }


}
