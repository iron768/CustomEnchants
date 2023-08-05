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

public class DrunkEnchant extends Enchant {

    private CustomEnchants plugin;

    public DrunkEnchant(CustomEnchants plugin) {
        super("Drunk", "You will swing slowly and move slowly, but you will have buffed strength", EnchantRarity.LEGENDARY, EnchantApplication.HELMET, EnchantType.ARMOR_EQUIP, 4);

        this.plugin = plugin;
    }

    @Override
    public void effect(PlayerData playerData, ItemStack item, int level) {
        switch (level) {
            case 1:
                this.applyPotionEffects(playerData, level, 0, 0, 1);
                break;
            case 2:
                this.applyPotionEffects(playerData, level, 0, 1, 1);
                break;
            case 3:
                this.applyPotionEffects(playerData, level, 1, 3, 1);
                break;
            case 4:
                this.applyPotionEffects(playerData, level, 2, 2, 0);
                break;
        }
    }

    @Override
    public void removeEffect(PlayerData playerData, ItemStack item, int level) {
        this.removePotionEffects(playerData, level);
    }

    private void applyPotionEffects(PlayerData playerData, int level, int amp1, int amp2, int amp3) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        PotionEffectType increaseDamage = PotionEffectType.INCREASE_DAMAGE;
        PotionEffectType slow = PotionEffectType.SLOW;
        PotionEffectType slowDigging = PotionEffectType.SLOW_DIGGING;

        PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(increaseDamage, Integer.MAX_VALUE, amp1, false, true), reason);
        PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(slow, Integer.MAX_VALUE, amp2, false, true), reason);
        PotionEffectUtil.addPotionEffect(playerData, new PotionEffect(slowDigging, Integer.MAX_VALUE, amp3, false, true), reason);
    }

    private void removePotionEffects(PlayerData playerData, int level) {
        String reason = getName() + " " + RomanNumberUtil.toRoman(level);

        PotionEffectType increaseDamage = PotionEffectType.INCREASE_DAMAGE;
        PotionEffectType slow = PotionEffectType.SLOW;
        PotionEffectType slowDigging = PotionEffectType.SLOW_DIGGING;

        PotionEffectUtil.removePotionEffect(playerData, increaseDamage, reason);
        PotionEffectUtil.removePotionEffect(playerData, slow, reason);
        PotionEffectUtil.removePotionEffect(playerData, slowDigging, reason);
    }

}
