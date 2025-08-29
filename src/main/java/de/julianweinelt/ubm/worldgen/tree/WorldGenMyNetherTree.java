package de.julianweinelt.ubm.worldgen.tree;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenMyNetherTree extends WorldGenAbstractTree {
    public WorldGenMyNetherTree() {
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = 7 + rand.nextInt(7);

        for (int y = 0; y <= height + 5; y++) {
            if (!worldIn.isAirBlock(position.up(y))) {
                return false;
            }
        }

        for (int y = 0; y < height; y++) {
            BlockPos logPos = position.up(y);
            worldIn.setBlockState(logPos, ModBlocks.WARPED_STEM.getDefaultState(), 2);
        }

        BlockPos top = position.up(height);

        int radius = 3 + rand.nextInt(2);
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                // Kreisform
                if (dx * dx + dz * dz <= radius * radius + rand.nextInt(2)) {
                    BlockPos leafPos = top.add(dx, 0, dz);
                    worldIn.setBlockState(leafPos, ModBlocks.WARPED_WART_BLOCK.getDefaultState(), 2);

                    if (rand.nextFloat() < 0.5f) {
                        BlockPos leafAbove = leafPos.up();
                        if (worldIn.isAirBlock(leafAbove)) {
                            worldIn.setBlockState(leafAbove, ModBlocks.WARPED_WART_BLOCK.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            int dx = rand.nextInt(radius * 2 + 1) - radius;
            int dz = rand.nextInt(radius * 2 + 1) - radius;
            BlockPos extra = top.add(dx, rand.nextInt(2) + 1, dz);
            if (worldIn.isAirBlock(extra)) {
                worldIn.setBlockState(extra, ModBlocks.WARPED_WART_BLOCK.getDefaultState(), 2);
            }
        }

        // Optionale "Vines" (wenn du einen Block dafür hast, z. B. Weeping Vine)
        // Hier hängen zufällig 1–5 Block lange Ketten unterhalb der Krone
    /*
    for (int i = 0; i < 6; i++) {
        BlockPos vineStart = top.add(rand.nextInt(radius * 2) - radius, 0, rand.nextInt(radius * 2) - radius);
        for (int len = 0; len < 5; len++) {
            BlockPos vinePos = vineStart.down(len + 1);
            if (worldIn.isAirBlock(vinePos)) {
                worldIn.setBlockState(vinePos, ModBlocks.WEEPING_VINE.getDefaultState(), 2);
            } else {
                break;
            }
        }
    }
    */

        return true;
    }

}
