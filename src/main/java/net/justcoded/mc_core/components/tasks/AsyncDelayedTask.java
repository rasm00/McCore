package net.justcoded.mc_core.components.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AsyncDelayedTask<T extends JavaPlugin> extends AsyncTask<T>{

    protected final int delayed;

    public AsyncDelayedTask(T main, int delayed) {
        super(main);
        this.delayed = delayed;
    }

    @Override
    public int task() {
        return Bukkit.getScheduler()
                .runTaskLaterAsynchronously(super.main, super.task, this.delayed).getTaskId();
    }
}
