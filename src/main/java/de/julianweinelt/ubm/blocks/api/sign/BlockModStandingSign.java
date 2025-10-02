package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockModStandingSign extends BlockSign {
    public BlockModStandingSign(String name) {
        this.setRegistryName(name + "_standing");
        this.setUnlocalizedName(name + "_standing");
    }

    @Override
    @Nonnull
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntityModSign();
    }
}


