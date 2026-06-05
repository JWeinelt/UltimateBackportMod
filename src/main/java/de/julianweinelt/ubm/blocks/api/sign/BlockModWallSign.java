package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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



    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityModSign) {
            player.openEditSign((TileEntitySign) te);
            return true;
        }

        return false;
    }
}