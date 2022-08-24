package net.justcoded.mc_core.components.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SyncRepeatingTask<T extends JavaPlugin> extends SyncDelayedTask<T>{

    private final int period;

    public SyncRepeatingTask(T main, int delayed, int period) {
        super(main, delayed);
        this.period = period;
    }

    @Override
    public int task() {
        return Bukkit.getScheduler()
                .runTaskTimer(super.main, super.task, this.delayed, period).getTaskId();
    }
}
