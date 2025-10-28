package de.julianweinelt.ubm.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityCamel extends EntityAnimal {

    public EntityCamel(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable ageAble) {
        EntityCamel child = new EntityCamel(this.world);
        child.setGrowingAge(-24000);
        return child;
    }

    @Override
    protected boolean canBeRidden(@Nonnull Entity entityIn) {
        return true;
    }

    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 2 && passenger instanceof EntityLivingBase;
    }

    @Override
    @Nonnull
    public List<Entity> getPassengers() {
        return this.getPassengers();
    }

    @Override
    public boolean isPassenger(Entity entityIn) {
        return this.getPassengers().contains(entityIn);
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canPassengerSteer() {
        return true;
    }

    @Override
    public void updatePassenger(@Nonnull Entity passenger) {
        if (this.isPassenger(passenger)) {
            double yOffset = 1.2D;
            double xOffset = 0.0D;

            if (this.getPassengers().size() > 1) {
                if (this.getPassengers().get(1) == passenger) {
                    xOffset = 0.5D;
                }
            }

            double sin = Math.sin(this.rotationYaw * 0.017453292F);
            double cos = Math.cos(this.rotationYaw * 0.017453292F);

            passenger.setPosition(
                    this.posX + xOffset * cos - 0.0 * sin,
                    this.posY + yOffset,
                    this.posZ + 0.0 * cos + xOffset * sin
            );

            passenger.rotationYaw = this.rotationYaw;
        }
    }

}
