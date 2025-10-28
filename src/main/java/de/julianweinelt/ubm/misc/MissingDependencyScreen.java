package de.julianweinelt.ubm.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class MissingDependencyScreen extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        String title = "Missing Dependency!";
        String text = "The required mod 'PathfinderAPI' was not found.\nPlease install it to use this mod.";

        this.drawCenteredString(this.fontRenderer, title, this.width / 2, this.height / 2 - 30, 0xFF5555);
        for (int i = 0; i < text.split("\n").length; i++) {
            this.drawCenteredString(this.fontRenderer, text.split("\n")[i], this.width / 2, this.height / 2 - 10 + i * 12, 0xFFFFFF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) {
            Minecraft.getMinecraft().shutdown();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
