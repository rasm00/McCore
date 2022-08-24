package net.justcoded.mc_core.components.interfaces;

import net.justcoded.mc_core.components.tasks.Task;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public interface Taskable<T extends JavaPlugin> {

    void runTask();
    BukkitTask task();
    Task<T> createTask(Runnable task);
    void stopTask();
    void setEndAndEndTask(int seconds, Runnable task);

    Task<T> createTask(Consumer<Task<T>> task);
}
