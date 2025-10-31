package de.julianweinelt.ubm.misc.texturesystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class GuiProgressBar extends Gui {
    private final int x, y, width, height;
    private float progress = 0.0F;
    private final int colorBackground;
    private final int colorForeground;
    private String label = "";

    public GuiProgressBar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colorBackground = 0xFF222222;
        colorForeground = 0xFFFFA500;
    }

    public GuiProgressBar(int x, int y, int width, int height, int colorBackground, int colorForeground) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorBackground = colorBackground;
        this.colorForeground = colorForeground;
    }

    public void setProgress(float progress) {
        this.progress = Math.max(0, Math.min(progress, 1));
    }

    public void setLabel(String text) {
        this.label = text;
    }

    public void draw(Minecraft mc) {
        drawRect(x, y, x + width, y + height, colorBackground);

        int filled = (int) (width * progress);
        drawRect(x, y, x + filled, y + height, colorForeground);

        String text = (int) (progress * 100) + "% " + label;
        int textX = x + (width / 2) - mc.fontRenderer.getStringWidth(text) / 2;
        int textY = y + (height - 8) / 2;
        mc.fontRenderer.drawString(text, textX, textY, 0xFFFFFFFF);
    }
}
