package de.julianweinelt.ubm.worldgen;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiSelectBiome extends GuiScreen {
    private final GuiCreateWorld parent;
    private GuiSlotBiomes slot;

    public GuiSelectBiome(GuiCreateWorld parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.slot = new GuiSlotBiomes(this.mc, this);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 30, "Zurück"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(parent);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.slot.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, "Wähle ein Biome", this.width / 2, 15, 0xFFFFFF);
    }

    public void setBiome(ResourceLocation biome) {
        parent.chunkProviderSettingsJson = biome.toString();
        this.mc.displayGuiScreen(parent);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.slot.handleMouseInput();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.slot.handleMouseInput();
    }


}
