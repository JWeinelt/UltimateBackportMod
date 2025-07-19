package de.julianweinelt.ubm.worldgen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeSelectableBiome extends WorldType {

    public WorldTypeSelectableBiome(String name) {
        super(name);
    }

    @Override
    public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld) {
        mc.displayGuiScreen(new GuiSelectBiome(guiCreateWorld));
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        String biomeName = world.getWorldInfo().getGeneratorOptions();
        Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(biomeName));
        if (biome == null) biome = Biomes.PLAINS;
        return new BiomeProviderSingle(biome);
    }
    @Override
    public boolean isCustomizable() {
        return true;
    }

}
