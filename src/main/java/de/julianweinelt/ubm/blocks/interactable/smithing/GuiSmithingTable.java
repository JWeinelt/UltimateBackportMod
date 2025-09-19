package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSmithingTable extends GuiContainer {
    private static final ResourceLocation BG_TEXTURE =
        new ResourceLocation("ubm", "textures/gui/smithing.png");

    public GuiSmithingTable(InventoryPlayer playerInv) {
        super(new ContainerSmithingTable(playerInv));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("Upgrade Gear", 44, 15, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }


}
