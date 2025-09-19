package de.julianweinelt.ubm.worldgen;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeGroupsModern {
    public static final Biome[] HOT = new Biome[] {
        Biomes.DESERT,
        Biomes.SAVANNA,
        Biomes.MESA,
    };

    public static final Biome[] TEMPERATE = new Biome[] {
            Biomes.FOREST,
            Biomes.PLAINS,
            Biomes.JUNGLE,
            Biomes.SWAMPLAND,
            Biomes.BIRCH_FOREST,
            Biomes.BIRCH_FOREST_HILLS,
            Biomes.MUTATED_BIRCH_FOREST,
            Biomes.MUTATED_BIRCH_FOREST_HILLS,
            Biomes.FOREST_HILLS,
            Biomes.MUTATED_PLAINS
    };

    public static final Biome[] COLD = new Biome[] {
            Biomes.COLD_TAIGA,
            Biomes.ICE_PLAINS,
            Biomes.EXTREME_HILLS,
    };

    public static final Biome[] OCEANS = new Biome[] {
            Biomes.OCEAN,
            Biomes.DEEP_OCEAN,
            ModBiomes.WARM_OCEAN
    };
}