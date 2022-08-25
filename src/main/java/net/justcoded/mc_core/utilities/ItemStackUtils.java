package net.justcoded.mc_core.utilities;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class ItemStackUtils {

    public static Map<Enchantment, Integer> parseEnchantments(Map<String, Integer> enchantments) throws IllegalArgumentException {
        Map<Enchantment, Integer> parsedEnchantment = new HashMap<>();

        enchantments.keySet()
                .forEach(s -> parsedEnchantment.put(Enchantment.getByKey(NamespacedKey.minecraft(s)), enchantments.get(s)));

        return parsedEnchantment;
    }

    public static Map<String, String> reparseEnchantments(Map<Enchantment, Integer> enchantments) {
        Map<String, String> parsedEnchantments = new HashMap<>();

        enchantments.keySet()
                .forEach(e -> parsedEnchantments.put(e.getKey().getKey(), String.valueOf(e.getStartLevel())));

        return parsedEnchantments;
    }
}
