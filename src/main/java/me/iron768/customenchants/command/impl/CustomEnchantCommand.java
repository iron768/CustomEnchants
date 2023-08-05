package me.iron768.customenchants.command.impl;

import com.jeff_media.armorequipevent.ArmorType;
import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.enchant.Enchant;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import me.iron768.customenchants.util.MathUtil;
import me.iron768.customenchants.util.MessageUtil;
import me.iron768.customenchants.util.RomanNumberUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantCommand implements CommandExecutor {

    private final CustomEnchants plugin;

    // dependency injection
    public CustomEnchantCommand(CustomEnchants plugin) {
        this.plugin = plugin;

        this.plugin.getCommand("customenchant").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No!"); // TODO: formatting and messages later :(
            return true;
        }

        // we can cast now safely
        Player player = (Player) sender;
        PlayerData playerData = this.plugin.getManagers().getPlayerDataManager().getPlayerData(player);

        //if (!player.hasPermission("customenchants.commands.customenchant")) {
        //sender.sendMessage(MessageUtil.color("&cNo permission.")); // TODO: ^^^^^
        //return true;
        //}

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            player.sendMessage(MessageUtil.color("&eYou must be holding something!"));
            return true;
        }

        Enchant enchant = this.plugin.getManagers().getEnchantManager().getEnchantByName(args[0]);

        if (enchant == null) {
            player.sendMessage(MessageUtil.color("&cThat enchant does not exist or could not be found."));
            return true;
        }

        int level = MathUtil.randomInteger(enchant.getMaxLevel());

        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("max")) {
                level = enchant.getMaxLevel();
            } else {
                level = Integer.parseInt(args[1]);
            }
        }

        this.plugin.getManagers().getEnchantManager().applyEnchant(playerData, enchant, level, itemStack);

        return false;
    }

}
