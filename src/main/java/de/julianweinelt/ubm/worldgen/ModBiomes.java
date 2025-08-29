package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber
public class ModBiomes {
    private static final List<BiomeData> BIOMES = new ArrayList<>();

    public static final Biome NETHER_FOREST = register("nether_forest", new BiomeNetherForest(), BiomeManager.BiomeType.DESERT, 10, true);
    public static final Biome CRIMSON_FOREST = register("crimson_forest", new BiomeCrimsonForest(), BiomeManager.BiomeType.DESERT, 10, true);
    public static final Biome SOUL_SAND_VALLEY = register("soul_sand_valley", new BiomeSoulSandValley(), BiomeManager.BiomeType.DESERT, 10, true);
    public static final Biome BASALT_DELTAS = register("basalt_deltas", new BiomeBasaltDeltas(), BiomeManager.BiomeType.DESERT, 10, true);
    public static final Biome SNOWY_SLOPES = register("snowy_slopes", new BiomeSnowySlopes(), BiomeManager.BiomeType.COOL, 10, true);

    private static Biome register(String name, Biome biome, BiomeManager.BiomeType type, int weight, boolean spawn) {
        BiomeData data = new BiomeData(name, biome, type, weight, spawn);
        BIOMES.add(data);
        return biome;
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        for (BiomeData data : BIOMES) {
            event.getRegistry().register(data.biome.setRegistryName("ubm", data.name));
        }
    }

    public static void init() {
        for (BiomeData data : BIOMES) {
            BiomeManager.addBiome(data.type, new BiomeManager.BiomeEntry(data.biome, data.weight));
            if (data.spawn) {
                BiomeManager.addSpawnBiome(data.biome);
            }
        }
    }

    private static class BiomeData {
        final String name;
        final Biome biome;
        final BiomeManager.BiomeType type;
        final int weight;
        final boolean spawn;

        BiomeData(String name, Biome biome, BiomeManager.BiomeType type, int weight, boolean spawn) {
            this.name = name;
            this.biome = biome;
            this.type = type;
            this.weight = weight;
            this.spawn = spawn;
        }
    }
}
