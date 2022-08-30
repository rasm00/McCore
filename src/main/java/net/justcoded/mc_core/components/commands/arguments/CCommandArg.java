package net.justcoded.mc_core.components.commands.arguments;

import java.util.UUID;

public abstract class CCommandArg {
    private final String name;

    public CCommandArg(String name) {
        this.name = name;
    }

    public CCommandArg() {
        this.name = UUID.randomUUID().toString();
    }

    
    public abstract Object getObject(Object object);

    public String getName() {
        return name;
    }
}
