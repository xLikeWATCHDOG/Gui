package me.huanmeng.opensource.bukkit.gui.button;

import me.huanmeng.opensource.bukkit.gui.button.function.PlayerClickCancelInterface;
import me.huanmeng.opensource.bukkit.gui.button.function.PlayerClickInterface;
import me.huanmeng.opensource.bukkit.gui.button.function.PlayerSimpleCancelInterface;
import me.huanmeng.opensource.bukkit.gui.button.function.UserItemInterface;
import me.huanmeng.opensource.bukkit.gui.enums.Result;
import me.huanmeng.opensource.bukkit.gui.slot.Slot;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2023/3/17<br>
 * Gui<br>
 *
 * @author huanmeng_qwq
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface Button {
    /**
     * 获取显示物品
     *
     * @param player 玩家
     * @return 物品
     */
    ItemStack getShowItem(Player player);

    /**
     * 空按钮
     */
    Button EMPTY = (p) -> null;

    default Result onClick(Slot slot, Player player, ClickType click, InventoryAction action, InventoryType.SlotType slotType, int slotKey, int hotBarKey, InventoryClickEvent e) {
        return Result.CANCEL;
    }

    /**
     * 展示型按钮
     *
     * @param item 物品
     * @return {@link Button}
     */
    static Button of(UserItemInterface item) {
        return new EmptyButton(item);
    }

    /**
     * 点击型按钮
     *
     * @param item      物品
     * @param clickable 点击事件
     * @return {@link Button}
     */
    static Button of(UserItemInterface item, PlayerClickInterface clickable) {
        return new ClickButton(item, clickable);
    }

    /**
     * 点击型按钮
     *
     * @param item      物品
     * @param clickable 点击事件
     * @return {@link Button}
     */
    static Button of(UserItemInterface item, PlayerClickCancelInterface clickable) {
        return new ClickButton(item, clickable);
    }

    /**
     * 点击型按钮
     *
     * @param item                        物品
     * @param playerSimpleCancelInterface 点击事件
     * @return {@link Button}
     */
    static Button of(UserItemInterface item, PlayerSimpleCancelInterface playerSimpleCancelInterface) {
        return new ClickButton(item, playerSimpleCancelInterface);
    }

    /**
     * 点击型按钮
     *
     * @param impl   实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @param <IMPL> 实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @return {@link Button}
     */
    static <IMPL extends UserItemInterface & PlayerClickInterface> Button ofInstance(IMPL impl) {
        return new ClickButton(impl, impl);
    }

    /**
     * 点击型按钮
     *
     * @param impl   实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @param <IMPL> 实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @return {@link Button}
     */
    static <IMPL extends UserItemInterface & PlayerClickInterface> List<Button> ofInstances(Collection<IMPL> impl) {
        return impl.stream().map(Button::ofInstance).collect(Collectors.toList());
    }

    /**
     * 点击型按钮
     *
     * @param impl   实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @param <IMPL> 实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @return {@link Button}
     */
    @SafeVarargs
    static <IMPL extends UserItemInterface & PlayerClickInterface> List<Button> ofInstancesArray(IMPL... impl) {
        return Arrays.stream(impl).map(Button::ofInstance).collect(Collectors.toList());
    }

    /**
     * 通过映射与物品集合合并按钮
     *
     * @param itemStacks 物品
     * @param map        映射
     * @return {@link Button}
     */
    static List<Button> ofItemStacksButton(Collection<ItemStack> itemStacks, Function<ItemStack, Button> map) {
        return itemStacks.stream().map(map).collect(Collectors.toList());
    }

    /**
     * 展示型按钮
     *
     * @param itemStacks 物品
     * @param map        映射
     * @return {@link Button}
     */
    static List<Button> ofItemStacks(Collection<ItemStack> itemStacks, Function<ItemStack, UserItemInterface> map) {
        return itemStacks.stream().map(map).map(Button::of).collect(Collectors.toList());
    }

    /**
     * 展示型按钮
     *
     * @param itemStacks 物品
     * @return {@link Button}
     */
    static List<Button> ofItemStacks(Collection<ItemStack> itemStacks) {
        return itemStacks.stream().map(e -> (UserItemInterface) player -> e).map(Button::of).collect(Collectors.toList());
    }

    /**
     * 点击型按钮
     *
     * @param itemStacks 物品
     * @param map        映射
     * @param <IMPL>     实现了{@link UserItemInterface}和{@link PlayerClickInterface}的类
     * @return {@link Button}
     */
    static <IMPL extends UserItemInterface & PlayerClickInterface> List<Button> ofItemStacksInstances(Collection<ItemStack> itemStacks, Function<ItemStack, IMPL> map) {
        return itemStacks.stream().map(map).map(Button::ofInstance).collect(Collectors.toList());
    }

    static Button empty() {
        return EMPTY;
    }
}
