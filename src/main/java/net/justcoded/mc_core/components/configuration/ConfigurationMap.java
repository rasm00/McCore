package net.justcoded.mc_core.components.configuration;

import net.justcoded.mc_core.components.collections.CMap;
import org.bukkit.plugin.Plugin;

public class ConfigurationMap {
    private final YamlConfiguration yamlConfiguration;
    private final CMap<String, String> map;

    public ConfigurationMap(Plugin plugin, String file) {
        map = new CMap<>();
        yamlConfiguration = new YamlConfiguration(plugin, file);
    }

    public ConfigurationMap(Plugin plugin, String file, boolean readOnly) {
        map = new CMap<>();
        yamlConfiguration = new YamlConfiguration(plugin, file, readOnly);
    }

    public YamlConfiguration getConfiguration() {
        return yamlConfiguration;
    }

    public CMap<String, String> getMap() {
        return map;
    }

    public String getValue(String path) {
        if (map.containsKey(path)) {
            return map.getMap().get(path);
        }

        Object object = getConfiguration().getObject(path);
        if (object == null) {
            return path;
        }

        if (object instanceof String str) {
            map.set(path, str);
            return str;
        }

        return object.toString();
    }

    //TODO add format message
    // public String getFormattedValue(String path) {
    //     return formatMessage(getValue(path));
    // }
}
