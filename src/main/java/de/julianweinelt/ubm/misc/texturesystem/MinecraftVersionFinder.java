package de.julianweinelt.ubm.misc.texturesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MinecraftVersionFinder {

    public static List<String> getInstalledVersions() {
        File versionsDir = getVersionsDir();

        List<String> versions = new ArrayList<>();

        File[] dirs = versionsDir.listFiles(File::isDirectory);
        if (dirs == null) return versions;

        for (File dir : dirs) {
            File versionJson = new File(dir, dir.getName() + ".json");
            if (versionJson.exists()) {
                versions.add(dir.getName());
            }
        }

        return versions;
    }

    private static File getVersionsDir() {
        String os = System.getProperty("os.name").toLowerCase();
        File baseDir;

        if (os.contains("win")) {
            baseDir = new File(System.getenv("APPDATA"), ".minecraft");
        } else if (os.contains("mac")) {
            baseDir = new File(System.getProperty("user.home"),
                    "Library/Application Support/minecraft");
        } else {
            baseDir = new File(System.getProperty("user.home"), ".minecraft");
        }

        return new File(baseDir, "versions");
    }
}
