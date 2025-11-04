package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.worldgen.dim.WorldProviderCustomNether;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class ModDimension {
    public static final int CUSTOM_NETHER_DIM_ID = 2;

    public static void registerDimensions() {
        //UBM.getLogger().info("Biome is " + ((ModBiomes.NETHER_FOREST == null) ? "null" : "there"));
        if (!DimensionManager.isDimensionRegistered(CUSTOM_NETHER_DIM_ID)) {
            DimensionManager.registerDimension(CUSTOM_NETHER_DIM_ID,
                    DimensionType.register("CustomNether", "_customnether",
                    CUSTOM_NETHER_DIM_ID,
                    WorldProviderCustomNether.class,
                    true
                    ));
        }
    }
}
