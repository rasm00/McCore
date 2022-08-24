package net.justcoded.mc_core.components.interfaces;

import net.justcoded.mc_core.components.tasks.Task;
import org.bukkit.plugin.java.JavaPlugin;

public interface Taskable<T extends JavaPlugin> {

    void runTask();
    int task();
    Task<T> createTask(Runnable task);
    void stopTask();
    void setEndAndEndTask(int seconds, Runnable task);
}
