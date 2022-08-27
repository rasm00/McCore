package net.justcoded.mc_core.components.tasks;

import net.justcoded.mc_core.interfaces.Taskable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public abstract class Task<T extends JavaPlugin> implements Taskable<T> {

    protected final T main;
    private BukkitTask task;
    protected Runnable taskRunnable;
    protected Consumer<Task<T>> taskConsumer;

    public Task(T main) {
        this.main = main;
    }

    @Override
    public void runTask() {
        this.run();
    }

    @Override
    public void stopTask() {
        this.cancel();
    }

    protected void run() {
        this.task = this.task();
    }

    protected void cancel() {
        if (this.task != null && !this.task.isCancelled()) {
            this.task.cancel();
        }
    }

    protected void runCurrentTask() {
        if (this.taskConsumer != null) {
            this.taskConsumer.accept(this);
        }
        if (this.taskRunnable != null) {
            this.taskRunnable.run();
        }
    }

    @Override
    public void setEndAndEndTask(int seconds, Runnable task) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            this.cancel();
            task.run();
        }, 20L * seconds);
    }

    @Override
    public Task<T> createTask(Runnable task) {
        this.taskRunnable = task;
        return this;
    }

    @Override
    public Task<T> createTask(Consumer<Task<T>> task) {
        this.taskConsumer = task;
        return this;
    }

}
