package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.tree.WorldGenCrimsonTree;
import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeCrimsonForest extends Biome {
    public BiomeCrimsonForest() {
        super(new BiomeProperties("Crimson Forest")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = ModBlocks.CRIMSON_NYLIUM.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.decorator.treesPerChunk = 4;  // Slightly more trees than warped forest
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        // Generate crimson trees
        for (int i = 0; i < 4; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos treePos = new BlockPos(x, y, z);
            new WorldGenCrimsonTree().generate(worldIn, rand, treePos);
        }

        // Add some scattered nether wart blocks for atmosphere
        for (int i = 0; i < 6; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z) + rand.nextInt(4);

            BlockPos decorPos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(decorPos) && worldIn.getBlockState(decorPos.down()).getBlock() == ModBlocks.CRIMSON_NYLIUM) {
                if (rand.nextFloat() < 0.3f) {
                    worldIn.setBlockState(decorPos, Blocks.NETHER_WART.getDefaultState(), 2);
                }
            }
        }
    }
}