package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BlockCaveVine extends Block implements IGrowable {

    private final int growChance;

    public BlockCaveVine(int growChance) {
        super(Material.PLANTS);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.growChance = growChance;
    }

    @Override
    public void updateTick(World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
        if (!world.isRemote) {
            if (rand.nextInt(growChance) == 0) {
                tryGrow(world, pos, state, rand);
            }
        }
    }

    protected void tryGrow(World world, BlockPos pos, IBlockState state, Random rand) {
        BlockPos below = pos.down();
        if (world.isAirBlock(below)) {
            world.setBlockState(below, this.getDefaultState());
        }
    }


    @Override
    public boolean canGrow(World worldIn, BlockPos pos, @Nonnull IBlockState state, boolean isClient) {
        return worldIn.isAirBlock(pos.down());
    }

    @Override
    public boolean canUseBonemeal(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return true;
    }

    @Override
    public void grow(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        tryGrow(worldIn, pos, state, rand);
    }
}
