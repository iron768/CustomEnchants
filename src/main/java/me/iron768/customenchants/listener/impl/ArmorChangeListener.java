package me.iron768.customenchants.listener.impl;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import com.jeff_media.armorequipevent.ArmorType;
import me.iron768.customenchants.CustomEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Bug: Swapping armor when already wearing armor does not call the armor change event
Cause: if(newArmorType.equals(ArmorType.HELMET) && isEmpty(event.getPlayer().getInventory().getHelmet())...
The isEmpty checks if the slot is currently empty, so it ignores when a player is swapping their gear when currently wearing gear

This "patch" is necessary since the current api has no capabilities for handling armor swapping (despite it being relatively simple given the code already present)

The following code is just copied from the ArmorListener just modified to handle swapping armor from hotbar

Eventually I should make a pull request to fix this issue, for now it stays here.
 */
public class ArmorChangeListener implements Listener {

    private List<Material> blockedMaterials;

    public ArmorChangeListener(CustomEnchants plugin) {
        // TODO: check this please
        blockedMaterials = new ArrayList<>(Arrays.asList(
                Material.FURNACE, Material.CHEST, Material.TRAPPED_CHEST, Material.BEACON, Material.DISPENSER, Material.DROPPER, Material.HOPPER, Material.CRAFTING_TABLE,
                Material.ENCHANTING_TABLE, Material.ENDER_CHEST, Material.ANVIL, Material.LEGACY_BED, Material.LEGACY_FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.BIRCH_FENCE_GATE,
                Material.ACACIA_FENCE_GATE, Material.JUNGLE_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.LEGACY_IRON_DOOR_BLOCK, Material.LEGACY_WOODEN_DOOR, Material.SPRUCE_DOOR,
                Material.BIRCH_DOOR, Material.JUNGLE_DOOR, Material.ACACIA_DOOR, Material.DARK_OAK_DOOR, Material.LEGACY_WOOD_BUTTON, Material.STONE_BUTTON, Material.LEGACY_TRAP_DOOR,
                Material.IRON_TRAPDOOR, Material.LEGACY_DIODE_BLOCK_OFF, Material.LEGACY_DIODE_BLOCK_ON, Material.LEGACY_REDSTONE_COMPARATOR_OFF, Material.LEGACY_REDSTONE_COMPARATOR_ON,
                Material.LEGACY_FENCE, Material.SPRUCE_FENCE, Material.BIRCH_FENCE, Material.JUNGLE_FENCE, Material.DARK_OAK_FENCE, Material.ACACIA_FENCE, Material.LEGACY_NETHER_FENCE,
                Material.BREWING_STAND, Material.CAULDRON, Material.LEGACY_SIGN_POST, Material.LEGACY_WALL_SIGN, Material.LEGACY_SIGN, Material.ACACIA_SIGN, Material.ACACIA_WALL_SIGN,
                Material.BIRCH_SIGN, Material.BIRCH_WALL_SIGN, Material.DARK_OAK_SIGN, Material.DARK_OAK_WALL_SIGN, Material.JUNGLE_SIGN, Material.JUNGLE_WALL_SIGN, Material.OAK_SIGN,
                Material.OAK_WALL_SIGN, Material.SPRUCE_SIGN, Material.SPRUCE_WALL_SIGN, Material.LEVER, Material.BLACK_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX,
                Material.CYAN_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX,
                Material.ORANGE_SHULKER_BOX, Material.PINK_SHULKER_BOX, Material.PURPLE_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.LEGACY_SILVER_SHULKER_BOX, Material.WHITE_SHULKER_BOX,
                Material.YELLOW_SHULKER_BOX, Material.LEGACY_DAYLIGHT_DETECTOR_INVERTED, Material.DAYLIGHT_DETECTOR, Material.BARREL, Material.BLAST_FURNACE, Material.SMOKER,
                Material.CARTOGRAPHY_TABLE, Material.COMPOSTER, Material.GRINDSTONE, Material.LECTERN, Material.LOOM, Material.STONECUTTER, Material.BELL
        ));

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent event) {
        if (event.useItemInHand().equals(Event.Result.DENY)) return;

        if (event.getAction() == Action.PHYSICAL) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Player player = event.getPlayer();

            if (!event.useInteractedBlock().equals(Event.Result.DENY)) {
                if (event.getClickedBlock() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking()) {// Having both of these checks is useless, might as well do it though.
                    // Some blocks have actions when you right click them which stops the client from equipping the armor in hand.
                    Material mat = event.getClickedBlock().getType();

                    if (blockedMaterials.contains(mat))
                        return;
                }
            }

            ArmorType newArmorType = ArmorType.matchType(event.getItem());

            // Carved pumpkins cannot be equipped using right-click
            if (event.getItem() != null && event.getItem().getType() == Material.CARVED_PUMPKIN)
                return;

            if (newArmorType == null)
                return;

            for (ItemStack armor : player.getInventory().getArmorContents()) {
                ArmorType oldArmorType = ArmorType.matchType(armor);

                if (oldArmorType == newArmorType) {
                    ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(event.getPlayer(), ArmorEquipEvent.EquipMethod.HOTBAR, newArmorType, armor, event.getItem());

                    Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);

                    if (armorEquipEvent.isCancelled()) {
                        event.setCancelled(true);

                        player.updateInventory();
                    }
                }
            }
        }
    }

}
