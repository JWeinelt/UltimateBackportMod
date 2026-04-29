package de.julianweinelt.ubm.configuration;

import de.julianweinelt.ubm.UBM;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.Logger;

public class ModYamlConfig {
    private final Logger logger;
    private final File configFolder;
    private final File configFile;

    private final File serverConfigs;

    private YamlDocument config = null;

    private static ModYamlConfig instance;

    private ModYamlConfig(File configFolder) {
        logger = UBM.getLogger();
        this.configFolder = new File(configFolder, "ubm");
        if (configFolder.mkdirs()) UBM.getLogger().debug("Created config folder");
        this.configFile = new File(configFolder, "ubm.yml");
        this.serverConfigs = new File(configFolder, "servers");
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
            InputStream iS = getClass().getResourceAsStream("config.yml");
            if (iS == null) throw new IOException("Default config file not found");
            config = YamlDocument.create(configFile, iS);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void save() {
        try {
            config.save();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void reload() {
        try {
            config.reload();
        } catch (IOException e) {
            logger.error(e);
        }
    }
    public String getConfigData() {
        return config.dump();
    }

    public void saveServerConfig(String server, String data) {
        try (FileWriter w = new FileWriter(new File(serverConfigs, server + ".yml"))) {
            w.write(data);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void applyData(String server) {
        try {
            config = YamlDocument.create(new File(serverConfigs, server + ".yml"));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static YamlDocument config() {
        return instance().config;
    }

    public static float entityHealth(String entityName) {
        return config().getFloat("entities." + entityName + ".health");
    }

}
