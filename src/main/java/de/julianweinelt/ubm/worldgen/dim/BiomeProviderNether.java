package de.julianweinelt.ubm.worldgen.dim;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import javax.annotation.Nonnull;

public class BiomeProviderNether extends BiomeProvider {

    public BiomeProviderNether() {
        super();
    }

    @Override
    @Nonnull
    public Biome getBiome(BlockPos pos) {
        if (pos.getX() > 0)
            return ModBiomes.WARPED_FOREST;
        else return ModBiomes.CRIMSON_FOREST;
    }

    @Override
    @Nonnull
    public Biome getBiome(BlockPos pos, @Nonnull Biome defaultBiome) {
        if (pos.getX() > 0)
            return ModBiomes.WARPED_FOREST;
        else return ModBiomes.CRIMSON_FOREST;
    }

    @Override
    @Nonnull
    public Biome[] getBiomesForGeneration(@Nonnull Biome[] biomes, int x, int z, int width, int height) {
        if (biomes.length < width * height) {
            biomes = new Biome[width * height];
        }

        for (int i = 0; i < biomes.length; i++) {
            if (x > 0)
                biomes[i] = ModBiomes.WARPED_FOREST;
            else biomes[i] = ModBiomes.CRIMSON_FOREST;
        }

        return biomes;
    }

    @Override
    @Nonnull
    public Biome[] getBiomes(Biome[] biomes, int x, int z, int width, int length, boolean useCache) {
        if (biomes == null || biomes.length < width * length) {
            biomes = new Biome[width * length];
        }

        for (int i = 0; i < biomes.length; i++) {
            if (x > 0)
                biomes[i] = ModBiomes.WARPED_FOREST;
            else biomes[i] = ModBiomes.CRIMSON_FOREST;
        }

        return biomes;
    }
}
