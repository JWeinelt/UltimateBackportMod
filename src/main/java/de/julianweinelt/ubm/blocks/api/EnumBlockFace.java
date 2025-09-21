package de.julianweinelt.ubm.blocks.api;

import net.minecraft.util.IStringSerializable;

public enum EnumBlockFace implements IStringSerializable {
    FLOOR, CEILING, WALL;

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
}
