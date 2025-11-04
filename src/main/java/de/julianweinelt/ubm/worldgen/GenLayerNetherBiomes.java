package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerNetherBiomes extends GenLayer {

    public GenLayerNetherBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int width, int height) {
        int[] out = IntCache.getIntCache(width * height);

        for (int i = 0; i < width * height; i++) {
            this.initChunkSeed(areaX + (i % width), areaZ + (i / width));
            int index = this.nextInt(ModBiomes.NETHER_BIOME_IDS.length);
            out[i] = ModBiomes.NETHER_BIOME_IDS[index];
        }

        return out;
    }

    public static GenLayer initialize(long seed) {
        GenLayer layer = new GenLayerNetherBiomes(seed);
        layer = new GenLayerZoom(2001L, layer);
        layer = new GenLayerZoom(2002L, layer);
        layer.initWorldGenSeed(seed);
        return layer;
    }
}
