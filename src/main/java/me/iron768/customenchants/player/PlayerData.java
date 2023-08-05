package me.iron768.customenchants.player;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.util.MessageUtil;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {

    private final UUID uuid;

    private final PlayerEnchantHolder playerEnchantHolder;

    private final Map<String, Integer> enchantHearts;

    private int debugLevel;

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();

        this.playerEnchantHolder = new PlayerEnchantHolder(this);
        this.enchantHearts = new HashMap<>();
        this.debugLevel = 0;
    }

    public Player getBukkitPlayer() {
        return CustomEnchants.getInstance().getServer().getPlayer(this.uuid);
    }

    public PlayerEnchantHolder getPlayerEnchantHolder() {
        return playerEnchantHolder;
    }

    public void addEnchantHearts(String enchant, int amount) {
        this.enchantHearts.put(enchant, amount);
    }

    public void removeEnchantHearts(String enchant) {
        this.enchantHearts.remove(enchant);
    }

    public Map<String, Integer> getEnchantHearts() {
        return enchantHearts;
    }

    public void updateEnchantHearts() {
        int totalHearts = 0;

        for (int value : getEnchantHearts().values()) {
            totalHearts += value;
        }

        int newMaxHealth = 20 + (totalHearts * 2);

        AttributeInstance maxHealthAttribute = getBukkitPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (maxHealthAttribute == null)
            return;

        double maxCurrentHealth = maxHealthAttribute.getBaseValue();

        if (maxCurrentHealth != newMaxHealth) {
            maxHealthAttribute.setBaseValue(newMaxHealth);

            getBukkitPlayer().setHealth(newMaxHealth);
        }
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    public void setDebugLevel(int debugLevel) {
        this.debugLevel = debugLevel;
    }

    public void sendDebugMessage(String message, int minLevel) {
        if (this.debugLevel > minLevel)
            getBukkitPlayer().sendMessage(MessageUtil.color("&c&lDEBUG - &r" + message));
    }

}
