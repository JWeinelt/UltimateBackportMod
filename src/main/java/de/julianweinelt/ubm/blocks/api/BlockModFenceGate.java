package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;

public class BlockModFenceGate extends BlockFenceGate {
    public BlockModFenceGate(String name) {
        super(BlockPlanks.EnumType.OAK);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }
}