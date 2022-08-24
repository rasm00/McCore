package net.justcoded.mc_core.components.tasks;

import net.justcoded.mc_core.components.interfaces.Taskable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Task<T extends JavaPlugin> implements Taskable {

    protected final T main;
    protected int id;
    protected Runnable task;

    public Task(T main) {
        this.main = main;
    }

    @Override
    public void runTask() {
        this.run();
    }

    @Override
    public void stopTask() {
        this.stop();
    }

    protected void run() {
        this.task();
    }
    protected void stop() {
        Bukkit.getScheduler().cancelTask(this.id);
    }

    @Override
    public void setEndAndEndTask(int seconds, Runnable task) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            this.stop();
            task.run();
        }, 20L * seconds);
    }

    @Override
    public Task<T> createTask(Runnable task) {
        this.task = task;
        return this;
    }
}
