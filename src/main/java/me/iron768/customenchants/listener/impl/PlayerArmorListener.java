package me.iron768.customenchants.listener.impl;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerArmorListener implements Listener {

    private final CustomEnchants plugin;

    public PlayerArmorListener(CustomEnchants plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onArmorEquip(ArmorEquipEvent event) {
        ItemStack newArmor = event.getNewArmorPiece();

        if (newArmor == null)
            return;

        if (newArmor.getType() == Material.AIR)
            return;

        if (!this.plugin.getManagers().getEnchantManager().containsEnchantments(newArmor))
            return;

        Player player = event.getPlayer();
        PlayerData playerData = this.plugin.getManagers().getPlayerDataManager().getPlayerData(player);

        PlayerEnchantedItem enchantedItem = this.plugin.getManagers().getEnchantManager().getEnchantedItem(newArmor);

        playerData.getPlayerEnchantHolder().addItem(newArmor, enchantedItem);

        this.plugin.getManagers().getEnchantEffectManager().handleEffect(playerData, enchantedItem, EnchantType.ARMOR_EQUIP);

        playerData.sendDebugMessage("&bYou equipped &l" + enchantedItem.getItem().getType() + " &r&band it has &l" + enchantedItem.getEnchantItemDataList().size() + " &r&benchants.", 1);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onArmorUnEquip(ArmorEquipEvent event) {
        ItemStack oldArmor = event.getOldArmorPiece();

        if (oldArmor == null)
            return;

        if (oldArmor.getType() == Material.AIR)
            return;

        if (!this.plugin.getManagers().getEnchantManager().containsEnchantments(oldArmor))
            return;

        Player player = event.getPlayer();
        PlayerData playerData = this.plugin.getManagers().getPlayerDataManager().getPlayerData(player);

        this.handleUnequip(playerData, oldArmor.clone());
    }

    private void handleUnequip(PlayerData playerData, ItemStack armor) {
        ItemStack toRemove = playerData.getPlayerEnchantHolder().getItem(armor);

        // TODO: is this needed?
        if (armor.isSimilar(toRemove)) {
            PlayerEnchantedItem enchantedItem = playerData.getPlayerEnchantHolder().getEnchantedItemByItemStack(toRemove);

            playerData.sendDebugMessage("&bYou unequipped &l" + armor.getType().name() + " &r&band it had &l" + enchantedItem.getEnchantItemDataList().size() + " &r&benchants.", 1);

            this.plugin.getManagers().getEnchantEffectManager().removeEffect(playerData, enchantedItem, EnchantType.ARMOR_EQUIP);

            playerData.getPlayerEnchantHolder().removeItem(toRemove);
        }
    }

}
