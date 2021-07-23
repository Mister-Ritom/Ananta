package me.ritomg.ananta.module;

import me.ritomg.ananta.module.modules.client.ClickGui;
import me.ritomg.ananta.module.modules.client.CustomFont;
import me.ritomg.ananta.module.modules.client.HudEditor;
import me.ritomg.ananta.module.modules.client.ToggleMessage;
import me.ritomg.ananta.module.modules.combat.AutoXp;
import me.ritomg.ananta.module.modules.combat.Critical;
import me.ritomg.ananta.module.modules.combat.KillAura;
import me.ritomg.ananta.module.modules.exploits.Fastutil;
import me.ritomg.ananta.module.modules.misc.AntiAfk;
import me.ritomg.ananta.module.modules.misc.AutoClick;
import me.ritomg.ananta.module.modules.misc.AutoReply;
import me.ritomg.ananta.module.modules.misc.AutoSpam;
import me.ritomg.ananta.module.modules.movement.PlayerMovements;
import me.ritomg.ananta.module.modules.movement.PlayerTweaks;
import me.ritomg.ananta.module.modules.render.ESP;
import me.ritomg.ananta.module.modules.render.FullBright;
import me.ritomg.ananta.module.modules.theme.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private static List<Module> modules;

    public static void init() {
        modules = new ArrayList<>();
        //themes
        addnewModule(new ClickGui());
        addnewModule(new ClearTheme());
        addnewModule(new ImpactTheme());
        addnewModule(new RainbowTheme());
        addnewModule(new WindowsTheme());
 //       addnewModule(new AnantaThemeModule());
        addnewModule(new GamesenseThemeModule());

        addnewModule(new PlayerMovements());
        addnewModule(new Fastutil());
        addnewModule(new AutoXp());
        addnewModule(new FullBright());
        addnewModule(new AutoReply());
        addnewModule(new ToggleMessage());
        addnewModule(new HudEditor());
        addnewModule(new CustomFont());
        addnewModule(new AutoSpam());
        addnewModule(new AutoClick());
        addnewModule(new AntiAfk());
        addnewModule(new PlayerTweaks());
        addnewModule(new KillAura());
        addnewModule(new ESP());
        addnewModule(new Critical());
    }

    public static List<Module> getModulesinCategory(Category c) {
        List<Module> module = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory().equals(c)) {
                module.add(m);
            }
        }
        return module;
    }

    public static void addnewModule(Module m) {
        if (!modules.contains(m))
            modules.add(m);
    }

    public static Module getModule(String name) {
        for (Module m : ModuleManager.getModules()) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public static <T extends Module> T getModule(Class<T> clazz) {
        for (Module m : modules) {
            if (m.getClass().equals(clazz)) {
                return (T) m;
            }
        }
        return null;
    }

    public static List<Module> getModules() {
        return modules;
    }
}
