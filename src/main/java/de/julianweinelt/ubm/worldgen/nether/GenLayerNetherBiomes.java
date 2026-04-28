package de.julianweinelt.ubm.worldgen.nether;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerNetherBiomes extends GenLayer {

    private final Biome[] biomes;

    public GenLayerNetherBiomes(long baseSeed, GenLayer parent, Biome[] biomes) {
        super(baseSeed);
        this.parent = parent;
        this.biomes = biomes;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] result = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; z++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed(areaX + x, areaY + z);

                int biomeIndex = nextInt(biomes.length);
                result[z * areaWidth + x] = Biome.getIdForBiome(biomes[biomeIndex]);
            }
        }

        return result;
    }
}