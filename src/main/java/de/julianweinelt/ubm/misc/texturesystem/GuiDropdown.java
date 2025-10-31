package de.julianweinelt.ubm.misc.texturesystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.util.List;

public class GuiDropdown extends GuiButton {
    private boolean open = false;
    private final List<String> options;
    private int selectedIndex = 0;

    public GuiDropdown(int buttonId, int x, int y, int widthIn, int heightIn, List<String> options) {
        super(buttonId, x, y, widthIn, heightIn, options.get(0));
        this.options = options;
    }
    public GuiDropdown(int buttonId, int x, int y, int widthIn, int heightIn, String display, List<String> options) {
        super(buttonId, x, y, widthIn, heightIn, display);
        this.options = options;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!this.visible) return;

        this.hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
        int color = hovered ? 0xFF666666 : 0xFF444444;
        drawRect(x, y, x + width, y + height, color);
        this.drawCenteredString(mc.fontRenderer, this.displayString, x + width / 2, y + (height - 8) / 2, 0xFFFFFF);

        if (open) {
            int optionY = y + height;
            for (int i = 0; i < options.size(); i++) {
                String option = options.get(i);
                int optColor = 0xFF222222;
                boolean hoveredOpt = mouseX >= x && mouseY >= optionY && mouseX < x + width && mouseY < optionY + height;

                if (hoveredOpt) optColor = 0xFF555555;

                drawRect(x, optionY, x + width, optionY + height, optColor);
                mc.fontRenderer.drawString(option, x + 5, optionY + (height - 8) / 2, 0xFFFFFF);

                optionY += height;
            }
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (!visible) return false;

        if (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height) {
            open = !open;
            return true;
        }

        if (open) {
            int optionY = y + height;
            for (int i = 0; i < options.size(); i++) {
                if (mouseX >= x && mouseY >= optionY && mouseX < x + width && mouseY < optionY + height) {
                    selectedIndex = i;
                    displayString = options.get(i);
                    open = false;
                    return true;
                }
                optionY += height;
            }
        }

        return false;
    }

    public String getSelectedOption() {
        return options.get(selectedIndex);
    }
}
