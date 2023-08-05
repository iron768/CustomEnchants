package me.iron768.customenchants.player;

import de.tr7zw.nbtapi.NBTItem;
import me.iron768.customenchants.enchant.Enchant;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerEnchantHolder {

    private PlayerData playerData;

    private final Map<ItemStack, PlayerEnchantedItem> enchantedItems;

    public PlayerEnchantHolder(PlayerData playerData) {
        this.playerData = playerData;

        this.enchantedItems = new HashMap<>();
    }

    public ItemStack getItem(ItemStack item) {
        if (this.enchantedItems.get(item) == null)
            return null;

        return this.enchantedItems.get(item).getItem();
    }

    public ItemStack getItemStackByKey(String uuid) {
        Collection<PlayerEnchantedItem> values = enchantedItems.values();
        UUID key = UUID.fromString(uuid);

        for (PlayerEnchantedItem enchantedItem : values) {
            NBTItem itemData = new NBTItem(enchantedItem.getItem());
            UUID itemKey = UUID.fromString(itemData.getString("ce-key"));

            if (key.equals(itemKey)) {
                return enchantedItem.getItem();
            }
        }

        // Return null if the map is empty
        return null;
    }

    public void addItem(ItemStack item, PlayerEnchantedItem enchantedItem) {
        if (item == null || item.getType() == Material.AIR)
            return;

        if (enchantedItems.containsKey(item))
            return;

        enchantedItem.setItem(item.clone());

        enchantedItems.put(item.clone(), enchantedItem);
    }

    public void removeItem(ItemStack item) {
        if (item == null)
            return;

        this.enchantedItems.remove(item);
    }

    public PlayerEnchantedItem getEnchantedItemByItemStack(ItemStack item) {
        return this.enchantedItems.get(item);
    }

    public List<Enchant> getAllEnchants() {
        return enchantedItems.values().stream()
                .filter(Objects::nonNull) // Filter out any null items in the enchantedItems list
                .flatMap(item -> item.getEnchantItemDataList().stream())
                .filter(Objects::nonNull) // Filter out any null items in the enchantItemDataList
                .map(PlayerEnchantItemData::getEnchant)
                .filter(Objects::nonNull) // Filter out any null enchant objects
                .collect(Collectors.toList());
    }

    public Map<ItemStack, PlayerEnchantedItem> getEnchantedItems() {
        return enchantedItems;
    }


}
