package de.julianweinelt.ubm.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.World;

public class EntityBambooRaft extends EntityBoat {
    public EntityBambooRaft(World worldIn) {
        super(worldIn);
    }

    public EntityBambooRaft(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }


    @Override
    public Type getBoatType() {
        return Type.OAK;
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            double yOffset = -0.1D;

            double cos = Math.cos(Math.toRadians(this.rotationYaw + 90));
            double sin = Math.sin(Math.toRadians(this.rotationYaw + 90));

            passenger.setPosition(
                    this.posX + cos * 0.0D,
                    this.posY + this.getMountedYOffset() + passenger.getYOffset() + yOffset,
                    this.posZ + sin * 0.0D
            );
            //passenger.rotationYaw = this.rotationYaw;
        }
    }

    @Override
    public double getMountedYOffset() {
        return 0.15D;
    }
}
