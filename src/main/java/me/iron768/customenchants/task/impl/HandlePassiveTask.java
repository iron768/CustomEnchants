package me.iron768.customenchants.task.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.player.PlayerData;

public class HandlePassiveTask implements Runnable {

    private CustomEnchants plugin;

    public HandlePassiveTask(CustomEnchants plugin) {
        this.plugin = plugin;

        long totalTicks = 20L;
        long seconds = 5L;

        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, totalTicks * seconds);
    }

    @Override
    public void run() {
        for (PlayerData playerData : this.plugin.getManagers().getPlayerDataManager().getPlayerDataMap().values()) {
            if (playerData == null)
                return; // necessary? maybe to handle /reload

            this.plugin.getManagers().getEnchantEffectManager().handlePassiveEnchants(playerData);
        }
    }

}
