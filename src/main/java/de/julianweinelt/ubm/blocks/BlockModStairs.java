package de.julianweinelt.ubm.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockModStairs extends BlockStairs {
    public BlockModStairs(IBlockState state, String name) {
        super(state);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
}
