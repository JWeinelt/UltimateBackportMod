package de.julianweinelt.ubm.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockStrippedStem extends BlockLog {

    public BlockStrippedStem(String name, CreativeTabs tab) {
        super();
        this.setUnlocalizedName("stripped_" + name + "_stem");
        this.setRegistryName("stripped_" + name + "_stem");
        this.setCreativeTab(tab);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta) {
            case 0:
                state = state.withProperty(LOG_AXIS, EnumAxis.Y);
                break;
            case 1:
                state = state.withProperty(LOG_AXIS, EnumAxis.X);
                break;
            case 2:
                state = state.withProperty(LOG_AXIS, EnumAxis.Z);
                break;
            default:
                state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(LOG_AXIS)) {
            case X: return 1;
            case Z: return 2;
            case NONE: return 3;
            case Y:
            default: return 0;
        }
    }
}
