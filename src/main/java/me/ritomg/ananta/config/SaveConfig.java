package me.ritomg.ananta.config;

import com.google.gson.*;
import me.ritomg.ananta.command.CommandManager;
import me.ritomg.ananta.hud.Hud;
import me.ritomg.ananta.hud.HudManager;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// thanks lukflug
public class SaveConfig {

    public static final String Ananta = "Ananta/";
    private static final String modulesPath = "Modules/";
    private static final String hudsPath = "HUDS/";

    public static void init() {
        try {
            saveConfig();
            saveModules();
            saveGuiPos();
            saveCommandPrefix();
            saveHuds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveConfig() throws IOException {
        if (!Files.exists(Paths.get(Ananta))) {
            Files.createDirectories(Paths.get(Ananta));
        }
        if (!Files.exists(Paths.get(Ananta + modulesPath))) {
            Files.createDirectories(Paths.get(Ananta + modulesPath));
        }
        if (!Files.exists(Paths.get(Ananta + hudsPath))) {
            Files.createDirectories(Paths.get(Ananta + hudsPath));
        }
        if (!Files.exists(Paths.get(Ananta + "Main/"))) {
            Files.createDirectories(Paths.get(Ananta + "Main/"));
        }
    }

    private static void registerFiles(String location, String name) throws IOException {
        if (Files.exists(Paths.get(Ananta + location + name + ".json"))) {
            File file = new File(Ananta + location + name + ".json");

            file.delete();

        }
        Files.createFile(Paths.get(Ananta + location + name + ".json"));
    }
//TODO do it in one method
    private static void saveHuds() throws IOException {
        for (Hud module : HudManager.huds) {
            try {
                saveHudsDirect(module);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveHudsDirect(Hud module) throws IOException {
        registerFiles(hudsPath, module.getName());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(Ananta + hudsPath + module.getName() + ".json"), StandardCharsets.UTF_8);
        JsonObject moduleObject = new JsonObject();
        JsonObject settingObject = new JsonObject();
//        JsonObject positionObject = new JsonObject();
        moduleObject.add("Hud", new JsonPrimitive(module.getName()));
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((BooleanSetting) setting).isOn()));
            } else if (setting instanceof NumberSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((NumberSetting) setting).getCurrent()));
            } else if (setting instanceof DNumberSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((DNumberSetting) setting).getCurrent()));
            } else if (setting instanceof ColourSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((ColourSetting)setting).getColorRGB()));
            }
            else if (setting instanceof ModeSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((ModeSetting) setting).getCurrentMode()));
            } else if (setting instanceof StringSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((StringSetting) setting).getText()));
            }
        }
//        positionObject.add("X", new JsonPrimitive(module.x));
//        positionObject.add("Y", new JsonPrimitive(module.y));
        settingObject.add("Enabled", new JsonPrimitive(module.isEnabled()));
        settingObject.add("Bind", new JsonPrimitive(module.getBind()));
        moduleObject.add("Settings", settingObject);
//        moduleObject.add("Position", positionObject);
        String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }

    private static void saveModules() throws IOException {
        for (Module module : ModuleManager.getModules()) {
            try {
                saveModuleDirect(module);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveModuleDirect(Module module) throws IOException {
        registerFiles(modulesPath, module.getName());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(Ananta + modulesPath + module.getName() + ".json"), StandardCharsets.UTF_8);
        JsonObject moduleObject = new JsonObject();
        JsonObject settingObject = new JsonObject();
        JsonObject subSettingsObject = new JsonObject();
        moduleObject.add("Module", new JsonPrimitive(module.getName()));
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((BooleanSetting) setting).isOn()));
            } else if (setting instanceof NumberSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((NumberSetting) setting).getCurrent()));
            } else if (setting instanceof DNumberSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((DNumberSetting) setting).getCurrent()));
            } else if (setting instanceof ColourSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((ColourSetting)setting).getColorRGB()));
            }
            else if (setting instanceof ModeSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((ModeSetting) setting).getCurrentMode()));
            } else if (setting instanceof StringSetting) {
                settingObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((StringSetting) setting).getText()));
            }

            if (setting.getSubSettings().size() > 0) {
                for (Setting subSetting : setting.getSubSettings()) {
                    if (subSetting instanceof BooleanSetting) {
                        subSettingsObject.add(subSetting.getName().replace(" ", ""), new JsonPrimitive(((BooleanSetting) subSetting).isOn()));
                    } else if (subSetting instanceof NumberSetting) {
                        subSettingsObject.add(subSetting.getName().replace(" ", ""), new JsonPrimitive(((NumberSetting) subSetting).getCurrent()));
                    } else if (subSetting instanceof DNumberSetting) {
                        subSettingsObject.add(subSetting.getName().replace(" ", ""), new JsonPrimitive(((DNumberSetting) subSetting).getCurrent()));
                    } else if (subSetting instanceof ColourSetting) {
                        subSettingsObject.add(subSetting.getName().replace(" ", ""), new JsonPrimitive(((ColourSetting) subSetting).getColorRGB()));
                    } else if (subSetting instanceof ModeSetting) {
                        subSettingsObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((ModeSetting) subSetting).getCurrentMode()));
                    } else if (subSetting instanceof StringSetting) {
                        subSettingsObject.add(setting.getName().replace(" ", ""), new JsonPrimitive(((StringSetting) subSetting).getText()));
                    }
                }
            }

        }


        settingObject.add("Enabled", new JsonPrimitive(module.isEnabled()));
        settingObject.add("Bind", new JsonPrimitive(module.getBind()));
        settingObject.add("SubSettings", subSettingsObject);
        moduleObject.add("Settings", settingObject);
        String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }


    public static void saveGuiPos()throws IOException {
        me.ritomg.ananta.Ananta.hudGui.gui.saveConfig(new AnantaGuiConfig(false));
        me.ritomg.ananta.Ananta.gui.gui.saveConfig(new AnantaGuiConfig(true));
    }

    public static void saveCommandPrefix() throws IOException {
        registerFiles("Main/", "Command");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(Ananta + "Main/" + "Command" + ".json"), StandardCharsets.UTF_8);
        JsonObject prefixObject = new JsonObject();

        prefixObject.add("Command", new JsonPrimitive(CommandManager.prefix));
        String jsonString = gson.toJson(new JsonParser().parse(prefixObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }

}