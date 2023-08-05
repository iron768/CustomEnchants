package me.iron768.customenchants.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static void addLoreLine(ItemStack item, String text) {
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        if (itemMeta.hasLore())
            lore = new ArrayList<>(itemMeta.getLore());

        lore.add(MessageUtil.color(text));

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
    }

}
