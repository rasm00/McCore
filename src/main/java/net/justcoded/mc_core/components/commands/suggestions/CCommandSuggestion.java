package net.justcoded.mc_core.components.commands.suggestions;

import net.justcoded.mc_core.components.commands.CCommandSender;

import java.util.List;
import java.util.function.BiFunction;

public record CCommandSuggestion(
        BiFunction<CCommandSender, String[], List<String>> biFunction) {

    public List<String> apply(CCommandSender commandSender, String[] args) {
        return biFunction.apply(commandSender, args);
    }
}