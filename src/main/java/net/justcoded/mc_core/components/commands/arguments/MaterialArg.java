package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;
import org.bukkit.Material;

public class MaterialArg extends OpCommandArg {

    public MaterialArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Material) {
            return object;
        }

        if (object instanceof String) {
            return CString.getMaterialFromString((String) object);
        }

        return null;
    }
}
