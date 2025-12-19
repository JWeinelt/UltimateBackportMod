package de.julianweinelt.ubm.worldgen.biome;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.worldgen.tree.WorldGenWarpedTree;
import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeWarpedForest extends Biome {
    public BiomeWarpedForest() {
        super(new BiomeProperties("Warped Forest")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = ModBlocks.WARPED_NYLIUM.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.decorator.treesPerChunk = 6;
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
    }

    @Override
    public void decorate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        for (int i = 0; i < 3; ++i) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int[] ys = new int[3];
            int y = 127;
            int idx = 0;
            for (int j = 0; j < y; j++) {
                if (idx == 2) break;
                if (worldIn.getBlockState(new BlockPos(x, j, z)).getBlock() == ModBlocks.WARPED_NYLIUM) {
                    ys[idx] = j;
                    idx++;
                }
            }

            for (int a : ys) {
                BlockPos treePos = new BlockPos(x, a + 1, z);

                new WorldGenWarpedTree().generate(worldIn, rand, treePos);
                UBM.getLogger().info("Setting Tree at " + treePos.getX() + ", " + treePos.getY() + ", " + treePos.getZ());
            }
        }
    }
}
