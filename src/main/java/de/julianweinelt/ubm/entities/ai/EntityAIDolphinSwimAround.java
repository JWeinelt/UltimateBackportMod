package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityDolphin;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;

public class EntityAIDolphinSwimAround extends EntityAIBase {

    private final EntityDolphin dolphin;
    private double x, y, z;
    private final double speed;
    private int cooldown;

    public EntityAIDolphinSwimAround(EntityDolphin dolphin, double speed) {
        this.dolphin = dolphin;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!dolphin.isInWater()) return false;

        if (cooldown > 0) {
            cooldown--;
            return false;
        }

        Vec3d pos = this.getRandomPosition();
        if (pos == null) return false;

        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !dolphin.getNavigator().noPath() && dolphin.isInWater();
    }

    @Override
    public void startExecuting() {
        dolphin.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
        cooldown = 40 + dolphin.getRNG().nextInt(60);
    }

    private Vec3d getRandomPosition() {
        double dx = dolphin.posX + (dolphin.getRNG().nextDouble() * 16 - 8);
        double dy = dolphin.posY + (dolphin.getRNG().nextDouble() * 6 - 3);
        double dz = dolphin.posZ + (dolphin.getRNG().nextDouble() * 16 - 8);

        if (dolphin.world.getBlockState(new net.minecraft.util.math.BlockPos(dx, dy, dz)).getMaterial().isLiquid()) {
            return new Vec3d(dx, dy, dz);
        }
        return null;
    }
}
