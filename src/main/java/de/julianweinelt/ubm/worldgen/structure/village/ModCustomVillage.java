package de.julianweinelt.ubm.worldgen.structure.village;

import de.julianweinelt.ubm.UBM;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModCustomVillage {
    private static final Map<String, int[]> houseSizes = new HashMap<>();

    public static void preInit() {
        houseSizes.put("house1", new int[] {8, 7, 7});
        houseSizes.put("house2", new int[] {8, 9, 9});
        houseSizes.put("house3", new int[] {7, 8, 10});
    }

    public static void init() {
        MapGenStructureIO.registerStructureComponent(VillageHouseNBT.class, "VillageHouseNBT");


        String[] houses = {"house1", "house2", "house3"};

        for (String house : houses) {
            VillagerRegistry.instance().registerVillageCreationHandler(new VillagerRegistry.IVillageCreationHandler() {
                @Override
                public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
                    return new StructureVillagePieces.PieceWeight(VillageHouseNBT.class, 15, 1);
                }

                @Override
                public Class<?> getComponentClass() {
                    return VillageHouseNBT.class;
                }

                @Override
                public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight weight,
                                                                     StructureVillagePieces.Start start,
                                                                     List pieces,
                                                                     Random rand,
                                                                     int x, int y, int z,
                                                                     EnumFacing facing, int type) {
                    int maxX = houseSizes.getOrDefault(house, new int[]{0, 0, 0})[0];
                    int maxY = houseSizes.getOrDefault(house, new int[]{0, 0, 0})[1];
                    int maxZ = houseSizes.getOrDefault(house, new int[]{0, 0, 0})[2];

                    StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(
                            x, y, z, 0, 0, 0,
                            maxX, maxY, maxZ,
                            facing
                    );

                    if (box == null) {
                        return null;
                    }

                    UBM.getLogger().info("Placeing village house 1");
                    return new VillageHouseNBT(start, type, rand, box, facing, house);
                }


            });
        }
    }
}