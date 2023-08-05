package me.iron768.customenchants.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class ChangeItemEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private ItemStack oldItem, newItem;
    private Player player;

    /**
     * @param player  the player
     * @param oldItem the old item
     * @param newItem the new item
     */
    public ChangeItemEvent(Player player, ItemStack oldItem, ItemStack newItem) {
        super(player);

        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }


    public final static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public ItemStack getOldItem() {
        return oldItem;
    }

    public ItemStack getNewItem() {
        return newItem;
    }

}
