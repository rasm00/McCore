package net.justcoded.mc_core.components.commands.arguments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class EnchantArg extends CCommandArg {

    public EnchantArg(String name) {
        super(name);
    }

    @Override
    public  Object getObject(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Enchantment) {
            return object;
        }

        if (object instanceof String) {
            return Enchantment.getByKey(NamespacedKey.minecraft(object.toString()));
        }

        return null;
    }
}
