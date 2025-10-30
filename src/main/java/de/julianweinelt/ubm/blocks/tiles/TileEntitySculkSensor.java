package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.blocks.BlockSculkSensor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class TileEntitySculkSensor extends TileEntity {

    private int cooldown = 0;
    private boolean triggered = false;

    public void triggerOnce() {
        if (world == null || world.isRemote) return;
        if (cooldown > 0) return;

        cooldown = 40;
        triggered = true;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockSculkSensor) {
            world.setBlockState(pos, state.withProperty(BlockSculkSensor.TRIGGERED, true), 3);
            world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
        }

        world.scheduleUpdate(pos, getBlockType(), 20);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        triggered = false;
        worldIn.setBlockState(pos, state.withProperty(BlockSculkSensor.TRIGGERED, false), 3);
        worldIn.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
    }
}
