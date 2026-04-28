package de.julianweinelt.ubm.worldgen.nether;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class NetherBiomeProvider extends BiomeProvider {

    private final Biome[] allowedBiomes;
    private final GenLayer layerGeneration;
    private final GenLayer layerLookup;

    public NetherBiomeProvider(World world) {
        this.allowedBiomes = new Biome[]{
                ModBiomes.CRIMSON_FOREST,
                ModBiomes.WARPED_FOREST,
                ModBiomes.NETHER_WASTES,
                ModBiomes.SOUL_SAND_VALLEY,

        };
        GenLayer[] layers = buildLayerChain(world.getSeed());

        this.layerGeneration = layers[0];
        this.layerLookup = layers[2];

        layerGeneration.initWorldGenSeed(world.getSeed());
        layerLookup.initWorldGenSeed(world.getSeed());
    }

    private GenLayer[] buildLayerChain(long seed) {
        GenLayer base = new GenLayerNetherBiomes(1L, null, allowedBiomes);
        GenLayer zoom1 = new GenLayerZoom(1000L, base);
        GenLayer zoom2 = new GenLayerZoom(1001L, zoom1);
        GenLayer smooth = new GenLayerSmooth(1002L, zoom2);
        GenLayer zoom3 = new GenLayerZoom(1003L, smooth);
        GenLayer zoom4 = new GenLayerZoom(1004L, zoom3);
        GenLayer smooth2 = new GenLayerSmooth(1005L, zoom4);
        GenLayer voronoi = new GenLayerVoronoiZoom(10L, smooth2);
        return new GenLayer[]{smooth2, smooth2, voronoi};
    }

    @Override
    public Biome getBiome(BlockPos pos, @Nullable Biome fallback) {

        int[] ids = layerLookup.getInts(pos.getX(), pos.getZ(), 1, 1);
        Biome result = Biome.getBiome(ids[0]);
        return result != null ? result : fallback;
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] cache, int x, int z, int width, int height) {
        if (cache == null || cache.length < width * height) {
            cache = new Biome[width * height];
        }

        int[] ids = layerGeneration.getInts(x, z, width, height);
        for (int i = 0; i < ids.length; i++) {
            Biome b = Biome.getBiome(ids[i]);
            cache[i] = b != null ? b : allowedBiomes[0];
        }

        return cache;
    }


    @Override
    public Biome[] getBiomes(@Nullable Biome[] cache, int x, int z, int width, int height) {
        return getBiomesForGeneration(cache, x, z, width, height);
    }


    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return Arrays.asList(allowedBiomes);
    }
}