package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.DimensionType;

public class ModDimensions {
    public static final DimensionType CUSTOM_NETHER_TYPE = DimensionType.register(
            "CustomNether",
            "_nether",
            -1,
            WorldProviderCustomNether.class,
            false
    );
}
