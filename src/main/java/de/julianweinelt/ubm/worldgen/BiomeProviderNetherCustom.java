package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import java.util.Arrays;

public class BiomeProviderNetherCustom extends BiomeProvider {

    private final Biome[] netherBiomes = new Biome[] {
            Biomes.HELL,
            ModBiomes.NETHER_FOREST,
            ModBiomes.CRIMSON_FOREST,
            ModBiomes.SOUL_SAND_VALLEY,
            ModBiomes.BASALT_DELTAS
    };

    public BiomeProviderNetherCustom() {
        allowedBiomes.clear();
        allowedBiomes.addAll(Arrays.asList(netherBiomes));
    }

    @Override
    public Biome getBiome(BlockPos pos) {
        // More varied biome distribution using both coordinates and some noise
        int x = pos.getX() / 200; // Larger biome regions
        int z = pos.getZ() / 200;
        
        // Simple hash-based distribution
        int hash = (x * 374761393 + z * 668265263) % netherBiomes.length;
        if (hash < 0) hash += netherBiomes.length;
        
        return netherBiomes[hash];
    }

    @Override
    public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int length) {
        if (oldBiomeList == null || oldBiomeList.length < width * length) {
            oldBiomeList = new Biome[width * length];
        }

        for (int i = 0; i < width * length; i++) {
            int bx = x + (i % width);
            int bz = z + (i / width);
            oldBiomeList[i] = getBiome(new BlockPos(bx, 0, bz));
        }

        return oldBiomeList;
    }
}
