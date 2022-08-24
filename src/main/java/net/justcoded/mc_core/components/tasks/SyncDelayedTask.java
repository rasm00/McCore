package net.justcoded.mc_core.components.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class SyncDelayedTask<T extends JavaPlugin> extends SyncTask<T>{

    protected final int delayed;

    public SyncDelayedTask(T main, int delayed) {
        super(main);
        this.delayed = delayed;
    }

    @Override
    public BukkitTask task() {
        return new TaskRunnable(this::runCurrentTask)
                .runTaskLater(super.main, this.delayed);
    }
}
