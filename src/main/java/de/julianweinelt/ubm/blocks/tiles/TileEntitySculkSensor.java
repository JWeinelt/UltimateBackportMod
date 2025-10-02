package de.julianweinelt.ubm.blocks.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TileEntitySculkSensor extends TileEntity implements ITickable {

    private int activeTicks = 0;
    private int cooldownTicks = 0;

    @Override
    public void update() {
        if (world == null || world.isRemote) return;

        if (activeTicks > 0) {
            activeTicks--;
            if (activeTicks == 0) {
                world.notifyNeighborsOfStateChange(pos, getBlockType(), false);
            }
            return;
        }

        if (cooldownTicks > 0) {
            cooldownTicks--;
            return;
        }

        AxisAlignedBB box = new AxisAlignedBB(pos).grow(8);
        List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, box);

        for (Entity e : entities) {
            if (e.prevPosX != e.posX || e.prevPosY != e.posY || e.prevPosZ != e.posZ) {
                BlockPos floor = new BlockPos(e.posX, Math.floor(e.posY) - 1, e.posZ);
                if (e.world.getBlockState(floor).getBlock().getRegistryName().getResourcePath().contains("wool")) continue;
                if (e.isSneaking()) continue; //TODO: Trigger advancement
                activateSensor();
                break;
            }
        }
    }

    private void activateSensor() {
        if (activeTicks == 0) {
            activeTicks = 20;
            cooldownTicks = 40;
            world.notifyNeighborsOfStateChange(pos, getBlockType(), true);
        }
    }

    public boolean isActive() {
        return activeTicks > 0;
    }
}
