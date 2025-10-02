package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;

public class BlockModDoubleSlab extends BlockModSlab {
    private final BlockModSlab baseSlab;
    private final String uName;

    public BlockModDoubleSlab(String name, Material material, Block baseSlab) {
        super(name + "_d", material, false);
        this.baseSlab = (BlockModSlab) baseSlab;
        uName = name + "_d";
    }

    @Override
    protected BlockModSlab getItemDroppedSlab() {
        return baseSlab;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(int meta) {
        return uName;
    }
}
