package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeBasaltDeltas extends Biome {
    public BiomeBasaltDeltas() {
        super(new BiomeProperties("Basalt Deltas")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = ModBlocks.BLACKSTONE.getDefaultState();
        this.fillerBlock = ModBlocks.SMOOTH_BASALT.getDefaultState();

        this.decorator.treesPerChunk = 0;  // No trees in basalt deltas
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        // Generate basalt pillars
        for (int i = 0; i < 4; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            // Create basalt pillars of varying heights
            int pillarHeight = 3 + rand.nextInt(6);
            for (int h = 0; h < pillarHeight; h++) {
                BlockPos pillarPos = new BlockPos(x, y + h, z);
                if (worldIn.isAirBlock(pillarPos)) {
                    worldIn.setBlockState(pillarPos, ModBlocks.SMOOTH_BASALT.getDefaultState(), 2);
                }
            }
        }

        // Generate basalt patches on the ground
        for (int i = 0; i < 6; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            int radius = 1 + rand.nextInt(2);
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        BlockPos basaltPos = new BlockPos(x + dx, y - 1, z + dz);
                        if (worldIn.getBlockState(basaltPos).getBlock() == Blocks.NETHERRACK) {
                            if (rand.nextFloat() < 0.7f) {
                                worldIn.setBlockState(basaltPos, ModBlocks.SMOOTH_BASALT.getDefaultState(), 2);
                            } else {
                                worldIn.setBlockState(basaltPos, ModBlocks.BLACKSTONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        // Add some polished basalt features
        for (int i = 0; i < 3; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos polishedPos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(polishedPos) && rand.nextFloat() < 0.15f) {
                worldIn.setBlockState(polishedPos, ModBlocks.POLISHED_BLACKSTONE.getDefaultState(), 2);
            }
        }

        // Add magma block patches for heat sources
        for (int i = 0; i < 2; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos magmaPos = new BlockPos(x, y - 1, z);
            if (worldIn.getBlockState(magmaPos).getBlock() == Blocks.NETHERRACK && rand.nextFloat() < 0.3f) {
                worldIn.setBlockState(magmaPos, Blocks.MAGMA.getDefaultState(), 2);
            }
        }
    }
}