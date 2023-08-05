package me.iron768.customenchants.manager.impl;

import de.tr7zw.nbtapi.NBTItem;
import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.enchant.impl.*;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantItemData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import me.iron768.customenchants.util.ItemUtil;
import me.iron768.customenchants.util.MessageUtil;
import me.iron768.customenchants.util.RomanNumberUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantManager {

    private CustomEnchants plugin;

    // names of enchants are stored in all uppercase
    private Map<String, Enchant> enchantMap;

    public EnchantManager(CustomEnchants plugin) {
        this.plugin = plugin;

        this.enchantMap = new HashMap<>();

        this.loadEnchants();
    }

    private void loadEnchants() {
        addEnchant(new OverloadEnchant(this.plugin));
        addEnchant(new DrunkEnchant(this.plugin));
        addEnchant(new SpringsEnchant(this.plugin));
        addEnchant(new GearsEnchant(this.plugin));
        addEnchant(new HasteEnchant());
    }

    public Map<String, Enchant> getEnchantMap() {
        return enchantMap;
    }

    public void addEnchant(Enchant enchant) {
        this.enchantMap.put(enchant.getName().toUpperCase(), enchant);
    }

    public Enchant getEnchantByName(String name) {
        return this.enchantMap.get(name.toUpperCase());
    }

    public void applyEnchant(PlayerData playerData, Enchant enchant, int level, ItemStack item) {
        if (alreadyHasEnchant(item, enchant)) {
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&cThat item already has that enchant!"));
            return;
        }

        if (!enchant.getEnchantApplication().getItems().contains(item.getType())) {
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&cYou cannot put that enchantment on that piece of equipment!"));
            return;
        }

        ItemStack newItem = applyEnchant(enchant, level, item);

        playerData.getBukkitPlayer().getInventory().remove(item);
        playerData.getBukkitPlayer().getInventory().addItem(newItem); // give new item

        // TODO: this is needs to be reformatted
        playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&bYou obtained the enchantment &l" + enchant.getEnchantRarity().getColor() + enchant.getName() + " " + RomanNumberUtil.toRoman(level) + " &r&bon your &l" + item.getType().name()));
    }

    private ItemStack applyEnchant(Enchant enchant, int level, ItemStack item) {
        ItemUtil.addLoreLine(item, enchant.getEnchantRarity().getColor() + enchant.getName() + " " + RomanNumberUtil.toRoman(level));

        NBTItem nbtItem = new NBTItem(item);

        if (!nbtItem.hasKey("ce-key")) {
            UUID uuid = UUID.randomUUID();

            nbtItem.setString("ce-key", uuid.toString());
        }

        nbtItem.setInteger(enchant.getName().toUpperCase(), level);

        return nbtItem.getItem();
    }

    public List<Enchant> getEnchants(ItemStack item) {
        if (item == null)
            return null;

        if (item.getType() == Material.AIR)
            return null;

        List<Enchant> enchantsList = new ArrayList<>();
        NBTItem nbtItem = new NBTItem(item);

        if (!nbtItem.hasNBTData())
            return null;

        for (String nbtData : nbtItem.getKeys()) {
            enchantsList.add(getEnchantByName(nbtData));
        }

        return enchantsList;
    }

    public PlayerEnchantedItem getEnchantedItem(ItemStack item) {
        if (item == null)
            return null;

        if (item.getType() == Material.AIR)
            return null;

        if (!containsEnchantments(item))
            return null;

        NBTItem nbtItem = new NBTItem(item);

        if (!nbtItem.hasNBTData())
            return null;

        PlayerEnchantedItem playerEnchantedItem = new PlayerEnchantedItem(item);

        for (String nbtData : nbtItem.getKeys()) {
            Enchant enchant = getEnchantByName(nbtData);

            if (enchant != null) {
                int level = nbtItem.getInteger(nbtData);

                PlayerEnchantItemData playerEnchantedItemData = new PlayerEnchantItemData(enchant, level);

                playerEnchantedItem.addEnchant(playerEnchantedItemData);
            }
        }

        return playerEnchantedItem;
    }

    // TODO: would it be more efficient to check if it has "ce-key" rather then loop through enchants?
    public boolean containsEnchantments(ItemStack item) {
        if (item == null)
            return false;

        if (item.getType() == Material.AIR)
            return false;

        NBTItem nbtItem = new NBTItem(item);

        if (!nbtItem.hasNBTData())
            return false;

        for (String nbtData : nbtItem.getKeys()) {
            if (containsEnchantmentData(nbtData))
                return true;
        }

        return false;
    }

    private boolean containsEnchantmentData(String text) {
        text = text.replaceAll("_", " ");

        text = text.toUpperCase();

        return this.enchantMap.get(text) != null;
    }

    private boolean alreadyHasEnchant(ItemStack item, Enchant enchant) {
        PlayerEnchantedItem enchantedItem = getEnchantedItem(item);

        if (enchantedItem == null)
            return false;

        return enchantedItem.getEnchants().contains(enchant);
    }

}
