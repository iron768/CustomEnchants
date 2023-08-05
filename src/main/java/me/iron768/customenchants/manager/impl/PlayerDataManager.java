package me.iron768.customenchants.manager.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    private final CustomEnchants plugin;

    private final Map<UUID, PlayerData> playerDataMap;

    public PlayerDataManager(CustomEnchants plugin) {
        this.plugin = plugin;

        this.playerDataMap = new HashMap<>();

        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        this.playerDataMap.put(player.getUniqueId(), new PlayerData(player));

        PlayerData playerData = this.playerDataMap.get(player.getUniqueId());

        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if (this.plugin.getManagers().getEnchantManager().containsEnchantments(armor)) {
                PlayerEnchantedItem enchantedArmor = this.plugin.getManagers().getEnchantManager().getEnchantedItem(armor);

                playerData.getPlayerEnchantHolder().addItem(armor, enchantedArmor);
            }
        }

        // TODO: possibly load all weapons? - this depends how i want to write the caching for tools/weapons

        // TODO: load data?
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.playerDataMap.remove(player.getUniqueId());
    }

    public PlayerData getPlayerData(String name) {
        return this.playerDataMap.get(this.plugin.getServer().getPlayer(name).getUniqueId());
    }

    public PlayerData getPlayerData(Player player) {
        return this.playerDataMap.get(player.getUniqueId());
    }

    public PlayerData getPlayerData(UUID uuid) {
        return this.playerDataMap.get(uuid);
    }

    public Map<UUID, PlayerData> getPlayerDataMap() {
        return playerDataMap;
    }

}
