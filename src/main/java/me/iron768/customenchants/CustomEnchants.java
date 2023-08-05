package me.iron768.customenchants;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import me.iron768.customenchants.command.Commands;
import me.iron768.customenchants.listener.Listeners;
import me.iron768.customenchants.listener.impl.ArmorChangeListener;
import me.iron768.customenchants.manager.Managers;
import me.iron768.customenchants.task.Tasks;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomEnchants extends JavaPlugin {

    private static CustomEnchants instance;

    private Managers managers;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        ArmorEquipEvent.registerListener(this);
        new ArmorChangeListener(this);

        this.managers = new Managers(this);

        new Commands(this);
        new Listeners(this);

        new Tasks(this); // call later!
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        instance = null;
    }

    public static CustomEnchants getInstance() {
        return instance;
    }

    public Managers getManagers() {
        return managers;
    }

}
