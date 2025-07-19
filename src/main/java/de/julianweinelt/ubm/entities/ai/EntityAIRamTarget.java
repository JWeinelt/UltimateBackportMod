package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityGoat;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;

import java.util.List;

public class EntityAIRamTarget extends EntityAIBase {
    private final EntityGoat goat;
    private EntityLivingBase target;

    public EntityAIRamTarget(EntityGoat goat) {
        this.goat = goat;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!goat.canRam()) return false;

        List<EntityLivingBase> list = goat.world.getEntitiesWithinAABB(EntityLivingBase.class,
            goat.getEntityBoundingBox().grow(4.0D, 2.0D, 4.0D),
            e -> e != goat && !(e instanceof EntityGoat) && e.isEntityAlive());

        if (!list.isEmpty()) {
            target = list.get(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return target != null && target.isEntityAlive() && goat.getDistance(target) > 1.0D;
    }

    @Override
    public void startExecuting() {
        goat.getNavigator().tryMoveToEntityLiving(target, 1.2D);
    }

    @Override
    public void updateTask() {
        if (goat.getDistance(target) < 1.5D) {
            goat.playSound(ModSounds.GOAT_SCREAM_PRE_RAM, 1, 1);
            target.attackEntityFrom(DamageSource.causeMobDamage(goat), 4.0F);
            double knockbackX = target.posX - goat.posX;
            double knockbackZ = target.posZ - goat.posZ;
            target.addVelocity(knockbackX, 0.2D, knockbackZ);
            goat.resetRamCooldown();
            target = null;
        }
    }
}