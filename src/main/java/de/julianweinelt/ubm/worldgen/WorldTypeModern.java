package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.*;

public class WorldTypeModern extends WorldType {
    public WorldTypeModern() {
        super("modern_world");
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new ChunkGeneratorOverworld(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer worldType, ChunkGeneratorSettings options) {
        GenLayer base = new GenLayerIsland(1L);
        base = new GenLayerFuzzyZoom(2000L, base);
        base = new GenLayerAddIsland(1L, base);
        base = new GenLayerZoom(2001L, base);
        base = new GenLayerAddIsland(2L, base);
        base = new GenLayerAddIsland(50L, base);
        base = new GenLayerAddIsland(70L, base);
        base = new GenLayerRemoveTooMuchOcean(2L, base);
        base = new GenLayerAddSnow(2L, base);
        base = new GenLayerAddIsland(3L, base);
        base = new GenLayerEdge(2L, base, GenLayerEdge.Mode.COOL_WARM);
        base = new GenLayerEdge(2L, base, GenLayerEdge.Mode.HEAT_ICE);
        base = new GenLayerEdge(3L, base, GenLayerEdge.Mode.SPECIAL);

        base = new GenLayerZoom(2002L, base);
        base = new GenLayerZoom(2003L, base);
        base = new GenLayerAddIsland(4L, base);
        base = new GenLayerAddMushroomIsland(5L, base);
        base = new GenLayerDeepOcean(4L, base);

        GenLayer climate = new ClimateLayerModern(200L, base);

        climate = new GenLayerZoom(1000L, climate);
        climate = new GenLayerZoom(1001L, climate);
        climate = new GenLayerZoom(1002L, climate);

        return climate;
    }
}
