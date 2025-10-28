package de.julianweinelt.ubm.mixin.loading;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SimpleLoadingScreen extends GuiScreen {

    private final String message;

    public SimpleLoadingScreen(String message) {
        this.message = message;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, 0xFF000000);

        int stringWidth = fontRenderer.getStringWidth(message);
        fontRenderer.drawString(message, width / 2 - stringWidth / 2, height / 2, 0xFFFFFF);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
