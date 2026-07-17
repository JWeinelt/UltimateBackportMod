package de.julianweinelt.ubm.configuration;

import de.julianweinelt.ubm.UBM;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class ModYamlConfig {
    private final Logger logger;
    private final File configFolder;
    private final File configFile;

    private final File serverConfigs;

    private Map<String, Object> config = null;

    private static ModYamlConfig instance;
    private final Yaml yaml = new Yaml();

    private ModYamlConfig(File configFolder) {
        logger = UBM.getLogger();
        this.configFolder = new File(configFolder, "ubm");
        if (this.configFolder.mkdirs()) UBM.getLogger().debug("Created config folder");
        this.configFile = new File(this.configFolder, "ubm.yml");
        this.serverConfigs = new File(this.configFolder, "servers");
        if (serverConfigs.mkdirs()) UBM.getLogger().debug("Created server configs folder");
        instance = this;

        logger.info("Loading config");
        load();
    }

    public static ModYamlConfig instance() {
        return instance;
    }

    public static void init(File configFolder) {
        new ModYamlConfig(configFolder);
    }


    public void load() {
        try {
            if (!configFile.exists()) {
                InputStream input = getClass()
                        .getClassLoader()
                        .getResourceAsStream("config.yml");

                if (input == null) {
                    throw new IOException("Default config.yml not found");
                }

                try (FileOutputStream output = new FileOutputStream(configFile)) {
                    byte[] buffer = new byte[4096];
                    int length;

                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                }
            }

            try (FileInputStream input = new FileInputStream(configFile)) {
                config = yaml.load(input);
            }

        } catch (IOException e) {
            logger.error("Could not load config", e);
        }
    }



    public void save() {
        try (FileWriter writer = new FileWriter(configFile)) {
            yaml.dump(config, writer);
        } catch (IOException e) {
            logger.error("Could not save config", e);
        }
    }

    public void reload() {
        load();
    }
    public String getConfigData() {
        return config.toString();
    }

    public void saveServerConfig(String server, String data) {
        try (FileWriter w = new FileWriter(new File(serverConfigs, server + ".yml"))) {
            w.write(data);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static Map<String, Object> config() {
        return instance().config;
    }

    public static float entityHealth(String entityName) {
        Object value = instance().config
                .get("entities");

        if (!(value instanceof Map)) {
            return 20.0F;
        }

        Map<?, ?> entities = (Map<?, ?>) value;
        Object entity = entities.get(entityName);

        if (!(entity instanceof Map)) {
            return 20.0F;
        }

        Object health = ((Map<?, ?>) entity).get("health");

        if (health instanceof Number) {
            return ((Number) health).floatValue();
        }

        return 20.0F;
    }

    @SuppressWarnings("unchecked")
    public Object get(String path) {
        if (config == null || path == null || path.isEmpty()) {
            return null;
        }

        String[] keys = path.split("\\.");
        Object current = config;

        for (String key : keys) {
            if (!(current instanceof Map)) {
                return null;
            }

            current = ((Map<String, Object>) current).get(key);
        }

        return current;
    }

    @SuppressWarnings("unchecked")
    public void set(String path, Object value) {
        if (config == null || path == null || path.isEmpty()) {
            return;
        }

        String[] keys = path.split("\\.");
        Map<String, Object> current = config;

        for (int i = 0; i < keys.length - 1; i++) {
            Object next = current.get(keys[i]);

            if (!(next instanceof Map)) {
                next = new LinkedHashMap<String, Object>();
                current.put(keys[i], next);
            }

            current = (Map<String, Object>) next;
        }

        current.put(keys[keys.length - 1], value);
    }
}