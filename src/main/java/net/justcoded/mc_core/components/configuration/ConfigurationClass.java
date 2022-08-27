package net.justcoded.mc_core.components.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.Serializable;

public abstract class ConfigurationClass implements Serializable {

    private final Plugin main;
    private final YamlConfiguration yamlConfiguration;
    protected FileConfiguration config;

    public ConfigurationClass(String name, Plugin main) {
        this.main = main;
        yamlConfiguration = new YamlConfiguration(main, name);
        reload();
    }

    public void reload() {
        yamlConfiguration.createConfig();
        yamlConfiguration.reload();
        config = yamlConfiguration.getConfig();
    }

    public abstract ConfigurationClass injectData();

    public FileConfiguration getConfig() {
        return config;
    }
}
