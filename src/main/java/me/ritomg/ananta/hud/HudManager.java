package me.ritomg.ananta.hud;

import me.ritomg.ananta.hud.huds.Cords;
import me.ritomg.ananta.hud.huds.Watermark;
import me.ritomg.ananta.module.Category;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    public static ArrayList<Hud> huds = new ArrayList<>();

    public static void init() {
        huds.add(new Watermark());
    }

    public static Hud getHud(Class clazz) {
        for (Hud h : huds) {
            if (h.getClass() == clazz) return h;
        }
        return null;
    }

    public static List<Hud> getHudInCategory(Category c) {
        List<Hud> hudList = new ArrayList<>();
        for (Hud h : huds) {
            if (h.getCategory().equals(c)) hudList.add(h);
        }
        return hudList;
    }

}
