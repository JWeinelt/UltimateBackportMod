package de.julianweinelt.ubm.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import de.julianweinelt.ubm.worldgen.ModBiomes;

/**
 * Simple test to verify biome provider distribution works correctly
 */
public class NetherBiomeTest {
    
    public static void testBiomeDistribution() {
        BiomeProviderNetherCustom provider = new BiomeProviderNetherCustom();
        
        System.out.println("Testing nether biome distribution:");
        
        // Test various positions to see biome variety
        BlockPos[] testPositions = {
            new BlockPos(0, 64, 0),
            new BlockPos(500, 64, 500),
            new BlockPos(-300, 64, 200),
            new BlockPos(1000, 64, -800),
            new BlockPos(-1500, 64, -1200),
            new BlockPos(2000, 64, 1800)
        };
        
        for (BlockPos pos : testPositions) {
            Biome biome = provider.getBiome(pos);
            System.out.println("Position " + pos + " -> Biome: " + biome.getBiomeName());
        }
        
        // Verify we get different biomes
        boolean hasVariety = false;
        Biome firstBiome = provider.getBiome(testPositions[0]);
        for (int i = 1; i < testPositions.length; i++) {
            if (provider.getBiome(testPositions[i]) != firstBiome) {
                hasVariety = true;
                break;
            }
        }
        
        System.out.println("Biome variety test: " + (hasVariety ? "PASS" : "FAIL"));
    }
}