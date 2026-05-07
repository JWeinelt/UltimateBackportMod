package de.julianweinelt.ubm.worldgen.biome;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.worldgen.tree.WorldGenCrimsonTree;
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

public class BiomeSoulSandValley extends Biome {
    public BiomeSoulSandValley() {
        super(new BiomeProperties("Soulsand Valley")
                .setTemperature(2.0F)
                .setRainDisabled());

        this.topBlock = Blocks.SOUL_SAND.getDefaultState();
        this.fillerBlock = Blocks.NETHERRACK.getDefaultState();

        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 1, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 1, 1, 1));
    }



    @Override
    public void decorate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos) {
        super.decorate(worldIn, rand, pos);

        String fossil = "skull_";
        if (rand.nextBoolean()) fossil = "spine_";
        fossil += "" + (rand.nextInt(3) + 1);
        if (fossil.endsWith("_")) fossil = "spine_1";

        TemplateManager manager = worldIn.getSaveHandler().getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation("ubm", "fossil/" + fossil);
        Template template = manager.getTemplate(worldIn.getMinecraftServer(), location);
        PlacementSettings settings = new PlacementSettings()
                .setMirror(Mirror.NONE)
                .setRotation(Rotation.values()[rand.nextInt(Rotation.values().length)])
                .setIgnoreEntities(true);
        template.addBlocksToWorld(worldIn, pos, settings);
    }
}
