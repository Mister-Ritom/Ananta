package me.ritomg.ananta.hud;

import me.ritomg.ananta.hud.huds.*;
import me.ritomg.ananta.module.Category;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    public static ArrayList<Hud> huds = new ArrayList<>();

    public static void init() {
        huds.add(new Watermark());
        huds.add(new Notification());
        huds.add(new Coordinates());
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
