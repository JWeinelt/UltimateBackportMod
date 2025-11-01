package de.julianweinelt.ubm.worldgen.dim;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;

public class CustomNetherTeleporter extends Teleporter {
    private final WorldServer world;


    public CustomNetherTeleporter(WorldServer worldIn) {
        super(worldIn);
        this.world = worldIn;
    }

    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        int x = 0;
        int y = 100;
        int z = 0;

        BlockPos pos = new BlockPos(x, y, z);
        while (!world.isAirBlock(pos) && pos.getY() < world.getHeight()) {
            pos = pos.up();
        }

        entity.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        entity.motionX = entity.motionY = entity.motionZ = 0.0;
    }

    @Override
    public boolean placeInExistingPortal(@Nonnull Entity entity, float rotationYaw) {
        return false;
    }
}
