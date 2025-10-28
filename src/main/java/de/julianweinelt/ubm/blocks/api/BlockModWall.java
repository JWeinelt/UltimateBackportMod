package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

/**
 * Custom Wall Block, based on a parent block (like cobblestone).
 */
public class BlockModWall extends BlockWall {

    public BlockModWall(Block parent, String name) {
        super(parent);

        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.STONE);

        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(NORTH, false)
                .withProperty(EAST, false)
                .withProperty(SOUTH, false)
                .withProperty(WEST, false)
                .withProperty(UP, false)
                .withProperty(VARIANT, EnumType.NORMAL));
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(@Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return false;
    }
}
