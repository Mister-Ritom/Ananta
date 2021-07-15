package me.ritomg.ananta.config;

import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

//thanks to lukflug for helping me with this
public class LoadConfig {

    private static final String Ananta = "Ananta/";
    private static final String modulesPath = "Modules/";

    public static void init() {
        try {
            loadModules();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadModules() throws IOException {
        String moduleLocation = Ananta + modulesPath;

        for (Module module : ModuleManager.getModules()) {
            try {
                loadModuleDirect(moduleLocation, module);
            } catch (IOException e) {
                System.out.println(module.getName());
                e.printStackTrace();
            }
        }
    }

    private static void loadModuleDirect(String moduleLocation, Module module) throws IOException {
        if (!Files.exists(Paths.get(moduleLocation + module.getName() + ".json"))) {
            return;
        }

        InputStream inputStream = Files.newInputStream(Paths.get(moduleLocation + module.getName() + ".json"));
        JsonObject moduleObject;
        try {
            moduleObject = new JsonParser().parse(new InputStreamReader(inputStream)).getAsJsonObject();
        }catch (java.lang.IllegalStateException e) {
            return;
        }

        if (moduleObject.get("Module") == null) {
            return;
        }

        JsonObject settingObject = moduleObject.get("Settings").getAsJsonObject();
        JsonElement bindObject = settingObject.get("Bind");
        JsonElement enabledObject = settingObject.get("Enabled");

        try {
            if (bindObject != null && bindObject.isJsonPrimitive()) {
                module.setBind(bindObject.getAsInt());
            }
        }  catch (java.lang.NumberFormatException e) {
            System.out.println(module.getBind() + " " + module.getName());
            System.out.println(bindObject);
        }
        try {
            if (enabledObject != null && enabledObject.isJsonPrimitive()) {
                if (enabledObject.getAsBoolean()) {
                    module.enable();
                }
            }
        }catch (java.lang.NumberFormatException e) {
            System.out.println(module.isEnabled()+ " " + module.getName());
            System.out.println(enabledObject);
        }

        for (Setting setting : module.getSettings()) {
            JsonElement dataObject = settingObject.get(setting.getName().replace(" ", ""));
            try {
                if (dataObject != null && dataObject.isJsonPrimitive()) {
                    if (setting instanceof BooleanSetting) {
                        ((BooleanSetting)setting).On(dataObject.getAsBoolean());
                    } else if (setting instanceof NumberSetting) {
                        ((NumberSetting)setting).setCurrent(dataObject.getAsInt());
                    } else if (setting instanceof DNumberSetting) {
                        ((DNumberSetting)setting).setCurrent(dataObject.getAsDouble());
                    }/*else if (setting instanceof ColourSetting) {
                        ((ColourSetting)setting).setColorRGB(dataObject.getAsLong());}*/ // TODO
                    else if (setting instanceof ModeSetting) {
                        ((ModeSetting) setting).setCurrentMode(dataObject.getAsString());
                    } else if (setting instanceof StringSetting) {
                        ((StringSetting)setting).setText(dataObject.getAsString());
                    }

                }
            } catch (java.lang.NumberFormatException e) {
                System.out.println(setting.getName().replace(" ", "") + " " + module.getName());
                System.out.println(dataObject);
            }
        }
        inputStream.close();
    }

}