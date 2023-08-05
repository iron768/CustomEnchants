package me.iron768.customenchants.listener.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.event.ChangeItemEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemChangeListener implements Listener {

    // TODO: add .clone() to old items?

    private final CustomEnchants plugin;

    public ItemChangeListener(CustomEnchants plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemHold(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        ItemStack oldItem = player.getInventory().getItem(event.getPreviousSlot());

        if (oldItem == null)
            oldItem = new ItemStack(Material.AIR);

        ItemStack newItem = player.getInventory().getItem(event.getNewSlot());

        if (newItem == null)
            newItem = new ItemStack(Material.AIR);

        ChangeItemEvent changeItemEvent = new ChangeItemEvent(player, oldItem, newItem);

        this.plugin.getServer().getPluginManager().callEvent(changeItemEvent);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack oldItem = event.getItemDrop().getItemStack();

        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack newItem = player.getInventory().getItemInMainHand();

                ChangeItemEvent changeItemEvent = new ChangeItemEvent(player, oldItem, newItem);

                plugin.getServer().getPluginManager().callEvent(changeItemEvent);
            }
        }.runTaskLater(this.plugin, 3);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();

        ItemStack oldItem = event.getBrokenItem(), newItem = player.getInventory().getItemInMainHand();

        ChangeItemEvent changeItemEvent = new ChangeItemEvent(player, oldItem, newItem);

        this.plugin.getServer().getPluginManager().callEvent(changeItemEvent);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onMoveItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getInventory().getHeldItemSlot() != event.getSlot())
            return;

        ItemStack oldItem = player.getInventory().getItemInMainHand().clone();

        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack newItem = player.getInventory().getItemInMainHand();

                ChangeItemEvent changeItemEvent = new ChangeItemEvent(player, oldItem, newItem);

                plugin.getServer().getPluginManager().callEvent(changeItemEvent);
            }
        }.runTaskLater(this.plugin, 3);
    }

    // TODO: deprecated event!
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack oldItem = player.getInventory().getItemInMainHand();

        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack newItem = player.getInventory().getItemInMainHand();

                if (oldItem.getType() == newItem.getType())
                    return;

                ChangeItemEvent changeItemEvent = new ChangeItemEvent(player, oldItem, newItem);

                plugin.getServer().getPluginManager().callEvent(changeItemEvent);
            }
        }.runTaskLater(this.plugin, 5);
    }

}
