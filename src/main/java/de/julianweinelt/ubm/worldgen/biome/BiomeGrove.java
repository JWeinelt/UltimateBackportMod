package de.julianweinelt.ubm.worldgen.biome;

import de.julianweinelt.ubm.entities.EntityFox;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BiomeGrove extends Biome {
    public BiomeGrove() {
        super(new BiomeProperties("Grove")
                .setTemperature(-0.2F)
                .setBaseHeight(1F)
                .setHeightVariation(0.1F)
                .setRainfall(0.8F)
                .setWaterColor(0x3F76E4)
                .setSnowEnabled()
        );


        this.topBlock = Blocks.SNOW.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityCustomWolf.class, 10, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 10, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityFox.class, 10, 1, 2));

        this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 1, 2));

        //this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityGlowSquid.class, 10, 1, 2));

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieVillager.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 1, 2));

        this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 8;
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        /*WorldGenPowderSnow snowGen = new WorldGenPowderSnow(ModBlocks.POWDER_SNOW.getDefaultState());
        for (int i = 0; i < 3; i++) {
            BlockPos genPos = pos.add(rand.nextInt(16), 0, rand.nextInt(16));
            snowGen.generate(worldIn, rand, genPos);
        }*/
    }

    @Override
    @Nonnull
    public WorldGenAbstractTree getRandomTreeFeature(@Nullable Random rand) {
        return new WorldGenTaiga1();
    }

}