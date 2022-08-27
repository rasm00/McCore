package net.justcoded.mc_core.components.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandExecutable {


    boolean onSubCommand(CommandSender sender, Command command, String label, String[] args);
}
