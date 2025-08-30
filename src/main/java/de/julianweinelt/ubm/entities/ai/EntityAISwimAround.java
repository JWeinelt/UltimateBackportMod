package de.julianweinelt.ubm.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAISwimAround extends EntityAIBase {

    private final EntityWaterMob fish;
    private double targetX;
    private double targetY;
    private double targetZ;
    private final double speed;

    public EntityAISwimAround(EntityWaterMob fish, double speed) {
        this.fish = fish;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!fish.isInWater()) {
            return false;
        }

        if (fish.getRNG().nextInt(2) != 0) {
            return false;
        }

        Vec3d vec = this.getRandomTarget();
        if (vec == null) {
            return false;
        } else {
            this.targetX = vec.x;
            this.targetY = vec.y;
            this.targetZ = vec.z;
            return true;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !fish.getNavigator().noPath() && fish.isInWater();
    }

    @Override
    public void startExecuting() {
        fish.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    private Vec3d getRandomTarget() {
        double x = fish.posX + (fish.getRNG().nextDouble() * 10 - 5);
        double y = fish.posY + (fish.getRNG().nextDouble() * 8 - 5) * (fish.getRNG().nextBoolean() ? -1 : 1);
        double z = fish.posZ + (fish.getRNG().nextDouble() * 10 - 5);

        BlockPos pos = new BlockPos(x, y, z);

        if (fish.world.getBlockState(pos).getBlock() == Blocks.WATER) {
            return new Vec3d(x, y, z);
        }
        return null;
    }
}
