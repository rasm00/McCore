package net.justcoded.mc_core;

import net.justcoded.mc_core.components.tasks.AsyncTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new AsyncTask<>(this).createTask(null).runTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
