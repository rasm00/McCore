package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;

public class DoubleArg extends OpCommandArg {
    public DoubleArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String || object instanceof Integer || object instanceof Float) {
            return CString.getDouble(object);
        }

        if (object instanceof Double) {
            return object;
        }

        return null;
    }
}
