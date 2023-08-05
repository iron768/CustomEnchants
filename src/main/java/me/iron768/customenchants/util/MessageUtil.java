package me.iron768.customenchants.util;

import org.bukkit.ChatColor;

public class MessageUtil {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
