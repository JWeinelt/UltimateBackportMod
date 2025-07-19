package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityBee;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIHurtByTargetFlying extends EntityAIBase {
    private final EntityBee entity;

    public EntityAIHurtByTargetFlying(EntityBee entity) {
        this.entity = entity;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        EntityLivingBase attacker = entity.getRevengeTarget();
        return attacker != null && attacker.isEntityAlive();
    }

    @Override
    public void startExecuting() {
        entity.setAttackTarget(entity.getRevengeTarget());
        entity.setAggressive(true);
    }

    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase target = entity.getAttackTarget();
        return target != null && target.isEntityAlive();
    }
}