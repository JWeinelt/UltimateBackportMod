package de.julianweinelt.ubm.worldgen.dim;

import de.julianweinelt.ubm.worldgen.GenLayerNetherBiomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;

public class BiomeProviderNether extends BiomeProvider {

    private final GenLayer genBiomes;

    public BiomeProviderNether(long worldSeed) {
        super();
        this.genBiomes = GenLayerNetherBiomes.initialize(worldSeed);
    }


    @Override
    public Biome getBiome(BlockPos pos) {
        return Biome.getBiome(this.genBiomes.getInts(pos.getX(), pos.getZ(), 1, 1)[0]);
    }

    @Override
    public Biome[] getBiomes(Biome[] biomes, int x, int z, int width, int height, boolean useCache) {
        int[] ids = this.genBiomes.getInts(x, z, width, height);

        if (biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        for (int i = 0; i < ids.length; i++) {
            biomes[i] = Biome.getBiome(ids[i]);
        }

        return biomes;
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        return getBiomes(biomes, x, z, width, height, false);
    }
}
