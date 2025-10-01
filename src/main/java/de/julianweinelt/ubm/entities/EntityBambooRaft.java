package de.julianweinelt.ubm.entities;

import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBambooRaft extends EntityBoat {
    public EntityBambooRaft(World worldIn) {
        super(worldIn);
    }

    public EntityBambooRaft(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @Override
    @Nonnull
    public Type getBoatType() {
        return Type.OAK;
    }
}
