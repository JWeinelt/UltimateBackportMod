package de.julianweinelt.ubm.util;

import java.awt.Color;
import java.util.Locale;

public final class ColorHex {

    private ColorHex() {}


    public static String rgbToHex(int r, int g, int b) {
        return rgbToHex(r, g, b, true, true);
    }

    public static String rgbToHex(int r, int g, int b, boolean withHash, boolean upperCase) {
        int rr = clamp8(r), gg = clamp8(g), bb = clamp8(b);
        String hex = String.format(Locale.ROOT, "%02X%02X%02X", rr, gg, bb);
        if (!upperCase) hex = hex.toLowerCase(Locale.ROOT);
        return withHash ? "#" + hex : hex;
    }


    public static String argbToHex(int a, int r, int g, int b) {
        return argbToHex(a, r, g, b, true, true);
    }


    public static String argbToHex(int a, int r, int g, int b, boolean withHash, boolean upperCase) {
        int aa = clamp8(a), rr = clamp8(r), gg = clamp8(g), bb = clamp8(b);
        String hex = String.format(Locale.ROOT, "%02X%02X%02X%02X", aa, rr, gg, bb);
        if (!upperCase) hex = hex.toLowerCase(Locale.ROOT);
        return withHash ? "#" + hex : hex;
    }
    public static String rgbIntToHex(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return rgbToHex(r, g, b);
    }
    public static String argbIntToHex(int argb) {
        int a = (argb >> 24) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = argb & 0xFF;
        return argbToHex(a, r, g, b);
    }

    public static String toHex(Color color) {
        return rgbToHex(color.getRed(), color.getGreen(), color.getBlue());
    }


    public static int hexToInt(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("HEX string darf nicht null sein.");
        }

        hex = hex.trim();
        if (hex.startsWith("#")) hex = hex.substring(1);

        if (hex.length() != 6 && hex.length() != 8) {
            throw new IllegalArgumentException("HEX muss 6 oder 8 Stellen haben: " + hex);
        }

        try {
            long value = Long.parseLong(hex, 16);
            return (int) value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ungültiger HEX-String: " + hex, e);
        }
    }

    public static int hexToInt(Color c) {
        return hexToInt(toHex(c));
    }

    private static int clamp8(int x) {
        if (x < 0) return 0;
        if (x > 255) return 255;
        return x;
    }
}
