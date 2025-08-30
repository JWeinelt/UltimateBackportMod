package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.entities.EntityGoat;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeSnowySlopes extends Biome {
    public BiomeSnowySlopes() {
        super(new BiomeProperties("Snowy Slopes")
                .setTemperature(-0.3F)
                .setBaseHeight(1.5F)
                .setHeightVariation(0.3F)
                .setRainfall(0.9F)
                .setWaterColor(0x3F76E4)
                .setSnowEnabled()
        );


        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();

        this.decorator.treesPerChunk = 1;
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityGoat.class, 10, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 10, 1, 2));

        this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 1, 2));

        //this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityGlowSquid.class, 10, 1, 2));

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieVillager.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 1, 2));

        this.decorator.generateFalls = false;
    }
}