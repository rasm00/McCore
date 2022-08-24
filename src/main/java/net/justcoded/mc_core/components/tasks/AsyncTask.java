package net.justcoded.mc_core.components.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AsyncTask<T extends JavaPlugin> extends Task<T> {

    public AsyncTask(T main) {
        super(main);
    }

    @Override
    public int task() {
        return Bukkit.getScheduler()
                .runTaskAsynchronously(super.main, super.task).getTaskId();
    }
}
