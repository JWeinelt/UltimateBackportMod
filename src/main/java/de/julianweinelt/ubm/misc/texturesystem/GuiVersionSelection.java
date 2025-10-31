package de.julianweinelt.ubm.misc.texturesystem;

import de.julianweinelt.ubm.util.ColorHex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class GuiVersionSelection extends GuiScreen {
    private static final int DPN_MC_VERSION = 0;
    private static final int BTN_START = 1;

    private boolean extracting = false;
    private GuiProgressBar progressBar;
    private float progress = 0.0F;

    private GuiDropdownDeluxe dropdown;

    @Override
    public void initGui() {
        dropdown = new GuiDropdownDeluxe(DPN_MC_VERSION, 150, 60, 100, 20, MinecraftVersionFinder.getInstalledVersions());
        this.buttonList.clear();
        this.buttonList.add(dropdown);
        this.buttonList.add(new GuiButton(BTN_START, this.width / 2 - 40, this.height - 100, 80, 20,
                "Start Installation"));
        progressBar = new GuiProgressBar(this.width / 2, this.height - 60, this.width / 4, 20);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Select your installation settings.", this.width / 2,
                30, ColorHex.hexToInt(Color.WHITE));

        this.drawString(fontRenderer, "Minecraft Installation: ", 10, 60, ColorHex.hexToInt(Color.WHITE));

        if (extracting) {
            progressBar.setProgress(progress);
            progressBar.setLabel("Extracting Minecraft assets");
            progressBar.draw(mc);
        }

        for (GuiButton button : this.buttonList) {
            if (button.isMouseOver()) {
                if (button.id == DPN_MC_VERSION) {
                    this.drawHoveringText(
                            Arrays.asList(
                                    "Note: If you install the assets from an older version,",
                                    "UBM will use it's own assets for everything that doesn't exist",
                                    "in that version."
                            ),
                            mouseX, mouseY);
                }
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == BTN_START) {
            button.visible = false;
            startExtraction(dropdown.getSelectedOption()).thenAccept(success -> {

            });
        }
    }

    private CompletableFuture<Boolean> startExtraction(String versionName) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        extracting = true;
        new Thread(() -> {
            try {
                File target = new File(Minecraft.getMinecraft().mcDataDir, "config/ubm/extracted");
                long total = MinecraftJarExtractor.countEntries(versionName);
                long processed = 0;

                processed = MinecraftJarExtractor.extractWithProgress(versionName, target, p -> progress = (float) p / total);

                System.out.println("Extraction finished!");
                future.complete(true);
            } catch (Exception e) {
                e.printStackTrace();
                future.complete(false);
            } finally {
                extracting = false;
            }
        }, "UBM-Extractor").start();
        return future;
    }
}
