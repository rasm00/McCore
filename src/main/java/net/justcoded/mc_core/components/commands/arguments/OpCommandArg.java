package net.justcoded.mc_core.components.commands.arguments;

import java.util.UUID;

public abstract class OpCommandArg {
    private final String name;

    public OpCommandArg(String name) {
        this.name = name;
    }

    public OpCommandArg() {
        this.name = UUID.randomUUID().toString();
    }

    
    public abstract Object getObject(Object object);

    public String getName() {
        return name;
    }
}
