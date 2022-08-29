package net.justcoded.mc_core.components.commands;

import net.justcoded.mc_core.utilities.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public record CCommandSender(CommandSender sender) {

    public Player getPlayer() {
        return (Player) sender;
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public Player getFixedPlayer() {
        return this.isPlayer() ? this.getPlayer() : null;
    }

    public void sendMessage(String message) {
        if (isPlayer()) {
            sender.sendMessage(Chat.format(message));
        } else {
            sender.sendMessage(message);
        }
    }
}
