package me.huanmeng.opensource.bukkit.gui.slot;

import me.huanmeng.opensource.bukkit.gui.AbstractGui;
import me.huanmeng.opensource.bukkit.gui.slot.impl.slots.ArraySlots;
import me.huanmeng.opensource.bukkit.gui.slot.impl.slots.ExcludeSlots;
import me.huanmeng.opensource.bukkit.gui.slot.impl.slots.PatternLineSlots;
import me.huanmeng.opensource.bukkit.gui.slot.impl.slots.PatternSlots;
import me.huanmeng.opensource.bukkit.util.MathUtil;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 2023/3/17<br>
 * Gui<br>
 *
 * @author huanmeng_qwq
 */
@SuppressWarnings("unused")
public interface Slots {
    @NonNull
    PatternLineSlots PATTERN_LINE_DEFAULT = Slots.patternLine((line -> {
        List<String> list = new ArrayList<>(line);
        if (line <= 2) {
            list.add("xxxxxxxxx");
            list.add("xxxxxxxxx");
            return list.toArray(new String[0]);
        }
        list.add("aaaaaaaaa");
        for (int i = 0; i < line - 2; i++) {
            list.add("axxxxxxxa");
        }
        list.add("aaaaaaaaa");
        return list.toArray(new String[0]);
    }), 'x');

    PatternLineSlots PATTERN_LINE_PAGE_DEFAULT = Slots.patternLine((line -> {
        List<String> list = new ArrayList<>(line);
        if (line <= 2) {
            list.add("xxxxxxxxx");
            list.add("xxxxxxxxx");
            return list.toArray(new String[0]);
        }
        for (int i = 0; i < line - 1; i++) {
            list.add("xxxxxxxxx");
        }
        list.add("axxxxxxxa");
        return list.toArray(new String[0]);
    }), 'x');

    @NonNull <@NonNull G extends AbstractGui<@NonNull G>> Slot[] slots(@NonNull G gui);

    @Contract(value = "_ -> new", pure = true)
    static Slots of(Slot... slot) {
        return new ArraySlots(slot);
    }

    @Contract(value = "_ -> new", pure = true)
    static Slots of(int... slots) {
        return new ArraySlots(Arrays.stream(slots).mapToObj(Slot::of).toArray(Slot[]::new));
    }

    /**
     * 用图案表达位置
     */
    @Contract(value = "!null, _ -> new", pure = true)
    static Slots pattern(String[] pattern, char... chars) {
        return new PatternSlots(pattern, chars);
    }

    /**
     * 用图案表达位置
     */
    @Contract(value = "!null, _ -> new", pure = true)
    static PatternLineSlots patternLine(Function<@NonNull Integer, @NonNull String[]> pattern, char @NonNull ... chars) {
        return new PatternLineSlots(pattern, chars);
    }

    @Contract(value = "-> !null", pure = true)
    static Slots patternLineDefault() {
        return PATTERN_LINE_DEFAULT;
    }

    @Contract(value = "_, _-> new", pure = true)
    static Slots range(int min, int max) {
        return of(MathUtil.range(min, max));
    }

    @Contract(value = "_-> new", pure = true)
    static Slots exclude(int... slots) {
        return new ExcludeSlots(slots);
    }

    @Contract(value = "_, _-> new", pure = true)
    static Slots excludePattern(String[] pattern, char... chars) {
        return new ExcludeSlots(pattern(pattern, chars));
    }

    @Contract(value = "_, _-> new", pure = true)
    static Slots excludeRange(int min, int max) {
        return new ExcludeSlots(range(min, max));
    }
}
