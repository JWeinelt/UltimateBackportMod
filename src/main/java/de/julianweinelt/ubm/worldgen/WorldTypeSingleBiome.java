package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeSingleBiome extends WorldType {

    private final Biome biome;

    public WorldTypeSingleBiome(String name, Biome biome) {
        super(name);
        this.biome = biome;
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderSingle(biome);
    }
}
