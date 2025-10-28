package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityPhantom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIPhantomOrbit extends EntityAIBase {
    private final EntityPhantom phantom;
    private EntityLivingBase target;

    private double angle = 0;
    private final double radius = 20;
    private final double height = 10;
    private final double speed = 0.05;

    public EntityAIPhantomOrbit(EntityPhantom phantom) {
        this.phantom = phantom;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        this.target = this.phantom.getAttackTarget();
        return this.target != null && this.target.isEntityAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return shouldExecute();
    }

    @Override
    public void updateTask() {
        if (target == null) return;

        angle += speed;
        if (angle > 2 * Math.PI) angle -= 2 * Math.PI;

        double x = target.posX + radius * Math.cos(angle);
        double y = target.posY + height;
        double z = target.posZ + radius * Math.sin(angle);

        double dx = x - phantom.posX;
        double dy = y - phantom.posY;
        double dz = z - phantom.posZ;
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (dist > 0.1) {
            phantom.motionX += dx / dist * 0.1;
            phantom.motionY += dy / dist * 0.1;
            phantom.motionZ += dz / dist * 0.1;
        }

        //phantom.faceEntity(target, 30.0F, 30.0F);

        if (phantom.getEntityBoundingBox().grow(0.3D).intersects(target.getEntityBoundingBox())) {
            phantom.attackEntityAsMob(target);
        }
    }
}
