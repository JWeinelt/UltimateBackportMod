package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockModWallSign extends BlockSign {
    public BlockModWallSign(String name) {
        this.setRegistryName(name + "_wall");
        this.setUnlocalizedName(name + "_wall");
    }

    @Override
    @Nonnull
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntityModSign();
    }
}