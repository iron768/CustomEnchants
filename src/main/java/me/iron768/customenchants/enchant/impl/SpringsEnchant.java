package me.iron768.customenchants.enchant.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.enchant.EnchantApplication;
import me.iron768.customenchants.enchant.EnchantRarity;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.util.PotionEffectUtil;
import me.iron768.customenchants.util.RomanNumberUtil;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpringsEnchant extends Enchant {

    private final CustomEnchants plugin;

    public SpringsEnchant(CustomEnchants plugin) {
        super("Springs", "Gives you a jump boost", EnchantRarity.ELITE, EnchantApplication.BOOTS, EnchantType.ARMOR_EQUIP, 3);

        this.plugin = plugin;
    }

    @Override
    public void effect(PlayerData playerData, ItemStack item, int level) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        switch (level) {
            case 1:
                PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0, true, true), reason);
                break;
            case 2:
                PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1, true, true), reason);
                break;
            case 3:
                PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2, true, true), reason);
                break;
        }
    }

    @Override
    public void removeEffect(PlayerData playerData, ItemStack item, int level) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        PotionEffectUtil.removePotionEffect(playerData, PotionEffectType.JUMP, reason);
    }

}
