package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.worldgen.biome.*;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModBiomes {
    private static final List<BiomeData> BIOMES = new ArrayList<>();

    public static final Biome WARPED_FOREST = register("warped_forest", new BiomeWarpedForest(), BiomeManager.BiomeType.DESERT, 4, false);
    public static final Biome CRIMSON_FOREST = register("crimson_forest", new BiomeCrimsonForest(), BiomeManager.BiomeType.DESERT, 10, false);
    public static final Biome SNOWY_SLOPES = register("snowy_slopes", new BiomeSnowySlopes(), BiomeManager.BiomeType.COOL, 10, true);
    public static final Biome GROVE = register("grove", new BiomeGrove(), BiomeManager.BiomeType.COOL, 10, true);
    public static final Biome WARM_OCEAN = register("warm_ocean", new BiomeWarmOcean(), BiomeManager.BiomeType.WARM, 10, true);
    public static final Biome SOULSAND_VALLEY = register("soulsand_valley", new BiomeSoulSandValley(), BiomeManager.BiomeType.WARM, 10, false);
    public static final Biome NETHER_WASTES = register("nether_wastes", new BiomeNetherWastes(), BiomeManager.BiomeType.WARM, 10, false);

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


    public static final int ID_WARPED = Biome.getIdForBiome(WARPED_FOREST);
    public static final int ID_CRIMSON = Biome.getIdForBiome(CRIMSON_FOREST);
    public static final int ID_SOULSAND = Biome.getIdForBiome(SOULSAND_VALLEY);
    public static final int ID_NETHER = Biome.getIdForBiome(NETHER_WASTES);

    public static final int[] NETHER_BIOME_IDS = {
            ID_WARPED,
            ID_CRIMSON,
            //ID_SOULSAND,
            //ID_NETHER
    };

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
