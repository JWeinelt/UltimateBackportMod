package de.julianweinelt.ubm.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;

public class EntitySalmon extends EntityWaterMob {

    public EntitySalmon(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    @Override
    protected void initEntityAI() {

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isInWater()) {
            if (this.ticksExisted % 40 == 0) {
                this.motionX += (this.rand.nextDouble() - 0.5D) * 0.1D;
                this.motionY += (this.rand.nextDouble() - 0.5D) * 0.05D;
                this.motionZ += (this.rand.nextDouble() - 0.5D) * 0.1D;
            }
        } else {
            if (this.onGround) {
                this.motionY += 0.3D;
                this.motionX += (this.rand.nextDouble() - 0.5D) * 0.4D;
                this.motionZ += (this.rand.nextDouble() - 0.5D) * 0.4D;
            }
        }
    }

    /*
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COD_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }*/

    @Override
    public boolean getCanSpawnHere() {
        return this.isInWater() && super.getCanSpawnHere();
    }
}
