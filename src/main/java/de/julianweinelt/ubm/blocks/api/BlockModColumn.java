package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Custom Log Block, behaves like vanilla logs.
 */
public class BlockModColumn extends BlockLog {

    public BlockModColumn(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);

        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumAxis axis;
        switch (meta & 12) {
            case 4:
                axis = EnumAxis.X;
                break;
            case 8:
                axis = EnumAxis.Z;
                break;
            case 12:
                axis = EnumAxis.NONE;
                break;
            case 0:
            default:
                axis = EnumAxis.Y;
        }
        return this.getDefaultState().withProperty(LOG_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        switch (state.getValue(LOG_AXIS)) {
            case X:
                meta |= 4;
                break;
            case Z:
                meta |= 8;
                break;
            case NONE:
                meta |= 12;
                break;
            case Y:
            default:
                break;
        }
        return meta;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
        return true;
    }
}
