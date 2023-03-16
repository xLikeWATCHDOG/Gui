package me.huanmeng.opensource.bukkit.gui.button.function;

import me.huanmeng.opensource.bukkit.gui.enums.Result;
import me.huanmeng.opensource.bukkit.gui.slot.Slot;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;

@FunctionalInterface
public interface PlayerSimpleCancelUpdateAllInterface extends PlayerClickInterface {
    @Override
    default Result onClick(Slot slot, Player player, ClickType click, InventoryAction action, InventoryType.SlotType slotType, int slotKey, int hotBarKey) {
        onPlayerClick(player);
        return Result.CANCEL_UPDATE_ALL;
    }

    void onPlayerClick(Player player);
}