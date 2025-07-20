package de.julianweinelt.ubm.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class BlockModFence extends BlockFence {
    public BlockModFence(Material material, String name) {
        super(material, material.getMaterialMapColor());
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}