package me.iron768.customenchants.enchant;

import me.iron768.customenchants.player.PlayerData;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class Enchant {

    // TODO: enchant rarity

    private String name, description;
    private EnchantRarity enchantRarity;
    private EnchantApplication enchantApplication;
    private EnchantType enchantType;
    private int maxLevel;

    public Enchant(String name, String description, EnchantRarity enchantRarity, EnchantApplication enchantApplication, EnchantType enchantType, int maxLevel) {
        this.name = name;
        this.description = description;
        this.enchantRarity = enchantRarity;
        this.enchantApplication = enchantApplication;
        this.enchantType = enchantType;
        this.maxLevel = maxLevel;
    }

    // TODO: please implement
    public void effect(PlayerData playerData, Event event, ItemStack item, int level) {
    }

    public void effect(PlayerData playerData, ItemStack item, int level) {
    }

    public void removeEffect(PlayerData playerData, ItemStack item, int level) {
    }

    // TODO: please implement or remove
    public void removeEffect(Event event, ItemStack item, int level) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnchantRarity getEnchantRarity() {
        return enchantRarity;
    }

    public void setEnchantRarity(EnchantRarity enchantRarity) {
        this.enchantRarity = enchantRarity;
    }

    public EnchantApplication getEnchantApplication() {
        return enchantApplication;
    }

    public void setEnchantApplication(EnchantApplication enchantApplication) {
        this.enchantApplication = enchantApplication;
    }

    public EnchantType getEnchantType() {
        return enchantType;
    }

    public void setEnchantType(EnchantType enchantType) {
        this.enchantType = enchantType;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

}
