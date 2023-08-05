package me.iron768.customenchants.manager.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantItemData;
import me.iron768.customenchants.player.PlayerEnchantedItem;

public class EnchantEffectManager {

    private CustomEnchants plugin;

    public EnchantEffectManager(CustomEnchants plugin) {
        this.plugin = plugin;
    }

    public void handlePassiveEnchants(PlayerData playerData) {
        for (PlayerEnchantedItem enchantedItemz : playerData.getPlayerEnchantHolder().getEnchantedItems().values()) {
            for (PlayerEnchantItemData playerEnchantItemData : enchantedItemz.getEnchantItemDataList()) {
                Enchant enchant = playerEnchantItemData.getEnchant();

                if (enchant.getEnchantType() == EnchantType.PASSIVE) {
                    enchant.effect(playerData, enchantedItemz.getItem(), playerEnchantItemData.getLevel());
                }
            }
        }
    }

    public void handleEffect(PlayerData playerData, PlayerEnchantedItem enchantedItem, EnchantType enchantType) {
        if (enchantedItem == null)
            return;

        for (PlayerEnchantItemData itemDataz : enchantedItem.getEnchantItemDataList()) {
            Enchant enchant = itemDataz.getEnchant();

            if (enchant.getEnchantType() == enchantType) {
                enchant.effect(playerData, enchantedItem.getItem(), itemDataz.getLevel());
            }
        }
    }

    public void removeEffect(PlayerData playerData, PlayerEnchantedItem enchantedItem, EnchantType enchantType) {
        if (enchantedItem == null)
            return;

        for (PlayerEnchantItemData itemDataz : enchantedItem.getEnchantItemDataList()) {
            Enchant enchant = itemDataz.getEnchant();

            if (enchant.getEnchantType() == enchantType) {
                enchant.removeEffect(playerData, enchantedItem.getItem(), itemDataz.getLevel());
            }
        }
    }

}
