package me.iron768.customenchants.player;

import me.iron768.customenchants.enchant.Enchant;

public class PlayerEnchantItemData {

    private Enchant enchant;
    private int level;

    public PlayerEnchantItemData(Enchant enchant, int level) {
        this.enchant = enchant;
        this.level = level;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    public void setEnchant(Enchant enchant) {
        this.enchant = enchant;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
