package me.ritomg.ananta.module.modules.client;

import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ModeSetting;
import me.ritomg.ananta.setting.settings.NumberSetting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

@Module.Info(name = "ClickGui", description = "Open the gui", category = Category.Client, bind = Keyboard.KEY_RSHIFT)
public class ClickGui extends Module {

    public NumberSetting scrollSpeed = new NumberSetting.NumberSettingBuilder()
            .withParent(this)
            .withName("ScrollSpeed")
            .withDescription("The scroll speed for your coolgui")
            .withCurrent(5)
            .withMax(20)
            .withMin(1)
            .build();
    public ModeSetting theme = new ModeSetting.ModeSettingBuilder()
            .withParent(this)
            .withName("Theme")
            .withDescription("the theme for your cool gui changes how the GUi looks")
            .withCurrentMode("ImpactTheme")
            .withModes(Arrays.asList("RainbowTheme", "ClearGradientTheme", "GamesenseTheme", "ClearTheme", "WindowsTheme","ImpactTheme", "AnantaTheme"))
            .build();
    public ModeSetting scrolling = new ModeSetting.ModeSettingBuilder()
            .withParent(this)
            .withName("Scrolling")
            .withDescription("your cool gui scrolling mode")
            .withCurrentMode("Screen")
            .withModes(Arrays.asList("Screen", "Container"))
            .build();
    public ModeSetting layout = new ModeSetting.ModeSettingBuilder()
            .withParent(this)
            .withName("Layout")
            .withDescription("the layout for your gui completely changes how the gui looks")
            .withCurrentMode("Normal")
            .withModes(Arrays.asList("Normal", "CSGO", "Search", "Single","Stacked"))
            .build();
    public NumberSetting animationSpeed = new NumberSetting.NumberSettingBuilder()
            .withParent(this)
            .withName("AnimationSpeed")
            .withDescription("gui animations speed")
            .withCurrent(200)
            .withMax(1000)
            .withMin(0)
            .build();
    public BooleanSetting blur = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Blur")
            .withDescription("Should the gui background be blurred?")
            .withIsOn(false)
            .build();
    public BooleanSetting shadow = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Shadow")
            .withDescription("Should the gui fonts have shadow?")
            .withIsOn(true)
            .build();


    ResourceLocation blurLocation = new ResourceLocation("shaders/post/blur.json");

    public void onEnable() {
        Ananta.gui.enterGUI();
        if (blur.isOn())
            mc.entityRenderer.loadShader(blurLocation);
    }

    public void onSettingChange(Module m, Setting s) {
        if (m.equals(this) && s.equals(blur)) {
            if (blur.isOn()) {
                mc.entityRenderer.loadShader(blurLocation);
            }
            else if (!blur.isOn()) {
                mc.entityRenderer.stopUseShader();
            }
        }
    }

    public void onDisable() {
        if (blur.isOn())
            mc.entityRenderer.stopUseShader();
    }

}
