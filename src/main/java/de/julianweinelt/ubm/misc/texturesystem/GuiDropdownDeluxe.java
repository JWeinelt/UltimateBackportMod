package de.julianweinelt.ubm.misc.texturesystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Mouse;

import java.util.List;

public class GuiDropdownDeluxe extends GuiButton {
    private boolean open = false;
    private final List<String> options;
    private int selectedIndex = 0;

    private float animation = 0.0F;
    private float animationSpeed = 0.25F;
    private int scrollOffset = 0;
    private int visibleOptions = 5;

    public GuiDropdownDeluxe(int buttonId, int x, int y, int width, int height, List<String> options) {
        super(buttonId, x, y, width, height, options.get(0));
        this.options = options;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!this.visible) return;

        this.hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

        int bgColor = hovered ? 0xFF555555 : 0xFF333333;
        Gui.drawRect(x, y, x + width, y + height, bgColor);

        this.drawCenteredString(mc.fontRenderer, this.displayString, x + width / 2, y + (height - 8) / 2, 0xFFFFFF);
        mc.fontRenderer.drawString(open ? "▲" : "▼", x + width - 10, y + 6, 0xFFFFFF);

        if (open && animation < 1.0F) {
            animation += animationSpeed * partialTicks;
            if (animation > 1.0F) animation = 1.0F;
        } else if (!open && animation > 0.0F) {
            animation -= animationSpeed * partialTicks;
            if (animation < 0.0F) animation = 0.0F;
        }

        if (animation > 0.01F) {
            int maxVisible = Math.min(visibleOptions, options.size());
            int optionHeight = height;
            int visibleHeight = (int) (optionHeight * maxVisible * animation);
            Gui.drawRect(x, y + height, x + width, y + height + visibleHeight, 0xAA000000);

            int startIndex = scrollOffset;
            int endIndex = Math.min(startIndex + maxVisible, options.size());
            int optionY = y + height;

            for (int i = startIndex; i < endIndex; i++) {
                String option = options.get(i);
                boolean hoveredOpt = mouseX >= x && mouseY >= optionY && mouseX < x + width && mouseY < optionY + optionHeight;
                int color = hoveredOpt ? 0xFF666666 : 0xFF222222;

                Gui.drawRect(x + 1, optionY, x + width - 1, optionY + optionHeight, color);
                mc.fontRenderer.drawString(option, x + 5, optionY + (optionHeight - 8) / 2, 0xFFFFFF);

                optionY += optionHeight;
            }

            if (options.size() > visibleOptions) {
                int barHeight = (int) ((float) visibleOptions / options.size() * (visibleOptions * optionHeight));
                int barY = y + height + (int) ((float) scrollOffset / options.size() * (visibleOptions * optionHeight));
                Gui.drawRect(x + width - 6, barY, x + width - 3, barY + barHeight, 0xFFAAAAAA);
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
            for (int i = scrollOffset; i < Math.min(scrollOffset + visibleOptions, options.size()); i++) {
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

    //@Override
    public void handleMouseInput() {
        if (!open || options.size() <= visibleOptions) return;

        int scroll = Integer.signum(Mouse.getEventDWheel());
        if (scroll != 0) {
            scrollOffset -= scroll;
            if (scrollOffset < 0) scrollOffset = 0;
            if (scrollOffset > options.size() - visibleOptions)
                scrollOffset = options.size() - visibleOptions;
        }
    }

    public String getSelectedOption() {
        return options.get(selectedIndex);
    }

    public boolean isOpen() {
        return open;
    }
}
