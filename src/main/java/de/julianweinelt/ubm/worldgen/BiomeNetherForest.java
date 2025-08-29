package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.tree.WorldGenMyNetherTree;
import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeNetherForest extends Biome {
    public BiomeNetherForest() {
        super(new BiomeProperties("Nether Forest")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = ModBlocks.WARPED_NYLIUM.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.decorator.treesPerChunk = 3;  // optional
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        // Generate warped trees
        for (int i = 0; i < 3; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos treePos = new BlockPos(x, y, z);
            new WorldGenMyNetherTree().generate(worldIn, rand, treePos);
        }

        // Add warped roots and fungi decorations
        for (int i = 0; i < 8; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos decorPos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(decorPos) && worldIn.getBlockState(decorPos.down()).getBlock() == ModBlocks.WARPED_NYLIUM) {
                if (rand.nextFloat() < 0.4f) {
                    // Add warped fungi if available
                    if (ModBlocks.WARPED_FUNGUS != null) {
                        worldIn.setBlockState(decorPos, ModBlocks.WARPED_FUNGUS.getDefaultState(), 2);
                    }
                } else if (rand.nextFloat() < 0.3f) {
                    // Add warped roots if available  
                    if (ModBlocks.WARPED_ROOTS != null) {
                        worldIn.setBlockState(decorPos, ModBlocks.WARPED_ROOTS.getDefaultState(), 2);
                    }
                }
            }
        }

        // Add some nether sprouts for undergrowth
        for (int i = 0; i < 4; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos sproutPos = new BlockPos(x, y, z);
            if (worldIn.isAirBlock(sproutPos) && worldIn.getBlockState(sproutPos.down()).getBlock() == ModBlocks.WARPED_NYLIUM) {
                if (rand.nextFloat() < 0.2f && ModBlocks.NETHER_SPROUTS != null) {
                    worldIn.setBlockState(sproutPos, ModBlocks.NETHER_SPROUTS.getDefaultState(), 2);
                }
            }
        }
    }
}
