package de.julianweinelt.ubm.gui.entity.villager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.IMerchant;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiCustomMerchant extends GuiContainer {
    private final IMerchant merchant;
    private int selectedTrade;

    public GuiCustomMerchant(ContainerCustomMerchant container, IMerchant merchant) {
        super(container);
        this.merchant = merchant;
        this.selectedTrade = 0;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("ubm:textures/gui/custom_merchant.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        drawRect(guiLeft + 50, guiTop + 20 + selectedTrade * 20, guiLeft + 120, guiTop + 40 + selectedTrade * 20, 0x80FFFFFF);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
