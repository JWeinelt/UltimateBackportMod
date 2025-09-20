package de.julianweinelt.ubm.trims;

import de.julianweinelt.ubm.UBM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TrimColorHelper {
    private static final HashMap<String, ResourceLocation> textureCache = new HashMap<>();

    public static ResourceLocation createColoredTrim(ResourceLocation location, Color[] palette) {
        String path = location.getResourcePath();
        return createColoredTrim(path, palette);
    }

    public static ResourceLocation createColoredTrim(String overlayPath, Color[] palette) {
        String cacheKey = overlayPath + "-" + System.identityHashCode(palette);
        if (textureCache.containsKey(cacheKey)) {
            return textureCache.get(cacheKey);
        }
        HashMap<Integer, Integer> paletteIndex = extractGrayLevels(overlayPath);

        try {
            BufferedImage overlay = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("ubm", overlayPath)).getInputStream());

            BufferedImage colored = new BufferedImage(overlay.getWidth(), overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int x = 0; x < overlay.getWidth(); x++) {
                for (int y = 0; y < overlay.getHeight(); y++) {
                    int rgb = overlay.getRGB(x, y);

                    if (new Color(rgb).equals(Color.BLACK)) {
                        colored.setRGB(x, y, 0x00000000);
                        continue;
                    }
                    Color color;
                    if (paletteIndex.size() == 8) color = palette[palette.length - 1 - paletteIndex.get(rgb)];
                    else color = palette[paletteIndex.get(rgb)];
                    //Color inverted = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), 255);
                    colored.setRGB(x, y, color.getRGB());
                }
            }

            DynamicTexture dynTex = new DynamicTexture(colored);
            ResourceLocation location = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("trim_colored", dynTex);
            textureCache.put(cacheKey, location);
            try {
                File outFile = new File(UBM.config, overlayPath.contains("legg") ? "leg_" : "ot" + "test.png");
                ImageIO.write(colored, "PNG", outFile);
                System.out.println("Textur gespeichert: " + outFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return location;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static HashMap<Integer, Integer> extractGrayLevels(String texturePath) {
        HashMap<Integer, Integer> grayLevels = new HashMap<>();
        try {
            BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("ubm", texturePath)).getInputStream());

            int currentLevel = 0;

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int argb = image.getRGB(x, y);
                    int alpha = (argb >> 24) & 0xFF;
                    if (alpha == 0) continue;

                    if (!grayLevels.containsKey(argb)) {
                        grayLevels.put(argb, currentLevel);
                        currentLevel++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return grayLevels;
    }

    public static Color[] extractPalette(String texturePath) {
        Color[] palette = new Color[8];
        try {
            BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("ubm", texturePath)).getInputStream());

            for (int x = 0; x < image.getWidth(); x++) {
                int argb = image.getRGB(x, 0);
                int alpha = (argb >> 24) & 0xFF;
                if (alpha == 0) continue;

                if (x >= 8) break;
                palette[x] = new Color(argb);
            }


        } catch (IOException e) {
            e.printStackTrace();
            return palette;
        }

        return palette;
    }
}
