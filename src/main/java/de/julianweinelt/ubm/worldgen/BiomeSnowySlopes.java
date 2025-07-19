package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.entities.EntityGoat;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeSnowySlopes extends Biome {
    public BiomeSnowySlopes() {
        super(new BiomeProperties("Snowy Slopes")
                .setTemperature(0)
                .setBaseHeight(1.5F)
                .setHeightVariation(0.4F)
                .setRainfall(0.3F)
        );


        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();

        this.decorator.treesPerChunk = 1;
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityGoat.class, 10, 1, 2));

        this.decorator.generateFalls = false;

    }
}
