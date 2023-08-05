package me.iron768.customenchants.manager;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.manager.impl.EnchantEffectManager;
import me.iron768.customenchants.manager.impl.EnchantManager;
import me.iron768.customenchants.manager.impl.PlayerDataManager;

public class Managers {

    private final EnchantManager enchantManager;
    private final PlayerDataManager playerDataManager;
    private final EnchantEffectManager enchantEffectManager;

    public Managers(CustomEnchants plugin) {
        this.enchantManager = new EnchantManager(plugin);
        this.playerDataManager = new PlayerDataManager(plugin);
        this.enchantEffectManager = new EnchantEffectManager(plugin);
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public EnchantEffectManager getEnchantEffectManager() {
        return enchantEffectManager;
    }

}
