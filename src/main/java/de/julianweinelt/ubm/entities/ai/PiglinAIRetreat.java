package de.julianweinelt.ubm.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class PiglinAIRetreat extends EntityAIBase {
    private final EntityMob piglin;
    private final int retreatThreshold = 3;

    public PiglinAIRetreat(EntityMob piglin) {
        this.piglin = piglin;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        AxisAlignedBB box = piglin.getEntityBoundingBox().grow(16.0D, 8.0D, 16.0D);
        int hoglins = piglin.world.getEntitiesWithinAABB(EntityMob.class, box,
                e -> e.getClass().getSimpleName().equals("EntityHoglin")).size();
        int piglins = piglin.world.getEntitiesWithinAABB(EntityMob.class, box,
                e -> e.getClass().getSimpleName().equals("EntityPiglin")).size();
        return hoglins > piglins && piglins < retreatThreshold;
    }

    @Override
    public void updateTask() {
        AxisAlignedBB box = piglin.getEntityBoundingBox().grow(16.0D, 8.0D, 16.0D);
        List<EntityMob> hoglins = piglin.world.getEntitiesWithinAABB(EntityMob.class, box,
                e -> e.getClass().getSimpleName().equals("EntityHoglin"));
        if (!hoglins.isEmpty()) {
            EntityMob closestHoglin = hoglins.get(0);
            double dx = piglin.posX - closestHoglin.posX;
            double dz = piglin.posZ - closestHoglin.posZ;
            piglin.motionX = dx * 0.1;
            piglin.motionZ = dz * 0.1;
            //TODO: Add retreat sound
        }
    }
}
