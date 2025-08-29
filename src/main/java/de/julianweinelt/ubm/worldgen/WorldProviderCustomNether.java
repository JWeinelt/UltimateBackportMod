package de.julianweinelt.ubm.worldgen;

import net.minecraft.world.WorldProviderHell;

public class WorldProviderCustomNether extends WorldProviderHell {

    @Override
    public void init() {
        this.biomeProvider = new BiomeProviderNetherCustom();
    }
}
