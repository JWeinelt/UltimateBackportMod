package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.nether.NetherWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
    public static DimensionType CUSTOM_NETHER;

    public static void register() {
        CUSTOM_NETHER = DimensionType.register(
                "custom_nether",
                "_custom_nether",
                -10,
                NetherWorldProvider.class,
                false
        );
        DimensionManager.registerDimension(-10, CUSTOM_NETHER);
    }
}