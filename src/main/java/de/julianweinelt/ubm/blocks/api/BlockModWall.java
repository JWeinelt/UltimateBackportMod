package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;

public class BlockModWall extends BlockWall {
    public BlockModWall(Block modelBlock, String name) {
        super(modelBlock);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
