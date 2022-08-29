package net.justcoded.mc_core.components.commands;

import net.justcoded.mc_core.utilities.Chat;
import org.bukkit.command.CommandSender;

public record CCommandPermission(String permission, String noPermissionMessage, PERMISSION_TYPE type) {

    public boolean hasPermission(CommandSender player, String permission) {
        if (permission == null) {
            return true;
        }
        return player.hasPermission(permission);
    }

    public boolean hasPermission(CommandSender player) {
        return hasPermission(player, permission);
    }

    public boolean checkPermission(CommandSender player, String noPermissionMessage) {
        boolean hasPermission = hasPermission(player);
        if (!hasPermission) {
            player.sendMessage(Chat.format(noPermissionMessage));
        }
        return hasPermission;
    }

    public boolean checkPermission(CommandSender player) {
        return checkPermission(player, noPermissionMessage);
    }

    public enum PERMISSION_TYPE {
        SEE_TAB_COMPLETE, USE_COMMAND
    }
}
