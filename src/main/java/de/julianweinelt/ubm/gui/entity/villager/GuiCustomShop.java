package de.julianweinelt.ubm.gui.entity.villager;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiCustomShop extends GuiScreen {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("ubm", "textures/gui/villager.png");
    private int guiLeft, guiTop;
    private int xSize = 176;
    private int ySize = 166;

    private int scrollOffset = 0;

    private List<ItemStack> itemsForSale = new ArrayList<>();
    
    @Override
    public void initGui() {
        guiLeft = (this.width - xSize) / 2;
        guiTop = (this.height - ySize) / 2;

        itemsForSale.add(new ItemStack(Items.APPLE, 5));
        itemsForSale.add(new ItemStack(Items.BREAD, 3));
        itemsForSale.add(new ItemStack(Items.WHEAT, 10));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);


        mc.getTextureManager().bindTexture(GUI_TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int startY = guiTop + 10;
        int buttonHeight = 20;
        int displayCount = 5;

        for (int i = 0; i < displayCount; i++) {
            int index = i + scrollOffset;
            if (index >= itemsForSale.size()) break;

            ItemStack stack = itemsForSale.get(index);
            int y = startY + i * buttonHeight;

            drawRect(guiLeft + 10, y, guiLeft + 150, y + buttonHeight, 0xFF888888);

            itemRender.renderItemAndEffectIntoGUI(stack, guiLeft + 12, y + 2);

            fontRenderer.drawString("" + stack.getCount(), guiLeft + 30, y + 6, 0xFFFFFF);
        }

        drawRect(guiLeft + 160, guiTop + 10, guiLeft + 220, guiTop + 60, 0xFF666666);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int startY = guiTop + 10;
        int buttonHeight = 20;
        int displayCount = 5;

        for (int i = 0; i < displayCount; i++) {
            int index = i + scrollOffset;
            if (index >= itemsForSale.size()) break;
            int y = startY + i * buttonHeight;
            if (mouseX >= guiLeft + 10 && mouseX <= guiLeft + 150 && mouseY >= y && mouseY <= y + buttonHeight) {
                ItemStack stack = itemsForSale.get(index);
                System.out.println("Klick auf: " + stack.getDisplayName());
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int dWheel = Mouse.getEventDWheel();
        if (dWheel > 0) scrollOffset = Math.max(scrollOffset - 1, 0);
        if (dWheel < 0) scrollOffset = Math.min(scrollOffset + 1, itemsForSale.size() - 5);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
