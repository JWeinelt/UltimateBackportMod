package de.julianweinelt.ubm.worldgen.tree;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenCrimsonTree extends WorldGenAbstractTree {
    public WorldGenCrimsonTree() {
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = 6 + rand.nextInt(6);

        // Check if there's enough space
        for (int y = 0; y <= height + 4; y++) {
            if (!worldIn.isAirBlock(position.up(y))) {
                return false;
            }
        }

        // Generate trunk
        for (int y = 0; y < height; y++) {
            BlockPos logPos = position.up(y);
            worldIn.setBlockState(logPos, ModBlocks.CRIMSON_STEM.getDefaultState(), 2);
        }

        // Generate crimson wart block "leaves"
        BlockPos top = position.up(height);
        int radius = 2 + rand.nextInt(2);
        
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                // Create a more organic, irregular shape
                if (dx * dx + dz * dz <= radius * radius + rand.nextInt(3)) {
                    BlockPos leafPos = top.add(dx, 0, dz);
                    worldIn.setBlockState(leafPos, ModBlocks.CRIMSON_PLANKS.getDefaultState(), 2); // Using crimson planks as crimson wart block equivalent
                    
                    // Add some vertical variation
                    if (rand.nextFloat() < 0.4f) {
                        BlockPos leafAbove = leafPos.up();
                        if (worldIn.isAirBlock(leafAbove)) {
                            worldIn.setBlockState(leafAbove, ModBlocks.CRIMSON_PLANKS.getDefaultState(), 2);
                        }
                    }
                    
                    if (rand.nextFloat() < 0.2f) {
                        BlockPos leafBelow = leafPos.down();
                        if (worldIn.isAirBlock(leafBelow)) {
                            worldIn.setBlockState(leafBelow, ModBlocks.CRIMSON_PLANKS.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        // Add some additional scattered blocks for more organic feel
        for (int i = 0; i < 4; i++) {
            int dx = rand.nextInt(radius * 2 + 1) - radius;
            int dz = rand.nextInt(radius * 2 + 1) - radius;
            BlockPos extra = top.add(dx, rand.nextInt(3) - 1, dz);
            if (worldIn.isAirBlock(extra)) {
                worldIn.setBlockState(extra, ModBlocks.CRIMSON_PLANKS.getDefaultState(), 2);
            }
        }

        return true;
    }
}