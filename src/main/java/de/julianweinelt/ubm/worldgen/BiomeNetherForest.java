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

        for (int i = 0; i < 3; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = worldIn.getHeight(x, z);

            BlockPos treePos = new BlockPos(x, y, z);

            new WorldGenMyNetherTree().generate(worldIn, rand, treePos);
        }
    }
}
