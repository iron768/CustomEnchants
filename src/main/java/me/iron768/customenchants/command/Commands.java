package me.iron768.customenchants.command;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.command.impl.CustomEnchantCommand;
import me.iron768.customenchants.command.impl.DebugCommand;

public class Commands {

    public Commands(CustomEnchants plugin) {
        new DebugCommand(plugin);
        new CustomEnchantCommand(plugin);
    }

}
