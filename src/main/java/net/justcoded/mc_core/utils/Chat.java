package net.justcoded.mc_core.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Chat {

    public static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-f0-9]{6}");

    private static String hexFormat(String toFormat) {
        Matcher match = HEX_PATTERN.matcher(toFormat);

        while(match.find()) {
            String hex = toFormat.substring(match.start(), match.end());
            toFormat = toFormat.replace(hex, ChatColor.of(hex) + "");
            match = HEX_PATTERN.matcher(toFormat);
        }
        return toFormat;
    }

    private static String ccFormat(String toFormat) {
        return ChatColor.translateAlternateColorCodes('&', toFormat);
    }

    private static List<String> ccFormat(List<String> toFormat) {
        return toFormat.stream()
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList());
    }

    public static String format(String toFormat) {
        if (toFormat == null) return  "";

        toFormat = hexFormat(toFormat);
        toFormat = ccFormat(toFormat);

        return toFormat;
    }

    public static List<String> format(List<String> toFormat) {
        return toFormat.stream().map(Chat::format).collect(Collectors.toList());
    }

    public static TextComponent getPerformingCommandTextComponent(String text, String hoverText, String command) {
        TextComponent component = new TextComponent(format(text));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(format(hoverText))));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return component;
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(format(message));
    }

    public static void broadcastMessage(TextComponent component) {
        Bukkit.spigot().broadcast(component);
    }

    public static void sendFormattedMessage(CommandSender sender, String message) {
        sender.sendMessage(format(message));
    }
}

