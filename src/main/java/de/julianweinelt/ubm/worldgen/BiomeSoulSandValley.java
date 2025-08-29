package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeSoulSandValley extends Biome {
    public BiomeSoulSandValley() {
        super(new BiomeProperties("Soul Sand Valley")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = ModBlocks.SOUL_SOIL.getDefaultState();
        this.fillerBlock = Blocks.SOUL_SAND.getDefaultState();

        this.decorator.treesPerChunk = 0;  // No trees in soul sand valley
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        // Generate soul sand patches
        for (int i = 0; i < 8; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            // Create small soul sand valleys
            int radius = 2 + rand.nextInt(3);
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        BlockPos valleyPos = new BlockPos(x + dx, y - 1, z + dz);
                        if (worldIn.getBlockState(valleyPos).getBlock() == Blocks.NETHERRACK) {
                            worldIn.setBlockState(valleyPos, Blocks.SOUL_SAND.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        // Add some soul fire features
        for (int i = 0; i < 3; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos firePos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(firePos) && 
                (worldIn.getBlockState(firePos.down()).getBlock() == Blocks.SOUL_SAND || 
                 worldIn.getBlockState(firePos.down()).getBlock() == ModBlocks.SOUL_SOIL)) {
                if (rand.nextFloat() < 0.2f && ModBlocks.SOUL_FIRE != null) {
                    worldIn.setBlockState(firePos, ModBlocks.SOUL_FIRE.getDefaultState(), 2);
                }
            }
        }

        // Add scattered bone blocks for fossil-like features
        for (int i = 0; i < 2; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos bonePos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(bonePos) && rand.nextFloat() < 0.1f) {
                worldIn.setBlockState(bonePos, Blocks.BONE_BLOCK.getDefaultState(), 2);
            }
        }
    }
}