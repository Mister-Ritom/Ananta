package me.ritomg.ananta.config;

import com.google.gson.*;
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

    public static void init() {
        try {
            saveConfig();
            saveModules();
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
    }

    private static void registerFiles(String location, String name) throws IOException {
        if (Files.exists(Paths.get(Ananta + location + name + ".json"))) {
            File file = new File(Ananta + location + name + ".json");

            file.delete();

        }
        Files.createFile(Paths.get(Ananta + location + name + ".json"));
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
        }
        settingObject.add("Enabled", new JsonPrimitive(module.isEnabled()));
        settingObject.add("Bind", new JsonPrimitive(module.getBind()));
        moduleObject.add("Settings", settingObject);
        String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }

}