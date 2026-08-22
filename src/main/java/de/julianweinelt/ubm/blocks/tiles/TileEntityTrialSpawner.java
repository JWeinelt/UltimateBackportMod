package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.blocks.BlockTrialSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityTrialSpawner extends TileEntity implements ITickable {

    @Override
    public void update() {
        if (world.isRemote) return;

        AxisAlignedBB box = new AxisAlignedBB(pos).grow(14);

        boolean playerNearby = !world.getEntitiesWithinAABB(EntityPlayer.class, box).isEmpty();

        IBlockState state = world.getBlockState(pos);

        if (playerNearby) {
            if (state.getValue(BlockTrialSpawner.STATE).equals(BlockTrialSpawner.EnumTrialSpawnerState.INACTIVE)) {
                world.setBlockState(pos, state.withProperty(BlockTrialSpawner.STATE, BlockTrialSpawner.EnumTrialSpawnerState.ACTIVE));
            }
        }
    }
}
