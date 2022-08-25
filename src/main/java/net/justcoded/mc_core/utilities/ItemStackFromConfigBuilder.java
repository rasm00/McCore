package net.justcoded.mc_core.utilities;

import net.justcoded.mc_core.components.enchantments.GlowingEnchantment;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemStackFromConfigBuilder implements Listener {

    private final String name;
    private final List<String> lore;
    private final Map<String, Integer> stringEnchantments;
    private final int amount;
    private final Material material;
    private final boolean isGlow;

    private Map<Enchantment, Integer> enchantments;

    public ItemStackFromConfigBuilder(String name, List<String> lore, Map<String, Integer> stringEnchantments, int amount, Material material, boolean isGlow) {
        this.name = name;
        this.lore = lore;
        this.stringEnchantments = stringEnchantments;
        this.amount = amount;
        this.material = material;
        this.isGlow = isGlow;
    }

    private void parseEnchantments() {
        try {
            if (stringEnchantments == null) return;
            System.out.println(stringEnchantments.keySet() + " tu jestem");
            enchantments = ItemStackUtils.parseEnchantments(stringEnchantments);
        } catch(IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    public ItemStack getItem() {
        parseEnchantments();

        ItemStack itemStack = new ItemStack(material, amount);
        itemStack.addUnsafeEnchantments(enchantments);

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) return itemStack;

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        if (isGlow) itemStack.addUnsafeEnchantment(Objects.requireNonNull((GlowingEnchantment.getEnchantment())), 0);


        return itemStack;
    }
}

