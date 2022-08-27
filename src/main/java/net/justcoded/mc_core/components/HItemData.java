package net.justcoded.mc_core.components;

import net.justcoded.mc_core.annotations.ConfigItem;
import net.justcoded.mc_core.injectors.DataInjector;
import net.justcoded.mc_core.utilities.Chat;
import net.justcoded.mc_core.utilities.ItemStackFromConfigBuilder;
import net.justcoded.mc_core.utilities.ItemStackUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.*;

public class HItemData implements Serializable {

    private  FileConfiguration config;
    private  String path;

    @ConfigItem(itemField = "name")
    private String name;
    @ConfigItem(itemField = "lore")
    private List<String> lore;
    @ConfigItem(itemField = "enchantments")
    private Map<String, String> enchantments;
    @ConfigItem(itemField = "amount")
    private String amount;
    @ConfigItem(itemField = "material")
    private String material;
    @ConfigItem(itemField = "glow")
    private String glow;

    public HItemData(FileConfiguration config, String path) {
        this.config = config;
        this.path = path;
    }

    public HItemData(ItemStack itemStack) {
        this.name = String.valueOf(itemStack.getItemMeta());
        this.lore = itemStack.getItemMeta().getLore();
        this.enchantments = ItemStackUtils.reparseEnchantments(itemStack.getEnchantments());
        this.amount = String.valueOf(itemStack.getAmount());
        this.material = itemStack.getType().toString();
        this.glow = "false";
    }

    public HItemData(String name, List<String> lore, Map<String, String> enchantments, String amount, String material, String glow) {
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
        this.amount = amount;
        this.material = material;
        this.glow = glow;
    }

    public HItemData setName(String name) {
        this.name = name;
        return this;
    }

    public HItemData setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public HItemData setEnchantments(Map<String, String> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public HItemData setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public HItemData setMaterial(String material) {
        this.material = material;
        return this;
    }

    public HItemData setGlow(String glow) {
        this.glow = glow;
        return this;
    }

    public String getName() {
        if (name == null) return "default";

        return Chat.format(name);
    }

    public List<String> getLore() {
        if (lore == null) return new ArrayList<>();

        return Chat.format(lore);
    }

    public Map<String, Integer> getEnchantments() {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.keySet());

        if (enchantments == null) return Collections.emptyMap();

        Set<String> strings = enchantments.keySet();

        for (String en : strings) {
            map.put(en, Integer.parseInt(enchantments.get(en)));
        }

        return map;
    }

    public Integer getAmount() {
        if (amount == null) return 1;

        return Integer.parseInt(amount);
    }

    public Material getMaterial() {
        if (material == null) return Material.STONE;

        return Material.getMaterial(material.toUpperCase());
    }

    public boolean isGlow() {
        if (glow == null) return false;

        return Boolean.parseBoolean(glow);
    }

    public ItemStack getItem() {
        ItemStackFromConfigBuilder builder = new ItemStackFromConfigBuilder(getName(), getLore(), getEnchantments(), getAmount(), getMaterial(), isGlow());
        return builder.getItem();
    }

    public HItemData injectData() {
        DataInjector.injectItemDataFromFile(this, config, path);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HItemData hItemData)) return false;
        return name.equals(hItemData.name) && enchantments.equals(hItemData.enchantments) && material.equals(hItemData.material) && glow.equals(hItemData.glow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lore, enchantments, amount, material);
    }
}
