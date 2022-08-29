package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;

public class IntArg extends OpCommandArg {
    public IntArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String || object instanceof Double || object instanceof Float) {
            return CString.getInt(object);
        }

        if (object instanceof Integer) {
            return object;
        }

        return null;
    }
}
