package net.justcoded.mc_core.components;

import net.justcoded.mc_core.utilities.Chat;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MagicWand {

    private static final ItemStack itemStack = new ItemStack(Material.WOODEN_AXE, 1);
    private static final String name = Chat.format("&b&lMagic Wand");
    private static final String rmb = Chat.format("&aRMB &f- press to select first position.");
    private static final String lmb = Chat.format("&aLMB &f- press to select second position.");

    public static ItemStack getMagicWand() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(rmb, lmb));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
