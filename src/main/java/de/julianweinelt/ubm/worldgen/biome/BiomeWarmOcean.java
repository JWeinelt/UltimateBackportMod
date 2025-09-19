package de.julianweinelt.ubm.worldgen.biome;

import de.julianweinelt.ubm.entities.EntityDolphin;
import de.julianweinelt.ubm.entities.EntityFox;
import de.julianweinelt.ubm.entities.EntityGlowSquid;
import de.julianweinelt.ubm.entities.EntityTropicalFish;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BiomeWarmOcean extends BiomeOcean {
    public BiomeWarmOcean() {
        super(new BiomeProperties("WarmOcean")
                .setTemperature(0.5F)
                .setBaseHeight(-1F)
                .setRainfall(0.5F)
                .setWaterColor(0x43D5EE)
                .setSnowEnabled()
        );


        this.topBlock = Blocks.WATER.getDefaultState();
        this.fillerBlock = Blocks.WATER.getDefaultState();

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityTropicalFish.class, 10, 8, 8));
        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityDolphin.class, 10, 1, 2));
        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
    }

    @Override
    @Nonnull
    public WorldGenAbstractTree getRandomTreeFeature(@Nullable Random rand) {
        return new WorldGenTaiga1();
    }

}