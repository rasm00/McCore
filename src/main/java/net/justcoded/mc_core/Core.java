package net.justcoded.mc_core;

import net.justcoded.mc_core.components.tasks.AsyncTask;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.Queue;

public final class Core extends JavaPlugin {
    Queue<Player> p = new LinkedList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        new AsyncTask<>(this).createTask(() -> {}).runTask();

        new AsyncTask<>(this).createTask((task) -> {}).runTask();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
