package me.iron768.customenchants.player;

import me.iron768.customenchants.enchant.Enchant;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerEnchantedItem {

    private ItemStack item;
    private List<PlayerEnchantItemData> enchantItemDataList;

    public PlayerEnchantedItem(ItemStack item) {
        this.item = item;
        this.enchantItemDataList = new ArrayList<>();
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public List<PlayerEnchantItemData> getEnchantItemDataList() {
        return enchantItemDataList;
    }

    public List<Enchant> getEnchants() {
        List<Enchant> enchants = new ArrayList<>();

        for (PlayerEnchantItemData itemDataz : this.enchantItemDataList) {
            enchants.add(itemDataz.getEnchant());
        }

        return enchants;
    }

    public void setEnchantItemDataList(List<PlayerEnchantItemData> enchantItemDataList) {
        this.enchantItemDataList = enchantItemDataList;
    }

    public void addEnchant(PlayerEnchantItemData playerEnchantItemData) {
        this.enchantItemDataList.add(playerEnchantItemData);
    }

}
