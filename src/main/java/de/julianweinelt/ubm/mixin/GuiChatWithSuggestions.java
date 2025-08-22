package de.julianweinelt.ubm.mixin;

import de.julianweinelt.ubm.UBM;
import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.*;

public class GuiChatWithSuggestions extends GuiChat {
    private int suggestionIndex = 0;
    private List<String> currentSuggestions = new ArrayList<>();

    @Override
    public void initGui() {
        super.initGui();
        this.inputField.setFocused(true); //TODO: Text is being reset after window resize (Minecraft Bug)
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        updateSuggestions();
        if (keyCode == Keyboard.KEY_DOWN) {
            suggestionIndex = Math.max(0, suggestionIndex - 1);
            UBM.getLogger().info(suggestionIndex + "");
            return;
        } else if (keyCode == Keyboard.KEY_UP) {
            int newIdx = Math.min(currentSuggestions.size() - 1, suggestionIndex + 1);
            UBM.getLogger().info(newIdx + " (new)");
            suggestionIndex = newIdx;
            UBM.getLogger().info(suggestionIndex + "");
            return;
        } else if (keyCode == Keyboard.KEY_TAB && !currentSuggestions.isEmpty()) {
            String base = getBaseCommand();
            UBM.getLogger().info(suggestionIndex + "");
            this.inputField.setText("/" + base + " " + currentSuggestions.get(suggestionIndex));
            return;
        }

        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.inputField.getText().startsWith("/")) {
            this.inputField.setTextColor(0xA8A8A8);
        } else {
            this.inputField.setTextColor(0xFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);

        int x = this.inputField.x;
        int y = this.inputField.y - 4;
        int lineHeight = this.fontRenderer.FONT_HEIGHT + 2;
        int width = 0;

        for (String s : currentSuggestions) {
            int w = this.fontRenderer.getStringWidth(s);
            if (w > width) width = w;
        }
        width += 6;

        int height = currentSuggestions.size() * lineHeight;

        String suggestion = (suggestionIndex > currentSuggestions.size() - 1) ? "" : this.currentSuggestions.get(suggestionIndex);
        int cursorPos = this.inputField.getCursorPosition();
        String textBeforeCursor = this.inputField.getText().substring(0, cursorPos);
        String latestArg = textBeforeCursor.split(" ")[textBeforeCursor.split(" ").length - 1];
        int caretX = this.inputField.x + this.fontRenderer.getStringWidth(textBeforeCursor);
        int caretY = this.inputField.y;

        // clear
        // cl
        this.fontRenderer.drawString(suggestion.substring((latestArg.length() > suggestion.length()) ? 0 :
                latestArg.length()), caretX, caretY, 0x545454);

        drawRect(caretX - 2, y - height, caretX + width, y, 0xEE000000);

        for (int i = 0; i < currentSuggestions.size(); i++) {
            int color = (i == suggestionIndex) ? 0xFBFB54 : 0xA8A8A8;
            this.fontRenderer.drawStringWithShadow(
                    currentSuggestions.get(i),
                    caretX,
                    y - (i + 1) * lineHeight + 1,
                    color
            );
        }
    }


    private void updateSuggestions() {
        String text = this.inputField.getText();
        if (text.startsWith("/")) {
            String[] parts = text.substring(1).split(" ");
            String base = parts[0];
            String currentArg = (parts.length != 0) ? parts[parts.length - 1] : "";

            Map<String, List<String>> map = new HashMap<>();
            map.put("weather", Arrays.asList("thunder", "rain", "clear"));

            List<String> newSuggestions = map.getOrDefault(base, Collections.emptyList());

            if (!newSuggestions.equals(this.currentSuggestions)) {
                this.currentSuggestions = new ArrayList<>(newSuggestions);
                this.suggestionIndex = currentSuggestions.size() - 1;
            }
        } else {
            this.currentSuggestions = new ArrayList<>();
            this.suggestionIndex = 0;
        }
    }


    private String getBaseCommand() {
        String text = this.inputField.getText();
        if (text.startsWith("/")) {
            String[] parts = text.substring(1).split(" ");
            return parts[0];
        }
        return "";
    }
}
