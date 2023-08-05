package me.iron768.customenchants.enchant.impl;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.enchant.EnchantApplication;
import me.iron768.customenchants.enchant.EnchantRarity;
import me.iron768.customenchants.enchant.EnchantType;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.util.PotionEffectUtil;
import me.iron768.customenchants.util.RomanNumberUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GearsEnchant extends Enchant {

    private final CustomEnchants plugin;

    public GearsEnchant(CustomEnchants plugin) {
        super("Gears", "A permanent speed boost", EnchantRarity.LEGENDARY, EnchantApplication.BOOTS, EnchantType.ARMOR_EQUIP, 3);

        this.plugin = plugin;
    }

    @Override
    public void effect(PlayerData playerData, ItemStack item, int level) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level - 1, true, true), reason);

    }

    @Override
    public void removeEffect(PlayerData playerData, ItemStack item, int level) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        PotionEffectUtil.removePotionEffect(playerData, PotionEffectType.SPEED, reason);
    }

}
