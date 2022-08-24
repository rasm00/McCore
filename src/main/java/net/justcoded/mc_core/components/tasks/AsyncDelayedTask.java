package net.justcoded.mc_core.components.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class AsyncDelayedTask<T extends JavaPlugin> extends AsyncTask<T>{

    protected final int delayed;

    public AsyncDelayedTask(T main, int delayed) {
        super(main);
        this.delayed = delayed;
    }

    @Override
    public BukkitTask task() {
        return new TaskRunnable(this::runCurrentTask)
                .runTaskLaterAsynchronously(super.main, this.delayed);
    }
}
