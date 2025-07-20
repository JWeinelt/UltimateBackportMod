package de.julianweinelt.ubm.worldgen;

import net.minecraft.block.Block;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructureWorldGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateStructure(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateStructure(World world, Random random, int x, int z) {
        if (random.nextInt(10) == 0) {
            int y = world.getHeight(new BlockPos(x, 0, z)).getY();
            BlockPos pos = new BlockPos(x, y, z);

            placeStructure(world, pos, "outpost/watchtower", random);
        }
    }

    private void placeStructure(World world, BlockPos pos, String structureName, Random random) {
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation("ubm", structureName);
        Template template = manager.getTemplate(world.getMinecraftServer(), location);

        PlacementSettings settings = new PlacementSettings()
                .setMirror(Mirror.NONE)
                .setRotation(Rotation.values()[random.nextInt(Rotation.values().length)])
                .setIgnoreEntities(false);
        template.addBlocksToWorld(world, pos, settings);
    }
}
