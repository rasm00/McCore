package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;

public class FloatArg extends CCommandArg {
    public FloatArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String || object instanceof Double || object instanceof Integer) {
            return CString.getFloat(object);
        }

        if (object instanceof Float) {
            return object;
        }

        return null;
    }
}
