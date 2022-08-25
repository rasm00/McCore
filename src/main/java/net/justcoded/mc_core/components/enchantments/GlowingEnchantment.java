package net.justcoded.mc_core.components.enchantments;

import net.justcoded.mc_library.McLibrary;
import net.justcoded.mc_library.components.ExceptionPrinter;
import net.justcoded.mc_library.components.annotations.Warning;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class GlowingEnchantment extends Enchantment {

    private static final NamespacedKey enchKey = new NamespacedKey(McLibrary.class.getName().toLowerCase(), "glowing");

    public GlowingEnchantment(@Warning(info = "Cannot be null") NamespacedKey key) {
        super(key);
    }

    @Warning(info = "Cannot be null")
    @Override
    public String getName() {
        return "Glowing";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Warning(info = "Cannot be null")
    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@Warning(info = "Cannot be null") Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@Warning(info = "Cannot be null") ItemStack item) {
        return false;
    }


    public static void load() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GlowingEnchantment glow = new GlowingEnchantment(enchKey);
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
            ExceptionPrinter.logException(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Enchantment getEnchantment() {
        return Enchantment.getByKey(enchKey);
    }
}
