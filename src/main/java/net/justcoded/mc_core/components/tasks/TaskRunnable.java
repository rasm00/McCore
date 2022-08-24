package net.justcoded.mc_core.components.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class TaskRunnable extends BukkitRunnable {
    private final Runnable runnable;

    public TaskRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
