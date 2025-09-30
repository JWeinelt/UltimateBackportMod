package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTurtle extends EntityWaterMob {

    public EntityTurtle(World worldIn) {
        super(worldIn);
        this.setSize(1.2F, 0.4F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("turtle").getHealth() / 2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(3, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isInWater()) {
            this.motionY += 0.02D;
            if (this.getRNG().nextInt(20) == 0) {
                this.motionX += (this.rand.nextDouble() - 0.5D) * 0.1D;
                this.motionZ += (this.rand.nextDouble() - 0.5D) * 0.1D;
            }
        } else {
            if (this.onGround) {
                this.motionY = 0;
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }
}
