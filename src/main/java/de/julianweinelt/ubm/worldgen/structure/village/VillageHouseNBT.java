package de.julianweinelt.ubm.worldgen.structure.village;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class VillageHouseNBT extends StructureVillagePieces.Village {
    private String structureName;

    public VillageHouseNBT() {}

    public VillageHouseNBT(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox box, EnumFacing facing, String structureName) {
        super(start, type);
        this.setCoordBaseMode(facing);
        this.boundingBox = box;
        this.structureName = structureName;
    }

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box) {
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        Template template = manager.getTemplate(world.getMinecraftServer(),
                new ResourceLocation("ubm", "village/" + structureName));
        if (template == null) return false;

        int groundY = getAverageGroundLevel(world, boundingBox.minX, boundingBox.minZ,
                boundingBox.getXSize(), boundingBox.getZSize());

        int offsetY = groundY - boundingBox.minY;
        this.boundingBox.offset(0, offsetY, 0);

        BlockPos pos = new BlockPos(boundingBox.minX, boundingBox.minY, boundingBox.minZ);

        PlacementSettings settings = new PlacementSettings()
                .setRotation(Rotation.NONE) //TODO: Adjust
                .setIgnoreEntities(true);

        template.addBlocksToWorldChunk(world, pos, settings);

        return true;
    }

    private int getAverageGroundLevel(World world, int x, int z, int sizeX, int sizeZ) {
        int total = 0;
        int count = 0;
        for (int dx = 0; dx < sizeX; dx++) {
            for (int dz = 0; dz < sizeZ; dz++) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x + dx, 0, z + dz));
                total += pos.getY();
                count++;
            }
        }
        return count > 0 ? total / count : 64;
    }
}
