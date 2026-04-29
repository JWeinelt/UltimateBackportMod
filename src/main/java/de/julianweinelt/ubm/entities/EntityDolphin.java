package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModYamlConfig;
import de.julianweinelt.ubm.entities.ai.EntityAIDolphinBreath;
import de.julianweinelt.ubm.entities.ai.EntityAIDolphinFindWater;
import de.julianweinelt.ubm.entities.ai.EntityAIDolphinJump;
import de.julianweinelt.ubm.entities.ai.EntityAIDolphinSwimAround;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDolphin extends EntityWaterMob {

    public EntityDolphin(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 0.6F);
        this.experienceValue = 5;

        this.initEntityAI();
    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }

    @Override
    protected void initEntityAI() {
        //this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIDolphinSwimAround(this, 1));
        this.tasks.addTask(1, new EntityAIDolphinBreath(this));
        this.tasks.addTask(2, new EntityAIDolphinFindWater(this));
        this.tasks.addTask(3, new EntityAIDolphinJump(this));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, net.minecraft.entity.player.EntityPlayer.class, 8.0F));
        //this.tasks.addTask(4, new EntityAIAvoidEntity<EntityGuardian>(this, EntityGuardian.class, 10, 10, 10));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(
                ModYamlConfig.entityHealth("dolphin") / 2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.DOLPHIN_IDLE_WATER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.DOLPHIN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DOLPHIN_DEATH;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}