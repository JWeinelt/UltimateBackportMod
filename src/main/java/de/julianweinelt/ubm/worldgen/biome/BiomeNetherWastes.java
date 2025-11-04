package de.julianweinelt.ubm.worldgen.biome;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeNetherWastes extends Biome {
    public BiomeNetherWastes() {
        super(new BiomeProperties("Nether Wastes")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = Blocks.NETHERRACK.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 1, 1, 1));
    }



    @Override
    public void decorate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos) {
        super.decorate(worldIn, rand, pos);
    }
}
