package me.iron768.customenchants.enchant;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnchantApplication {

    HELMET(Material.LEATHER_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET),
    CHESTPLATE(Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE),
    LEGGINGS(Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS),
    BOOTS(Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS),
    SWORD(Material.WOODEN_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD),
    BOW(Material.BOW),
    PICKAXE(Material.WOODEN_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE),
    SHOVEL(Material.WOODEN_SHOVEL, Material.IRON_SHOVEL, Material.STONE_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL),
    AXE(Material.WOODEN_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE),
    HOE(Material.WOODEN_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE),
    TOOLS(EnchantApplication.PICKAXE, EnchantApplication.SHOVEL, EnchantApplication.AXE, EnchantApplication.HOE),
    ARMOR(EnchantApplication.HELMET, EnchantApplication.CHESTPLATE, EnchantApplication.LEGGINGS, EnchantApplication.BOOTS),
    WEAPONS(EnchantApplication.SWORD, EnchantApplication.AXE, EnchantApplication.BOW),
    WEAPONSANDTOOLS(EnchantApplication.TOOLS, EnchantApplication.WEAPONS),
    SWORDAXE(EnchantApplication.SWORD, EnchantApplication.AXE),
    SWORDBOW(EnchantApplication.SWORD, EnchantApplication.BOW);

    private List<Material> items = new ArrayList<>();

    EnchantApplication(Material... itemss) {
        Collections.addAll(items, itemss);
    }

    EnchantApplication(EnchantApplication... applications) {
        for (EnchantApplication application : applications) {
            items.addAll(application.getItems());
        }
    }

    public List<Material> getItems() {
        return items;
    }

}
