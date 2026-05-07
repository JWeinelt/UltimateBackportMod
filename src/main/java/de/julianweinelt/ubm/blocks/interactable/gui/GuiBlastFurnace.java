package de.julianweinelt.ubm.blocks.interactable.gui;

import de.julianweinelt.ubm.blocks.interactable.container.ContainerBlastFurnace;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBlastFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiBlastFurnace extends GuiContainer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("ubm", "textures/gui/blast_furnace.png");

    private final TileEntityBlastFurnace te;
    private final ContainerBlastFurnace container;

    public GuiBlastFurnace(ContainerBlastFurnace container, TileEntityBlastFurnace te) {
        super(container);
        this.te = te;
        this.container = container;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(TEXTURE);
        int x = (width  - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (container.isBurning()) {
            int scale = container.getBurnTimeScaled(13);
            drawTexturedModalRect(x + 56, y + 36 + 12 - scale, 176, 12 - scale, 14, scale + 1);
        }

        int progress = container.getCookProgressScaled(24);
        drawTexturedModalRect(x + 79, y + 34, 176, 14, progress + 1, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("container.blast_furnace");
        fontRenderer.drawString(title, xSize / 2 - fontRenderer.getStringWidth(title) / 2, 6, 0x404040);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 0x404040);
    }
}