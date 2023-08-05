package me.iron768.customenchants.enchant.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.enchant.EnchantApplication;
import me.iron768.customenchants.enchant.EnchantRarity;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.player.PlayerData;
import org.bukkit.inventory.ItemStack;

public class OverloadEnchant extends Enchant {

    private CustomEnchants plugin;

    private final int increase;

    public OverloadEnchant(CustomEnchants plugin) {
        super("Overload", "A permanent increase to the amount of hearts you have", EnchantRarity.LEGENDARY, EnchantApplication.CHESTPLATE, EnchantType.ARMOR_EQUIP, 3);

        this.plugin = plugin;

        this.increase = 2;
    }

    @Override
    public void effect(PlayerData playerData, ItemStack item, int level) {
        playerData.addEnchantHearts(getName(), level + this.increase);

        playerData.updateEnchantHearts();
    }

    @Override
    public void removeEffect(PlayerData playerData, ItemStack item, int level) {
        playerData.getEnchantHearts().remove(getName());

        playerData.updateEnchantHearts();
    }

}
