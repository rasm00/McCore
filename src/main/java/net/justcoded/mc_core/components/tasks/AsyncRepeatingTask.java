package net.justcoded.mc_core.components.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AsyncRepeatingTask<T extends JavaPlugin> extends AsyncDelayedTask<T>{

    protected final int period;

    public AsyncRepeatingTask(T main, int delayed, int period) {
        super(main, delayed);
        this.period = period;
    }

    @Override
    public int task() {
        return Bukkit.getScheduler()
                .runTaskTimerAsynchronously(super.main, super.task, this.delayed, this.period).getTaskId();
    }
}
