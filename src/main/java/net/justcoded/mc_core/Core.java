package net.justcoded.mc_core;

import net.justcoded.mc_core.components.tasks.AsyncTask;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public final class Core extends JavaPlugin {
    Queue<Player> p = new LinkedList<>();
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
