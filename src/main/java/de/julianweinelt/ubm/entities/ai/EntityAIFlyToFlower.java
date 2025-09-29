package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.BlockBeeNest;
import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBeeNest;
import de.julianweinelt.ubm.entities.EntityBee;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFlyToFlower extends EntityAIBase {

    private final EntityBee bee;
    private BlockPos targetFlower;
    private BlockPos nest;
    private final double speed;

    public EntityAIFlyToFlower(EntityBee bee, double speed) {
        this.bee = bee;
        this.speed = speed;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (bee.getRNG().nextInt(20) != 0) {
            return false;
        }
    
        if (bee.getBeeState() == EntityBee.BeeState.SEARCHING_FLOWER) {
            targetFlower = findNearbyFlower();
            return targetFlower != null;
        }
    
        if (bee.getBeeState() == EntityBee.BeeState.RETURN_TO_NEST) {
            if (nest == null) { //TODO: Or if the Block type at this position isn't BEE_HIVE or BEE_NEST
                nest = findNest();
                bee.setNestPos(nest);
            }
            return nest != null;
        }
    
        return false;
    }

    @Override
    public void startExecuting() {
        if (targetFlower != null && bee.getBeeState().equals(EntityBee.BeeState.SEARCHING_FLOWER)) {
            bee.getNavigator().tryMoveToXYZ(
                targetFlower.getX() + 0.5,
                targetFlower.getY() + 1.0,
                targetFlower.getZ() + 0.5,
                speed
            );
        }

        if (nest != null && bee.getBeeState().equals(EntityBee.BeeState.RETURN_TO_NEST)) {
            bee.getNavigator().tryMoveToXYZ(
                    nest.getX() + 0.5,
                    nest.getY() + 1.0,
                    nest.getZ() + 0.5,
                    speed
            );
        }
    }

    @Override
    public void resetTask() {
        targetFlower = null;
    }

    @Override
    public void updateTask() {
        if (targetFlower != null && bee.getBeeState().equals(EntityBee.BeeState.SEARCHING_FLOWER)) {
            Vec3d dir = new Vec3d(
                targetFlower.getX() + 0.5 - bee.posX,
                    targetFlower.getY() + 0.5 - bee.posY,
                    targetFlower.getZ() + 0.5 - bee.posZ
        ).normalize();

            double speed = 0.1D;
            bee.motionX = dir.x * speed;
            bee.motionY = dir.y * speed;
            bee.motionZ = dir.z * speed;
        }

        if (nest != null && bee.getBeeState().equals(EntityBee.BeeState.RETURN_TO_NEST)) {
            Vec3d dir = new Vec3d(
                    nest.getX() + 0.5 - bee.posX,
                    nest.getY() + 0.5 - bee.posY,
                    nest.getZ() + 0.5 - bee.posZ
            ).normalize();

            double speed = 0.1D;
            bee.motionX = dir.x * speed;
            bee.motionY = dir.y * speed;
            bee.motionZ = dir.z * speed;
        }

        if (foundFlower()) {
            bee.setState(EntityBee.BeeState.RETURN_TO_NEST);
        }
        tryEnterNearbyHive();
    }

    private BlockPos findNearbyFlower() {
        BlockPos beePos = bee.getPosition();
        int radius = 32;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -8; y <= 8; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = beePos.add(x, y, z);
                    IBlockState state = bee.world.getBlockState(pos);

                    if (isFlower(state)) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }

    private boolean foundFlower() {
        BlockPos beePos = bee.getPosition();
        int radius = 2;

        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = beePos.add(x, y, z);
                    IBlockState state = bee.world.getBlockState(pos);

                    if (isFlower(state)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void tryEnterNearbyHive() {
        BlockPos beePos = bee.getPosition();

        for (BlockPos pos : BlockPos.getAllInBox(beePos.add(-1, -1, -1), beePos.add(1, 1, 1))) {
            if (pos.equals(beePos)) continue;

            IBlockState state = bee.world.getBlockState(pos);

            if (state.getBlock() == ModBlocks.BEE_HIVE || state.getBlock() == ModBlocks.BEE_NEST) {
                int currentBees = state.getValue(BlockBeeNest.BEES);

                if (currentBees < 3) {
                    bee.world.setBlockState(
                            pos,
                            state.withProperty(BlockBeeNest.BEES, currentBees + 1),
                            2
                    );

                    bee.setDead();

                    bee.world.playSound(
                            null,
                            pos,
                            ModSounds.BEE_HIVE_ENTER,
                            SoundCategory.BLOCKS,
                            1.0F,
                            1.0F
                    );
                }
                return;
            }

        }
    }


    private BlockPos findNest() {
        BlockPos beePos = bee.getPosition();
        if (bee.getNestPos() != null) {
            return bee.getNestPos();
        }
        int radius = 32;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -8; y <= 8; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = beePos.add(x, y, z);
                    IBlockState state = bee.world.getBlockState(pos);

                    if (state.getBlock() == ModBlocks.BEE_NEST) return pos;
                    if (state.getBlock() == ModBlocks.BEE_HIVE) return pos;

                    ResourceLocation name = state.getBlock().getRegistryName();
                    if (name != null && name.getResourcePath().contains("bee_")) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }

    private boolean isFlower(IBlockState state) {
        return state.getBlock() == Blocks.RED_FLOWER ||
               state.getBlock() == Blocks.YELLOW_FLOWER;
    }

}
