package me.iron768.customenchants.listener;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.listener.impl.ItemChangeListener;
import me.iron768.customenchants.listener.impl.PlayerArmorListener;
import me.iron768.customenchants.listener.impl.PlayerItemListener;

public class Listeners {

    public Listeners(CustomEnchants plugin) {
        new ItemChangeListener(plugin);

        new PlayerArmorListener(plugin);
        new PlayerItemListener(plugin);
    }

}
