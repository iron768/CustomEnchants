package me.iron768.customenchants.util;

import me.iron768.customenchants.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectUtil {

    public static void addPotionEffect(PlayerData playerData, PotionEffect potionEffect, String reason) {
        Player bukkitPlayer = playerData.getBukkitPlayer();

        boolean hasEffect = bukkitPlayer.getActivePotionEffects()
                .stream()
                .anyMatch(effect -> effect.getType().equals(potionEffect.getType()));

        if (hasEffect) {
            bukkitPlayer.removePotionEffect(potionEffect.getType());
        }

        bukkitPlayer.addPotionEffect(potionEffect);

        int amplifier = potionEffect.getAmplifier();

        String amplifierString = amplifier == 0 ? "" : " " + RomanNumberUtil.toRoman(amplifier + 1);

        bukkitPlayer.sendMessage(MessageUtil.color("&b[+] " + reason + ":&7 applying " + potionEffect.getType().getName() + amplifierString));
    }

    public static void removePotionEffect(PlayerData playerData, PotionEffectType potionEffectType, String reason) {
        Player bukkitPlayer = playerData.getBukkitPlayer();

        bukkitPlayer.removePotionEffect(potionEffectType);

        bukkitPlayer.sendMessage(MessageUtil.color("&c[-] " + reason + ":&7 removing " + potionEffectType.getName()));
    }

}
