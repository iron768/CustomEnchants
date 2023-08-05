package me.iron768.customenchants.listener.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.EnchantApplication;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.event.ChangeItemEvent;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerItemListener implements Listener {

    private CustomEnchants plugin;

    public PlayerItemListener(CustomEnchants plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemChange(ChangeItemEvent event) {
        ItemStack newItem = event.getNewItem();

        if (newItem == null)
            return;

        if (newItem.getType() == Material.AIR)
            return;

        if (EnchantApplication.ARMOR.getItems().contains(newItem.getType()))
            return;

        // maybe redundant?
        if (!EnchantApplication.WEAPONSANDTOOLS.getItems().contains(newItem.getType()))
            return;

        if (!this.plugin.getManagers().getEnchantManager().containsEnchantments(newItem))
            return;

        Player player = event.getPlayer();
        PlayerData playerData = this.plugin.getManagers().getPlayerDataManager().getPlayerData(player);

        PlayerEnchantedItem enchantedItem = this.plugin.getManagers().getEnchantManager().getEnchantedItem(newItem);

        playerData.getPlayerEnchantHolder().addItem(newItem, enchantedItem);

        this.plugin.getManagers().getEnchantEffectManager().handleEffect(playerData, enchantedItem, EnchantType.HOLD_ITEM);

        playerData.sendDebugMessage("&bYou are holding &l" + enchantedItem.getItem().getType() + " &r&band it has &l" + enchantedItem.getEnchantItemDataList().size() + " &r&benchants.", 1);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemRemove(ChangeItemEvent event) {
        ItemStack oldItem = event.getOldItem();

        if (oldItem == null)
            return;

        if (EnchantApplication.ARMOR.getItems().contains(oldItem.getType()))
            return;

        Player player = event.getPlayer();
        PlayerData playerData = this.plugin.getManagers().getPlayerDataManager().getPlayerData(player);

        ItemStack toRemove = playerData.getPlayerEnchantHolder().getItem(oldItem);

        if (oldItem.clone().isSimilar(toRemove)) {
            this.handleRemove(playerData, toRemove);
        }
    }

    private void handleRemove(PlayerData playerData, ItemStack item) {
        PlayerEnchantedItem enchantedItem = playerData.getPlayerEnchantHolder().getEnchantedItemByItemStack(item);

        playerData.sendDebugMessage("&bYou are no longer holding &l" + item.getType().name() + " &r&band it had &l" + enchantedItem.getEnchantItemDataList().size() + " &r&benchants.", 1);

        this.plugin.getManagers().getEnchantEffectManager().removeEffect(playerData, enchantedItem, EnchantType.HOLD_ITEM);

        playerData.getPlayerEnchantHolder().removeItem(item);
    }

}
