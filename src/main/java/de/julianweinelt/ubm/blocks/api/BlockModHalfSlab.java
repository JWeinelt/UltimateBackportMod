package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;

public class BlockModHalfSlab extends BlockModSlab {
    private final String uName;

    public BlockModHalfSlab(String name, Material material) {
        super(name, material, false);
        uName = name;
    }

    @Override
    protected BlockModSlab getItemDroppedSlab() {
        return this;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(int meta) {
        return uName;
    }
}