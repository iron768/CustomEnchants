package me.iron768.customenchants.enchant;

import org.bukkit.ChatColor;

public enum EnchantRarity {

    SIMPLE(ChatColor.GRAY),
    UNIQUE(ChatColor.GREEN),
    ELITE(ChatColor.AQUA),
    ULTIMATE(ChatColor.YELLOW),
    LEGENDARY(ChatColor.GOLD),
    HEROIC(ChatColor.LIGHT_PURPLE),
    SOUL(ChatColor.RED),
    MASTERY(ChatColor.DARK_RED),
    PIXEL(ChatColor.BLUE);

    private final ChatColor color;

    EnchantRarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }

}
