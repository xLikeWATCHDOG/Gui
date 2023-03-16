package me.huanmeng.opensource.bukkit.gui.slot.impl.slots;

import com.google.common.primitives.Chars;
import me.huanmeng.opensource.bukkit.gui.AbstractGui;
import me.huanmeng.opensource.bukkit.gui.slot.Slot;
import me.huanmeng.opensource.bukkit.gui.slot.Slots;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 2022/9/28<br>
 * LimeCode<br>
 *
 * @author huanmeng_qwq
 */
public class PatternLineSlots implements Slots {
    private final Function<Integer, String[]> patternFun;
    private final List<Character> chars;

    public PatternLineSlots(Function<Integer, String[]> pattern, char... chars) {
        this.patternFun = pattern;
        this.chars = new ArrayList<>(chars.length);
        this.chars.addAll(Chars.asList(chars));
    }

    @Override
    public <G extends AbstractGui<G>> Slot[] slots(G gui) {
        String[] pattern = patternFun.apply(gui.size() / 9);
        List<Slot> list = new ArrayList<>(pattern.length);
        for (int y = 0; y < pattern.length; y++) {
            String line = pattern[y];
            char[] chars = line.toCharArray();
            for (int x = 0; x < chars.length; x++) {
                if (this.chars.contains(chars[x])) {
                    list.add(Slot.ofBukkit(x, y));
                }
            }
        }
        return list.toArray(new Slot[0]);
    }

    public Function<Integer, String[]> patternFun() {
        return patternFun;
    }
}