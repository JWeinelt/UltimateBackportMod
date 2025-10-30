package de.julianweinelt.ubm.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityCamel extends EntityAnimal {

    public EntityCamel(World worldIn) {
        super(worldIn);
        setSize(1.7F, 2.375F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(0, new EntityAIWander(this, 0.5));
        this.tasks.addTask(0, new EntityAILookIdle(this));
    }

    @Nullable
    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable ageAble) {
        EntityCamel child = new EntityCamel(this.world);
        child.setGrowingAge(-24000);
        return child;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (stack.getItem().getRegistryName() == null) return false;
        return stack.getItem().getRegistryName().equals(Blocks.HAY_BLOCK.getRegistryName());
    }

    @Override
    protected boolean canBeRidden(@Nonnull Entity entityIn) {
        return true;
    }

    @Override
    protected boolean canFitPassenger(@Nonnull Entity passenger) {
        return this.getPassengers().size() < 2 && passenger instanceof EntityLivingBase;
    }

    @Override
    public boolean isPassenger(@Nonnull Entity entityIn) {
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
