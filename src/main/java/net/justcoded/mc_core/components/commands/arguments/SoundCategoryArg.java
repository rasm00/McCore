package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;
import org.bukkit.SoundCategory;

import java.util.Optional;

public class SoundCategoryArg extends CCommandArg {
    public SoundCategoryArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof SoundCategory) {
            return object;
        }

        if (object instanceof String) {
            Optional<SoundCategory> optional = CString.getEnumValue((String) object, SoundCategory.class);
            if (optional.isPresent()) {
                return optional.get();
            }
        }

        return null;
    }
}
