package de.julianweinelt.ubm.worldgen.nether;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class CustomTeleporter extends Teleporter {

    public CustomTeleporter(WorldServer worldIn) {
        super(worldIn);
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
        entity.setPositionAndUpdate(0, 80, 0);
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        return false;
    }

    @Override
    public boolean makePortal(Entity entity) {
        return false;
    }
}