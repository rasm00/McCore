package net.justcoded.mc_core.components.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class AsyncTask<T extends JavaPlugin> extends Task<T> {

    public AsyncTask(T main) {
        super(main);
    }

    @Override
    public BukkitTask task() {
        return new TaskRunnable(this::runCurrentTask)
                .runTaskAsynchronously(super.main);
    }
}
