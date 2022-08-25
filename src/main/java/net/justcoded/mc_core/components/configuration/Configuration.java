package net.justcoded.mc_core.components.configuration;

import net.justcoded.mc_core.components.configuration.updater.ConfigUpdater;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

class Configuration {
    private final Plugin plugin;
    private final String pluginName;
    private String fileName;
    private final File pluginDataFolder;
    private final File configuration;
    private FileConfiguration config;

    public Configuration(Plugin plugin, String fileName) {
        setName(fileName);
        this.plugin = plugin;
        this.pluginName = plugin.getName();
        this.pluginDataFolder = plugin.getDataFolder();
        this.configuration = getFile();
        this.config = YamlConfiguration.loadConfiguration(this.configuration);
        createConfig();
    }

    public Configuration(Plugin plugin, String fileName, boolean readOnly) {
        setName(fileName);
        this.plugin = plugin;
        this.pluginName = plugin.getName();
        this.pluginDataFolder = plugin.getDataFolder();
        this.configuration = getFile();
        if (!readOnly) {
            this.config = YamlConfiguration.loadConfiguration(this.configuration);
        }
        createConfig();
    }

    public void createConfig() {
        if (!this.configuration.exists()) {
            if (!this.pluginDataFolder.exists()) {
                this.pluginDataFolder.mkdir();
            }
            this.plugin.saveResource(this.fileName, false);
        }
    }

    public void updateConfig() {
        try {
            ConfigUpdater.update(this.plugin, this.fileName, this.configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setName(String name) {
        if (name.isBlank()) {
            throw new RuntimeException("Cannot create file with empty name.");
        }

        if (name.endsWith(".yml")) {
            this.fileName = name;
        } else {
            this.fileName = name + ".yml";
        }
    }

    private File getFile() {
        return new File(this.pluginDataFolder, this.fileName);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getConfiguration() {
        return this.configuration;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getPluginName() {
        return this.pluginName;
    }

    public void save() {
        try {
            this.config.save(this.configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.configuration);
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public Object getObject(String path) {
        return getConfig().get(path);
    }

    public Optional<ConfigurationSection> getSection(String path) {
        if (this.config == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.config.getConfigurationSection(path.endsWith(".")
                ? path.substring(0, path.length() - 1)
                : path));
    }

    public Optional<Set<String>> getSectionKeys(String path) {
        Optional<ConfigurationSection> optional = getSection(path);
        return optional.map(section -> section.getKeys(false));
    }

    public void useSectionKeys(String path, Consumer<Set<String>> consumer) {
        getSectionKeys(path).ifPresent(consumer);
    }

    public void useEachSectionKeys(String path, Consumer<String> consumer) {
        useSectionKeys(path, strings -> strings.forEach(consumer));
    }
}