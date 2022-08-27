package net.justcoded.mc_core.components;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class HCommand<T extends JavaPlugin> implements CommandExecutor, TabCompleter {

    protected String command;
    protected String permission;
    protected T plugin;

    public HCommand(String command, String permission, T plugin) {
        this.command = command;
        this.permission = permission;
        this.plugin = plugin;
    }

    public void registerCommand() {
        try {
            PluginCommand command = plugin.getCommand(this.command);
            command.setExecutor(this);
            command.setTabCompleter(this);

        }catch (NullPointerException ex) {
           ex.printStackTrace();
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    @SuppressWarnings("NullableProblems")
    @Override
    public abstract List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args);
}
