package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityDolphin;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.material.Material;

public class EntityAIDolphinFindWater extends EntityAIBase {

    private final EntityDolphin dolphin;
    private BlockPos target;

    public EntityAIDolphinFindWater(EntityDolphin dolphin) {
        this.dolphin = dolphin;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (dolphin.isInWater()) return false;

        BlockPos pos = new BlockPos(dolphin);
        for (int dx = -8; dx <= 8; dx++) {
            for (int dz = -8; dz <= 8; dz++) {
                BlockPos check = pos.add(dx, -1, dz);
                if (dolphin.world.getBlockState(check).getMaterial() == Material.WATER) {
                    target = check;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void startExecuting() {
        if (target != null) {
            dolphin.getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), 1.0D);
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !dolphin.isInWater() && target != null && dolphin.getDistanceSqToCenter(target) > 2.0D;
    }
}
