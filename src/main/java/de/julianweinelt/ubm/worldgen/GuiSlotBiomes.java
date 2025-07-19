package de.julianweinelt.ubm.worldgen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class GuiSlotBiomes extends GuiSlot {
    private final GuiSelectBiome parent;
    private final List<Biome> biomes = new ArrayList<>();

    public GuiSlotBiomes(Minecraft mc, GuiSelectBiome parent) {
        super(mc, parent.width, parent.height, 32, parent.height - 32, 24);
        this.parent = parent;
        for (Biome biome : Biome.REGISTRY) {
            ResourceLocation rl = Biome.REGISTRY.getNameForObject(biome);
            if (rl != null && rl.getResourceDomain().equalsIgnoreCase("ubm")) {
                this.biomes.add(biome);
            }
        }
    }

    @Override
    protected int getSize() {
        return biomes.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick, int mouseX, int mouseY) {
        Biome biome = biomes.get(index);
        parent.setBiome(Biome.REGISTRY.getNameForObject(biome));
    }

    @Override
    protected boolean isSelected(int index) {
        return false;
    }

    @Override
    protected void drawBackground() {
        parent.drawDefaultBackground();
    }

    @Override
    protected void drawSlot(int index, int x, int y, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
        String name = biomes.get(index).getBiomeName();
        parent.drawString(this.mc.fontRenderer, name, x + 2, y + 2, 0xFFFFFF);
    }
}
