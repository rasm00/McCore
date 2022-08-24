package net.justcoded.mc_core.components.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SyncRepeatingTask<T extends JavaPlugin> extends SyncDelayedTask<T>{

    private final int period;

    public SyncRepeatingTask(T main, int delayed, int period) {
        super(main, delayed);
        this.period = period;
    }

    @Override
    public BukkitTask task() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                runCurrentTask();
            }
        }.runTaskTimer(super.main, this.delayed, this.period);
    }
}
