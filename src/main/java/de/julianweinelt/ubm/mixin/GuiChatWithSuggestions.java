package de.julianweinelt.ubm.mixin;

import de.julianweinelt.pathfinder.PathFinderAPI;
import de.julianweinelt.pathfinder.command.Argument;
import de.julianweinelt.pathfinder.command.Command;
import de.julianweinelt.ubm.UBM;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
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
            if (inputField.getText().split(" ").length > 1)
                this.inputField.setText("/" + base + " " + currentSuggestions.get(suggestionIndex));
            else this.inputField.setText("/" + currentSuggestions.get(suggestionIndex));
            return;
        }

        super.keyTyped(typedChar, keyCode);
        updateSuggestions();
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

        String suggestion = (suggestionIndex > currentSuggestions.size() - 1 || suggestionIndex < 0)
                ? "" : this.currentSuggestions.get(suggestionIndex);
        int cursorPos = this.inputField.getCursorPosition();
        String textBeforeCursor = this.inputField.getText().substring(0, cursorPos);
        String latestArg = textBeforeCursor.split(" ")[textBeforeCursor.split(" ").length - 1];
        int caretX = this.inputField.x + this.fontRenderer.getStringWidth(textBeforeCursor);
        int caretY = this.inputField.y;

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
        if (!text.startsWith("/")) {
            this.currentSuggestions = new ArrayList<>();
            this.suggestionIndex = 0;
            return;
        }

        String[] parts = text.substring(1).split(" ");
        String base = parts[0];

        boolean newArg = text.endsWith(" ");

        String currentArg;
        if (newArg && parts.length > 1) {
            currentArg = "";
        } else {
            currentArg = parts[parts.length - 1];
        }

        List<String> newSuggestions;
        if (parts.length == 1 && !newArg) {
            newSuggestions =  getCommands(base);
        } else {
            newSuggestions = getLastArgSuggestions(base, currentArg, text);
        }

        if (!newSuggestions.equals(this.currentSuggestions)) {
            this.currentSuggestions = new ArrayList<>(newSuggestions);
            this.suggestionIndex = currentSuggestions.size() - 1;
        }
    }


    private List<String> getCommands() {
        List<String> commands = new ArrayList<>();
        for (String s : PathFinderAPI.commandRegistry.getCommandMap().keySet()) {
            for (Command c : PathFinderAPI.commandRegistry.getCommandMap().get(s)) commands.add(c.getName());
        }
        commands.sort(Collections.reverseOrder());
        return commands;
    }

    private List<String> getCommands(String input) {
        List<String> commands = new ArrayList<>();
        for (String s : PathFinderAPI.commandRegistry.getCommandMap().keySet()) {
            for (Command c : PathFinderAPI.commandRegistry.getCommandMap().get(s)) if (c.getName().startsWith(input)) commands.add(c.getName());
        }
        commands.sort(Collections.reverseOrder());
        return commands;
    }

    private List<String> getLastArgSuggestions(String command, String input, String fullText) {
        Command cmd = PathFinderAPI.getCommandRegistry().getCommand("minecraft", command);
        if (cmd == null) {
            UBM.getLogger().warn("CMD is null");
            return new ArrayList<>();
        }

        String[] args = fullText.substring(1).split(" ");
        List<Argument> availableArgs = cmd.getArgs();
        Argument current = null;


        for (int i = 1; i < args.length; i++) {
            String argInput = args[i];
            Argument match = null;

            for (Argument candidate : availableArgs) {
                if (candidate == null) continue;

                if (argInput.isEmpty() || candidate.matches(argInput)) {
                    match = candidate;
                    break;
                }
            }

            if (match == null) break;

            current = match;
            availableArgs = current.getArgs();
        }

        List<String> suggestions = new ArrayList<>();

        if (current != null) {
            if (current.getSuggestions() != null) {
                for (String s : current.getSuggestions()) {
                    if (s.toLowerCase().startsWith(input.toLowerCase())) suggestions.add(s);
                }
            }
        }

        for (Argument candidate : availableArgs) {
            if (candidate.getSuggestions() != null) {
                for (String suggestion : candidate.getSuggestions()) {
                    if (suggestion.toLowerCase().startsWith(input.toLowerCase())) {
                        suggestions.add(suggestion);
                    }
                }
            }
            suggestions.add(candidate.getName());
            if (candidate.getSuggestion_type() != null) {
                if (!Objects.equals(candidate.getSuggestion_type(), "list") && !Objects.equals(candidate.getSuggestion_type(), "custom")) {
                    suggestions.addAll(PathFinderAPI.getSuggestionTypeEntries(candidate.getSuggestion_type(), null));
                }
            }
        }

        Collections.sort(suggestions);
        return suggestions;
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