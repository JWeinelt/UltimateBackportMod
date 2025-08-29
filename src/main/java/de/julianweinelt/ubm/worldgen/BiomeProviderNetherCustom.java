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

    public BiomeProviderNetherCustom() {
        // Initialize allowedBiomes with vanilla nether first to prevent NPE
        this.allowedBiomes = new ArrayList<>();
        this.allowedBiomes.add(Biomes.HELL);
    }

    private Biome[] getAvailableBiomes() {
        List<Biome> biomes = new ArrayList<>();
        
        // Always start with vanilla nether
        biomes.add(Biomes.HELL);
        
        // Safely try to add custom biomes
        try {
            if (ModBiomes.NETHER_FOREST != null && ModBiomes.NETHER_FOREST.getRegistryName() != null) {
                biomes.add(ModBiomes.NETHER_FOREST);
            }
            if (ModBiomes.CRIMSON_FOREST != null && ModBiomes.CRIMSON_FOREST.getRegistryName() != null) {
                biomes.add(ModBiomes.CRIMSON_FOREST);
            }
            if (ModBiomes.SOUL_SAND_VALLEY != null && ModBiomes.SOUL_SAND_VALLEY.getRegistryName() != null) {
                biomes.add(ModBiomes.SOUL_SAND_VALLEY);
            }
            if (ModBiomes.BASALT_DELTAS != null && ModBiomes.BASALT_DELTAS.getRegistryName() != null) {
                biomes.add(ModBiomes.BASALT_DELTAS);
            }
        } catch (Exception e) {
            // If any error occurs, continue with just vanilla nether
        }
        
        return biomes.toArray(new Biome[0]);
    }

    @Override
    public Biome getBiome(BlockPos pos) {
        try {
            Biome[] biomes = getAvailableBiomes();
            
            // Always return vanilla nether if only one biome available
            if (biomes.length <= 1) {
                return Biomes.HELL;
            }
            
            // Hash-based biome distribution with larger regions
            int x = pos.getX() / 200;
            int z = pos.getZ() / 200;
            int hash = Math.abs((x * 374761393 + z * 668265263) % biomes.length);
            
            return biomes[hash];
        } catch (Exception e) {
            // Always fallback to vanilla nether on any error
            return Biomes.HELL;
        }
    }

    @Override
    public Biome[] getBiomes(Biome[] biomeArray, int x, int z, int width, int length) {
        try {
            int arraySize = width * length;
            if (biomeArray == null || biomeArray.length < arraySize) {
                biomeArray = new Biome[arraySize];
            }

            for (int i = 0; i < arraySize; i++) {
                int localX = x + (i % width);
                int localZ = z + (i / width);
                biomeArray[i] = getBiome(new BlockPos(localX, 0, localZ));
            }

            return biomeArray;
        } catch (Exception e) {
            // Fallback: fill with vanilla nether
            if (biomeArray == null || biomeArray.length < width * length) {
                biomeArray = new Biome[width * length];
            }
            Arrays.fill(biomeArray, Biomes.HELL);
            return biomeArray;
        }
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        try {
            // Update the list with current biomes
            List<Biome> spawningBiomes = new ArrayList<>();
            spawningBiomes.addAll(Arrays.asList(getAvailableBiomes()));
            
            // Update the instance variable too
            this.allowedBiomes = spawningBiomes;
            
            return spawningBiomes;
        } catch (Exception e) {
            // Fallback to vanilla nether only
            List<Biome> fallback = new ArrayList<>();
            fallback.add(Biomes.HELL);
            this.allowedBiomes = fallback;
            return fallback;
        }
    }
}
