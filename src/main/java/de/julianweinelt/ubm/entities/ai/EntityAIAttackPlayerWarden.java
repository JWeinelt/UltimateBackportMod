package de.julianweinelt.ubm.entities.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

import java.util.List;

public class EntityAIAttackPlayerWarden extends EntityAIBase {

    private final EntityCreature warden;
    private EntityLivingBase target;
    private final double speedTowardsTarget;
    private final double detectionRadius;

    private int attackCooldown;

    public EntityAIAttackPlayerWarden(EntityCreature warden, double speed, double detectionRadius) {
        this.warden = warden;
        this.speedTowardsTarget = speed;
        this.detectionRadius = detectionRadius;
        this.setMutexBits(3); // Movement + Look
    }

    @Override
    public boolean shouldExecute() {
        List<EntityPlayer> players = warden.world.getEntitiesWithinAABB(
                EntityPlayer.class,
                warden.getEntityBoundingBox().grow(detectionRadius)
        );

        if (!players.isEmpty()) {
            this.target = players.get(0);
            return true;
        }

        return false;
    }

    @Override
    public void resetTask() {
        this.target = null;
        this.warden.getNavigator().clearPath();
    }

    @Override
    public void updateTask() {
        if (target == null) return;

        warden.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);

        double distanceSq = warden.getDistanceSq(target);

        if (distanceSq > 2.0D) {
            warden.getNavigator().tryMoveToEntityLiving(target, speedTowardsTarget);
        } else {
            if (attackCooldown <= 0) {
                warden.swingArm(EnumHand.MAIN_HAND);
                warden.attackEntityAsMob(target);
                attackCooldown = 20;
            }
        }

        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }
}
