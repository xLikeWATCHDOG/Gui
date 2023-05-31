package me.huanmeng.opensource.bukkit.gui.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * 2023/3/17<br>
 * Gui<br>
 *
 * @author huanmeng_qwq
 */
public interface GuiEmptyItemClick {
    /**
     * 点击空白按钮时触发
     *
     * @param player    玩家
     * @param slot      位置
     * @param clickType 点击类型
     * @param action    点击事件
     * @param slotType  位置类型
     * @param hotBar    热键
     * @param e         事件
     * @return 是否cancel事件
     */
    boolean onClickEmptyButton(Player player, int slot, ClickType clickType, InventoryAction action, InventoryType.SlotType slotType, int hotBar, InventoryClickEvent e);
}
