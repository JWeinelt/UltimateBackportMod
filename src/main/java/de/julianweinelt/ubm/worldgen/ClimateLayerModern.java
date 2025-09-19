package de.julianweinelt.ubm.worldgen;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import javax.annotation.Nonnull;

public class ClimateLayerModern extends GenLayer {
    public ClimateLayerModern(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    @Nonnull
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] parentInts = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] dest = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y) {
            for (int x = 0; x < areaWidth; ++x) {
                int index = x + y * areaWidth;
                int parentVal = parentInts[index];

                this.initChunkSeed(x + areaX, y + areaY);

                Biome chosen;

                if (parentVal == Biome.getIdForBiome(Biomes.OCEAN)
                        || parentVal == Biome.getIdForBiome(Biomes.DEEP_OCEAN)) {
                    chosen = BiomeGroupsModern.OCEANS[this.nextInt(BiomeGroupsModern.OCEANS.length)];
                } else {
                    int noise = (int)(this.nextInt(100) + (x + areaX + y + areaY) / 10) % 3;

                    switch(noise) {
                        case 0: chosen = BiomeGroupsModern.HOT[this.nextInt(BiomeGroupsModern.HOT.length)]; break;
                        case 1: chosen = BiomeGroupsModern.TEMPERATE[this.nextInt(BiomeGroupsModern.TEMPERATE.length)]; break;
                        case 2: default: chosen = BiomeGroupsModern.COLD[this.nextInt(BiomeGroupsModern.COLD.length)]; break;
                    }
                }

                dest[index] = Biome.getIdForBiome(chosen);
            }
        }

        return dest;
    }
}

