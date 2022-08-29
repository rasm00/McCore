package net.justcoded.mc_core.components.commands.arguments;

import net.justcoded.mc_core.utilities.CString;
import org.bukkit.Sound;

import java.util.Optional;

public class SoundArg extends OpCommandArg {
    public SoundArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Sound) {
            return object;
        }

        if (object instanceof String s) {
            s = s.toUpperCase().replace(".", "_");
            Optional<Sound> optional = CString.getEnumValue(s, Sound.class);
            if (optional.isPresent()) {
                return optional.get();
            }
        }

        return null;
    }
}
