package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;

public class BlockModPressurePlate extends BlockPressurePlate {
    public BlockModPressurePlate(BlockPressurePlate.Sensitivity sensitivity, Material material, String name) {
        super(material, sensitivity);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
