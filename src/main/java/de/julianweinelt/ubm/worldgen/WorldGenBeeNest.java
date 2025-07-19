package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.entities.EntityBee;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenBeeNest implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() != 0) return;

        int x = chunkX * 16;
        int z = chunkZ * 16;

        if (random.nextInt(10) != 0) return;

        int posX = x + random.nextInt(16);
        int posZ = z + random.nextInt(16);
        int posY = world.getHeight(posX, posZ);

        BlockPos pos = new BlockPos(posX, posY, posZ);

        BlockPos treePos = findNearbyTree(pos, world);

        if (treePos != null) {
            BlockPos nestPos = treePos.east();
            world.setBlockState(nestPos, ModBlocks.BEE_NEST.getDefaultState());

            int bees = 2 + random.nextInt(2);
            for (int i = 0; i < bees; i++) {
                EntityBee bee = new EntityBee(world);
                bee.setPosition(nestPos.getX() + 0.5, nestPos.getY() + 1, nestPos.getZ() + 0.5);
                world.spawnEntity(bee);
            }
        }
    }

    private BlockPos findNearbyTree(BlockPos pos, World world) {
        for (int y = pos.getY(); y > 60; y--) {
            BlockPos check = new BlockPos(pos.getX(), y, pos.getZ());
            if (world.getBlockState(check).getBlock() == Blocks.LOG || world.getBlockState(check).getBlock() == Blocks.LOG2) {
                return check;
            }
        }
        return null;
    }
}
