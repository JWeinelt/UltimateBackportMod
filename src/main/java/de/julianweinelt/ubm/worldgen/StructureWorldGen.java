package de.julianweinelt.ubm.worldgen;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StructureWorldGen implements IWorldGenerator {
    private List<Biome> getAllBiomes() {
        List<Biome> list = new ArrayList<>();
        for (Biome b : ForgeRegistries.BIOMES) {
            list.add(b);
        }
        return list;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateStructure(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateStructure(World world, Random random, int x, int z) {
        if (random.nextInt(1000) == 0) {
            int y = world.getHeight(new BlockPos(x, 0, z)).getY();
            BlockPos pos = new BlockPos(x, y, z);


            placePillagerOutpost(world, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
        }
        if (random.nextInt(100) == 0) {
            BlockPos pos = new BlockPos(x, random.nextInt(40), z);
            placeStructure(world, pos, "amethyst_geode", random, getAllBiomes());
        }
    }

    private void placeStructure(World world, BlockPos pos, String structureName, Random random, List<Biome> biomes) {
        if (!biomes.contains(world.getBiome(pos))) return;
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation("ubm", structureName);
        Template template = manager.getTemplate(world.getMinecraftServer(), location);
        PlacementSettings settings = new PlacementSettings()
                .setMirror(Mirror.NONE)
                .setRotation(Rotation.values()[random.nextInt(Rotation.values().length)])
                .setIgnoreEntities(false);
        template.addBlocksToWorld(world, pos, settings);
    }

    private void placePillagerOutpost(World world, BlockPos pos) {
        List<Biome> biomes = Arrays.asList(
                Biomes.DESERT,
                Biomes.PLAINS,
                //TODO: Meadow
                //TODO: Grove
                ModBiomes.SNOWY_SLOPES,
                //TODO: Jagged Peaks
                //TODO: Frozen Peaks
                //TODO: Stony Peaks
                Biomes.SAVANNA,
                //TODO: Snowy Taiga
                Biomes.ICE_PLAINS,
                Biomes.MUTATED_PLAINS,
                Biomes.TAIGA
                //TODO: Cherry Grove
        );
        Random random = new Random();
        placeStructure(world, pos, "outpost/watchtower", random, biomes);
        placeAround(world, pos, random, biomes);
        placeAround(world, pos, random, biomes);
        placeAround(world, pos, random, biomes);
        placeAround(world, pos, random, biomes);
    }

    private void placeAround(World world, BlockPos pos, Random random, List<Biome> biomes) {
        int x = pos.getX() + (random.nextInt(32) - 16);
        int z = pos.getZ() + (random.nextInt(32) - 16);
        int y = world.getHeight(new BlockPos(x, 0, z)).getY();

        String[] features = {
                "outpost/feature_tent1",
                "outpost/feature_tent2",
                "outpost/feature_cage1",
                "outpost/feature_cage2",
        };

        placeStructure(world, new BlockPos(x, y, z), features[random.nextInt(3)], random, biomes);
    }
}