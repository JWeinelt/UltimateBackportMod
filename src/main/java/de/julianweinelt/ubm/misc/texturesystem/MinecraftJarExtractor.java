package de.julianweinelt.ubm.misc.texturesystem;

import java.io.*;
import java.nio.file.*;
import java.util.function.LongConsumer;
import java.util.zip.*;

public class MinecraftJarExtractor {

    public static void extractMinecraftVersion(String versionName, File targetDir) throws IOException {
        File versionJar = new File(getVersionsDir(), versionName + "/" + versionName + ".jar");
        if (!versionJar.exists()) {
            throw new FileNotFoundException("Version not found: " + versionJar.getAbsolutePath());
        }

        System.out.println("Extracting from: " + versionJar);

        try (ZipInputStream zip = new ZipInputStream(Files.newInputStream(versionJar.toPath()))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (!entry.getName().startsWith("assets/minecraft/")) continue;

                File outFile = new File(targetDir, entry.getName());
                if (entry.isDirectory()) {
                    outFile.mkdirs();
                    continue;
                }

                outFile.getParentFile().mkdirs();
                try (FileOutputStream out = new FileOutputStream(outFile)) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zip.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                }
            }
        }

        System.out.println("Extraction complete → " + targetDir.getAbsolutePath());
    }

    private static File getVersionsDir() {
        String os = System.getProperty("os.name").toLowerCase();
        File baseDir;

        if (os.contains("win")) {
            baseDir = new File(System.getenv("APPDATA"), ".minecraft");
        } else if (os.contains("mac")) {
            baseDir = new File(System.getProperty("user.home"), "Library/Application Support/minecraft");
        } else {
            baseDir = new File(System.getProperty("user.home"), ".minecraft");
        }

        return new File(baseDir, "versions");
    }

    public static long countEntries(String versionName) throws IOException {
        File versionJar = new File(getVersionsDir(), versionName + "/" + versionName + ".jar");
        long count = 0;
        try (ZipInputStream zip = new ZipInputStream(Files.newInputStream(versionJar.toPath()))) {
            while (zip.getNextEntry() != null) count++;
        }
        return count;
    }

    public static long extractWithProgress(String versionName, File targetDir, LongConsumer progressCallback) throws IOException {
        File versionJar = new File(getVersionsDir(), versionName + "/" + versionName + ".jar");
        long processed = 0;

        try (ZipInputStream zip = new ZipInputStream(Files.newInputStream(versionJar.toPath()))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (!entry.getName().startsWith("assets/minecraft/")) continue;
                File outFile = new File(targetDir, entry.getName());
                if (entry.isDirectory()) {
                    outFile.mkdirs();
                    continue;
                }
                outFile.getParentFile().mkdirs();
                try (FileOutputStream out = new FileOutputStream(outFile)) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zip.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                }
                processed++;
                progressCallback.accept(processed);
            }
        }
        return processed;
    }
}
