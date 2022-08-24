package net.justcoded.mc_core.components.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class SyncTask<T extends JavaPlugin> extends Task<T>{
    public SyncTask(T main) {
        super(main);
    }

    @Override
    public BukkitTask task() {
        return Bukkit.getScheduler()
                .runTask(super.main, super.taskRunnable);
    }
}
