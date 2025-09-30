package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

public class BlockModDoor extends BlockDoor {
    protected BlockModDoor(Material materialIn, String name) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
}
