package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.pathfinding.PathNavigate;

import java.util.Random;

public class EntityAIHoglinFleeBlock extends EntityAIBase {

    private final EntityAnimal hoglin;
    private final double speed;
    private final int checkRadius;
    private final Random random = new Random();

    private final Block fleeBlock = ModBlocks.WARPED_NYLIUM;
    private Vec3d fleeTarget;

    public EntityAIHoglinFleeBlock(EntityAnimal hoglin, double speed, int checkRadius) {
        this.hoglin = hoglin;
        this.speed = speed;
        this.checkRadius = checkRadius;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        BlockPos hogPos = new BlockPos(hoglin.posX, hoglin.posY, hoglin.posZ);

        for (int dx = -checkRadius; dx <= checkRadius; dx++) {
            for (int dz = -checkRadius; dz <= checkRadius; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    BlockPos checkPos = hogPos.add(dx, dy, dz);
                    Block block = hoglin.world.getBlockState(checkPos).getBlock();
                    if (block == fleeBlock || block == Blocks.PORTAL) {
                        fleeTarget = new Vec3d(
                                hoglin.posX + (hoglin.posX - checkPos.getX()) * 2.0,
                                hoglin.posY,
                                hoglin.posZ + (hoglin.posZ - checkPos.getZ()) * 2.0
                        );
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return fleeTarget != null && hoglin.getDistance(fleeTarget.x, fleeTarget.y, fleeTarget.z) > 1.0;
    }

    @Override
    public void startExecuting() {
        PathNavigate navigator = hoglin.getNavigator();
        if (fleeTarget != null) {
            navigator.tryMoveToXYZ(fleeTarget.x, fleeTarget.y, fleeTarget.z, speed);
        }
    }

    @Override
    public void updateTask() {
        PathNavigate navigator = hoglin.getNavigator();
        if (fleeTarget != null && !navigator.noPath()) {

        }
    }

    @Override
    public void resetTask() {
        fleeTarget = null;
    }
}
