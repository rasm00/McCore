package net.justcoded.mc_core.components.commands;

import java.util.ArrayList;
import java.util.List;

public class CCommandRegister {
    private static final List<CCommand> commands = new ArrayList<>();

    public static void addCommand(CCommand command) {
        commands.add(command.register());
    }

    public static void unregisterCommands() {
        commands.forEach(CCommand::unregister);
    }


}
