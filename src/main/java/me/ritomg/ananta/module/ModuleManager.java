package me.ritomg.ananta.module;

import me.ritomg.ananta.module.modules.client.*;
import me.ritomg.ananta.module.modules.misc.*;
import me.ritomg.ananta.module.modules.movement.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private static List<Module> modules;

    public static void init() {
        modules = new ArrayList<>();
        addnewModule(new ClickGui());
        addnewModule(new Sprint());
        addnewModule(new ChatUtils());
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
        modules.add(m);
    }

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
