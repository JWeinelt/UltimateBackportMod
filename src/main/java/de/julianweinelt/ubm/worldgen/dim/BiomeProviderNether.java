package de.julianweinelt.ubm.worldgen.dim;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeProviderNether extends BiomeProvider {
    private final NoiseGeneratorPerlin noiseGen;

    public BiomeProviderNether(long worldSeed) {
        super();
        this.noiseGen = new NoiseGeneratorPerlin(new Random(worldSeed), 1);
    }

    @Override
    @Nonnull
    public Biome getBiome(BlockPos pos) {
        double noise = noiseGen.getValue(pos.getX() / 100.0, pos.getZ() / 100.0);
        return noise > 0 ? ModBiomes.WARPED_FOREST : ModBiomes.CRIMSON_FOREST;
    }

    @Override
    @Nonnull
    public Biome getBiome(BlockPos pos, @Nonnull Biome defaultBiome) {
        double noise = noiseGen.getValue(pos.getX() / 100.0, pos.getZ() / 100.0);
        return noise > 0 ? ModBiomes.WARPED_FOREST : ModBiomes.CRIMSON_FOREST;
    }

    @Override
    @Nonnull
    public Biome[] getBiomesForGeneration(@Nonnull Biome[] biomes, int x, int z, int width, int height) {
        if (biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        for (int i = 0; i < width * height; i++) {
            int localX = x + (i % width);
            int localZ = z + (i / width);
            double noise = noiseGen.getValue(localX / 100.0, localZ / 100.0);
            biomes[i] = noise > 0 ? ModBiomes.WARPED_FOREST : ModBiomes.CRIMSON_FOREST;
        }

        return biomes;
    }

    @Override
    @Nonnull
    public Biome[] getBiomes(Biome[] biomes, int x, int z, int width, int height, boolean useCache) {
        if (biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        for (int i = 0; i < width * height; i++) {
            int localX = x + (i % width);
            int localZ = z + (i / width);
            double noise = noiseGen.getValue(localX / 100.0, localZ / 100.0);
            biomes[i] = noise > 0 ? ModBiomes.WARPED_FOREST : ModBiomes.CRIMSON_FOREST;
        }

        return biomes;
    }
}
