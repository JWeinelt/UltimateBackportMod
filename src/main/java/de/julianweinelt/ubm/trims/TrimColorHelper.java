package de.julianweinelt.ubm.trims;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TrimColorHelper {

    /**
     * Wandelt eine Graustufen-Overlay-Textur in eine gefärbte Textur anhand einer Palette um.
     *
     * @param overlayPath Pfad zur Graustufen-Overlay-Textur in resources (z.B. "textures/equipment/trims/overlay.png")
     * @param palette     Farbpalette (RGBA)
     * @return ResourceLocation der fertigen gefärbten Textur
     */
    public static ResourceLocation createColoredTrim(String overlayPath, Color[] palette) {
        try {
            BufferedImage overlay = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("ubm", overlayPath)).getInputStream());

            BufferedImage colored = new BufferedImage(overlay.getWidth(), overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < overlay.getWidth(); x++) {
                for (int y = 0; y < overlay.getHeight(); y++) {
                    int gray = new Color(overlay.getRGB(x, y), true).getRed(); // Grauwert
                    int index = Math.round((gray / 255f) * (palette.length - 1));
                    Color c = palette[index];
                    int alpha = new Color(overlay.getRGB(x, y), true).getAlpha(); // Alpha behalten
                    int rgb = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha).getRGB();
                    colored.setRGB(x, y, rgb);
                }
            }

            DynamicTexture dynTex = new DynamicTexture(colored);
            return Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("trim_colored", dynTex);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
