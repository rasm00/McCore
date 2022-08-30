package net.justcoded.mc_core.components.commands.arguments;

public class StringArg extends CCommandArg {

    public StringArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String) {
            return object;
        }

        return null;
    }
}
