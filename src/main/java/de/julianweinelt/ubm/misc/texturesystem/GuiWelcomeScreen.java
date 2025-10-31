package de.julianweinelt.ubm.misc.texturesystem;

import de.julianweinelt.ubm.util.ColorHex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class GuiWelcomeScreen extends GuiScreen {
    private static final int BTN_MODE = 0;
    private static final int BTN_REPLACE_VANILLA = 1;
    private static final int BTN_DONE = 2;

    private int textureMode = 0; // 0 = Original, 1 = UBM
    private String useVersion = "";
    private boolean replaceVanillaTextures = true;

    @Override
    public void initGui() {
        int centerX = this.width / 2;
        int yStart = 60;

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(BTN_MODE, centerX - 100, yStart, 200, 20,
                TextFormatting.YELLOW + "Use Original Textures"));
        this.buttonList.add(new GuiButton(BTN_REPLACE_VANILLA, centerX - 100, yStart + 30, 50, 20,
                TextFormatting.YELLOW + "Yes"));
        this.buttonList.add(new GuiButton(BTN_DONE, centerX - 40, this.height - 40, 80, 20,
                TextFormatting.WHITE + "Done"));
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Welcome to the Ultimate Backport Mod!", this.width / 2,
                30, ColorHex.hexToInt(Color.WHITE));
        this.drawCenteredString(this.fontRenderer, "Before getting started, we have to setup the mod.", this.width / 2,
                60, ColorHex.hexToInt(Color.WHITE));

        this.drawString(fontRenderer, "Which textures do you want to use?", 10, 120, ColorHex.hexToInt(Color.WHITE));
        this.drawString(fontRenderer, "Override vanilla textures?", 10, 150, ColorHex.hexToInt(Color.WHITE));

        for (GuiButton button : this.buttonList) {
            if (button.isMouseOver()) {
                if (button.id == BTN_MODE) {
                    this.drawHoveringText(
                            Arrays.asList(
                                    "Choose which textures you want to see.",
                                    "",
                                    "Original: Use the original Minecraft textures.",
                                    "Note: You need to have Minecraft 1.21.10 installed on your system.",
                                    "Setting up the mod may take some time as it has to copy the textures from the Minecraft installation.",
                                    "",
                                    "Ultimate Backport: Use the Ultimate Backport textures."
                            ),
                            mouseX, mouseY);
                } else if (button.id == BTN_REPLACE_VANILLA) {
                    this.drawHoveringText(
                            Arrays.asList(
                                    "Choose whether you want to override the vanilla textures.",
                                    "",
                                    "Yes: The mod will replace the vanilla textures with its own, depending on texture mode.",
                                    "No: The mod will only add its own textures, leaving the vanilla ones intact."
                            ),
                            mouseX, mouseY);
                }
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case BTN_MODE:
                if (button.displayString.equals("Use Original Textures")) {
                    button.displayString = "Use UBM textures";
                    textureMode = 1;
                } else {
                    button.displayString = "Use Original Textures";
                    textureMode = 0;
                }
                break;
            case BTN_REPLACE_VANILLA:
                if (button.displayString.equals("Yes")) {
                    button.displayString = "No";
                    replaceVanillaTextures = false;
                } else {
                    button.displayString = "Yes";
                    replaceVanillaTextures = true;
                }
                break;
            case BTN_DONE:
                if (textureMode == 1) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiVersionSelection());
                } else {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
                }
                break;
        }
    }
}