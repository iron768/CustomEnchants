package me.iron768.customenchants.task;

import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.task.impl.HandlePassiveTask;

public class Tasks {

    public Tasks(CustomEnchants plugin) {
        new HandlePassiveTask(plugin).run();
    }

}
