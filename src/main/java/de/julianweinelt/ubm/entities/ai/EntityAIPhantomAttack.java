package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityPhantom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIPhantomAttack extends EntityAIBase {
    private final EntityPhantom phantom;
    private EntityLivingBase target;

    public EntityAIPhantomAttack(EntityPhantom phantom) {
        this.phantom = phantom;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        this.target = this.phantom.getAttackTarget();
        return this.target != null && this.target.isEntityAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    @Override
    public void updateTask() {
        if (this.target != null) {
            double tx = target.posX;
            double ty = target.posY + target.getEyeHeight() + 5;
            double tz = target.posZ;

            double dx = tx - phantom.posX;
            double dy = ty - phantom.posY;
            double dz = tz - phantom.posZ;

            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (dist > 1.0D) {
                phantom.motionX += dx / dist * 0.1;
                phantom.motionY += dy / dist * 0.1;
                phantom.motionZ += dz / dist * 0.1;
            }

            phantom.faceEntity(target, 30.0F, 30.0F);

            if (phantom.getEntityBoundingBox().grow(0.3D).intersects(target.getEntityBoundingBox())) {
                phantom.attackEntityAsMob(target);
            }
        }
    }
}
