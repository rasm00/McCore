package net.justcoded.mc_core.components.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class AsyncRepeatingTask<T extends JavaPlugin> extends AsyncDelayedTask<T>{

    protected final int period;

    public AsyncRepeatingTask(T main, int delayed, int period) {
        super(main, delayed);
        this.period = period;
    }

    @Override
    public BukkitTask task() {
        return new TaskRunnable(this::runCurrentTask)
                .runTaskTimerAsynchronously(super.main, this.delayed, this.period);
    }
}
