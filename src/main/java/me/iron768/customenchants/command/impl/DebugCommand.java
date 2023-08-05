package me.iron768.customenchants.command.impl;

import de.tr7zw.nbtapi.NBTItem;
import me.iron768.customenchants.CustomEnchants;
import me.iron768.customenchants.player.PlayerData;
import me.iron768.customenchants.player.PlayerEnchantItemData;
import me.iron768.customenchants.player.PlayerEnchantedItem;
import me.iron768.customenchants.util.MessageUtil;
import me.iron768.customenchants.util.PotionEffectUtil;
import me.iron768.customenchants.util.RomanNumberUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand implements CommandExecutor {

    private final CustomEnchants plugin;

    // dependency injection
    public DebugCommand(CustomEnchants plugin) {
        this.plugin = plugin;

        this.plugin.getCommand("debug").setExecutor(this);
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

        if (args.length == 1 && args[0].equalsIgnoreCase("refresh")) {
            this.refreshPlayer(playerData);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("keyval")) {
            this.sendKeyAndVals(playerData);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("health")) {
            this.displayHealth(playerData);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("nbt")) {
            if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                player.sendMessage(MessageUtil.color("&cYou must be holding something when preforming this command."));
                return true;
            }

            this.displayNbtData(player, player.getItemInHand());
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("roman")) {
            int number = Integer.parseInt(args[1]);

            player.sendMessage(MessageUtil.color("&aRoman numeral conversion for &l" + number + " &r&ais &l" + RomanNumberUtil.toRoman(number)));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("level")) {
            int level = Integer.parseInt(args[1]);

            if (level > 4) {
                player.sendMessage(MessageUtil.color("&cThat level does not exist!"));
                return true;
            }

            playerData.setDebugLevel(level);

            player.sendMessage(MessageUtil.color("&aYou set your debug level to: &l" + level));
            return true;
        }

        //if (!player.hasPermission("customenchants.commands.debug")) {
        //sender.sendMessage(MessageUtil.color("&cNo permission.")); // TODO: ^^^^^
        //return true;
        //}

        player.sendMessage(MessageUtil.color("&7======================="));
        player.sendMessage(MessageUtil.color("&6Current enchants: "));

        playerData.getPlayerEnchantHolder().getEnchantedItems().forEach((key, value) -> {
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&bItem: " + key.getType().name() + ", Enchant: " + getEnchantNameList(value)));
        });

        player.sendMessage(MessageUtil.color("&7======================="));

        return false;
    }

    public String getEnchantNameList(PlayerEnchantedItem enchantItemData) {
        List<String> enchantNames = new ArrayList<>();

        for (PlayerEnchantItemData enchantItemDataz : enchantItemData.getEnchantItemDataList()) {
            enchantNames.add(enchantItemDataz.getEnchant().getName() + " " + RomanNumberUtil.toRoman(enchantItemDataz.getLevel()));
        }

        return enchantNames.toString();
    }

    private void refreshPlayer(PlayerData playerData) {
        playerData.getBukkitPlayer().setMaxHealth(20);
        playerData.getBukkitPlayer().setHealth(playerData.getBukkitPlayer().getMaxHealth());

        playerData.getBukkitPlayer().setSaturation(20);

        // clearing enchanted items DANGEROUS? idk
        playerData.getPlayerEnchantHolder().getEnchantedItems().clear();

        for (PotionEffect potionEffect : playerData.getBukkitPlayer().getActivePotionEffects())
            PotionEffectUtil.removePotionEffect(playerData, potionEffect.getType(), "Admin Refresh");


        playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&aYou have been refreshed"));
    }

    private void displayNbtData(Player player, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);

        if (!nbtItem.hasNBTData()) {
            player.sendMessage(MessageUtil.color("&eThis item does not have any NBT data or it could not be found."));
            return;
        }

        for (String nbtData : nbtItem.getKeys()) {
            player.sendMessage(MessageUtil.color("&bNBT DATA &f- &b&l" + nbtData));
        }
    }

    private void sendKeyAndVals(PlayerData playerData) {
        playerData.getPlayerEnchantHolder().getEnchantedItems().forEach((key, value) -> {
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&7==== DEBUG ===="));
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&bKey: " + key + ", Value: " + value));
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&7==== DEBUG END ===="));
        });
    }

    private void displayHealth(PlayerData playerData) {
        playerData.getEnchantHearts().forEach((key, value) -> {
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&7==== Health ===="));
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&bReason: " + key + ", Health Value: " + value));
            playerData.getBukkitPlayer().sendMessage(MessageUtil.color("&7==== Health END ===="));
        });
    }

}
