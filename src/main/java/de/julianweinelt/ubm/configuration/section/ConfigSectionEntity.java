package de.julianweinelt.ubm.configuration.section;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.*;

public class ConfigSectionEntity {
    private final Configuration config;
    private final String entity;

    private final Map<String, Object> values = new HashMap<>();

    private boolean enabled;
    private int spawnWeight;
    private int health;
    private int damage = 0;

    private final List<String> feedItems = new ArrayList<>();

    public ConfigSectionEntity(Configuration config, String entity) {
        this.config = config;
        this.entity = entity;
        load();
    }

    public ConfigSectionEntity(String entity) {
        this.config = null;
        this.entity = entity;
        enabled = false;
        spawnWeight = 0;
        health = 1;
    }

    private void load() {
        enabled = config.getBoolean("enabled", "entities." + entity, true, "");
        spawnWeight = config.getInt("spawnWeight", "entities." + entity, 0, 0, 100, "");
        health = config.getInt("health", "entities." + entity, 0, 1, Integer.MAX_VALUE, "");
        damage = config.getInt("damage", "entities." + entity, 0, 0, Integer.MAX_VALUE, "");
        feedItems.clear();
        feedItems.addAll(Arrays.asList(config.getStringList("feedItems", "entities." + entity, new String[]{}, "")));
        ConfigCategory cat = config.getCategory("entities." + entity);
        for (String s : cat.getValues().keySet()) {
            Property p = cat.get(s);
            if (p != null) {
                if (p.getType().equals(Property.Type.BOOLEAN)) values.put(s, p.getBoolean());
                if (p.getType().equals(Property.Type.STRING)) values.put(s, p.getString());
                if (p.getType().equals(Property.Type.DOUBLE)) values.put(s, p.getDouble());
                if (p.getType().equals(Property.Type.INTEGER)) values.put(s, p.getInt());
            }
        }
    }



    public int getAsInt(String key) {
        Object value = values.get(key);
        if (value instanceof Integer) return (Integer) value;
        return 0;
    }

    public boolean getAsBoolean(String key) {
        Object value = values.get(key);
        if (value instanceof Boolean) return (Boolean) value;
        return false;
    }

    public String getAsString(String key) {
        Object value = values.get(key);
        if (value instanceof String) return (String) value;
        return "";
    }

    public List<String> getAsStringList(String key) {
        Object value = values.get(key);
        if (value instanceof List<?>) {
            List<?> list = (List<?>) value;
            List<String> strings = new ArrayList<>();
            for (Object o : list) if (o instanceof String) strings.add((String) o);
            return strings;
        }
        return Collections.emptyList();
    }


    public Map<String, Object> getValues() {
        return values;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getSpawnWeight() {
        return spawnWeight;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public List<String> getFeedItems() {
        return feedItems;
    }

    public String getEntity() {
        return entity;
    }
}