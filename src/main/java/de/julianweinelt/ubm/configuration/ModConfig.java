package de.julianweinelt.ubm.configuration;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.configuration.section.ConfigSectionEntity;
import net.minecraftforge.common.config.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static Configuration config;

    private static final Map<String, ConfigSectionEntity> entities = new HashMap<>();

    public static void init(File file) {
        config = new Configuration(file);
        sync();
    }

    public static void sync() {
        try {
            config.load();
            int entLoaded = 0;
            for (String category : config.getCategoryNames()) {
                if (category.startsWith("entities.")) {
                    String entityName = category.substring("entities.".length());
                    ConfigSectionEntity section = new ConfigSectionEntity(config, entityName);
                    entities.put(entityName, section);
                    entLoaded++;
                }
            }
            UBM.getLogger().info("Loaded configuration data for {} entities", entLoaded);

        } catch (Exception e) {
            UBM.getLogger().error("Failed to load configuration file!", e);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    @Nonnull
    public static ConfigSectionEntity getEntityConfig(String entityName) {
        return entities.getOrDefault(entityName, new ConfigSectionEntity(entityName));
    }
}