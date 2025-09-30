package de.julianweinelt.ubm.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.Random;

public class PiglinAIHuntHoglin extends EntityAIBase {

    private final EntityMob piglin;
    private EntityMob targetHoglin;
    private final Random random = new Random();

    public PiglinAIHuntHoglin(EntityMob piglin) {
        this.piglin = piglin;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (piglin.ticksExisted % 20 != 0) return false; // nur einmal pro Sekunde prüfen
        if (random.nextFloat() > 0.1F) return false; // 10% Chance

        // Suche Hoglins in einem Radius von 16 Blöcken
        AxisAlignedBB box = piglin.getEntityBoundingBox().grow(16.0D, 8.0D, 16.0D);
        List<EntityMob> hoglins = piglin.world.getEntitiesWithinAABB(EntityMob.class, box,
                e -> e.getClass().getSimpleName().equals("EntityHoglin"));

        if (hoglins.isEmpty()) return false;

        targetHoglin = hoglins.get(random.nextInt(hoglins.size()));
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return targetHoglin != null && targetHoglin.isEntityAlive();
    }

    @Override
    public void startExecuting() {
        piglin.setAttackTarget(targetHoglin);
        AxisAlignedBB box = piglin.getEntityBoundingBox().grow(16.0D, 8.0D, 16.0D);
        List<EntityMob> allies = piglin.world.getEntitiesWithinAABB(EntityMob.class, box,
                e -> e.getClass().getSimpleName().equals("EntityPiglin") && e != piglin);
        for (EntityMob ally : allies) {
            ally.setAttackTarget(targetHoglin);
        }
    }

    @Override
    public void updateTask() {
        if (targetHoglin == null) return;
        piglin.getNavigator().tryMoveToEntityLiving(targetHoglin, 1.0D);
        if (piglin.getDistance(targetHoglin) < 2.0D) {
            piglin.attackEntityAsMob(targetHoglin);
        }
    }

    @Override
    public void resetTask() {
        targetHoglin = null;
    }
}
