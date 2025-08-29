package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BiomeProviderNetherCustom extends BiomeProvider {

    private Biome[] netherBiomes;

    public BiomeProviderNetherCustom() {
        // Initialize allowedBiomes list
        if (allowedBiomes == null) {
            allowedBiomes = new ArrayList<>();
        }
        // Don't initialize netherBiomes here - do it lazily to avoid timing issues
    }

    private Biome[] getNetherBiomes() {
        if (netherBiomes == null) {
            // Initialize with null checks to handle registration timing issues
            List<Biome> biomes = new ArrayList<>();
            
            // Always include vanilla nether as fallback
            biomes.add(Biomes.HELL);
            
            // Add custom biomes if they're registered, otherwise use vanilla as fallback
            if (ModBiomes.NETHER_FOREST != null) {
                biomes.add(ModBiomes.NETHER_FOREST);
            } else {
                biomes.add(Biomes.HELL);
            }
            
            if (ModBiomes.CRIMSON_FOREST != null) {
                biomes.add(ModBiomes.CRIMSON_FOREST);
            } else {
                biomes.add(Biomes.HELL);
            }
            
            if (ModBiomes.SOUL_SAND_VALLEY != null) {
                biomes.add(ModBiomes.SOUL_SAND_VALLEY);
            } else {
                biomes.add(Biomes.HELL);
            }
            
            if (ModBiomes.BASALT_DELTAS != null) {
                biomes.add(ModBiomes.BASALT_DELTAS);
            } else {
                biomes.add(Biomes.HELL);
            }
            
            netherBiomes = biomes.toArray(new Biome[0]);
            
            // Update allowedBiomes list
            allowedBiomes.clear();
            allowedBiomes.addAll(Arrays.asList(netherBiomes));
        }
        return netherBiomes;
    }

    @Override
    public Biome getBiome(BlockPos pos) {
        Biome[] biomes = getNetherBiomes();
        
        // More varied biome distribution using both coordinates and some noise
        int x = pos.getX() / 200; // Larger biome regions
        int z = pos.getZ() / 200;
        
        // Simple hash-based distribution
        int hash = (x * 374761393 + z * 668265263) % biomes.length;
        if (hash < 0) hash += biomes.length;
        
        return biomes[hash];
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

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        getNetherBiomes(); // Ensure biomes are initialized
        return allowedBiomes;
    }
}
