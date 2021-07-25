package me.ritomg.ananta.hud.huds;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.setting.Labeled;
import com.lukflug.panelstudio.theme.ITheme;
import me.ritomg.ananta.hud.Hud;
import me.ritomg.ananta.hud.HudGui;
import me.ritomg.ananta.hud.HudManager;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.notifcation.NotificationManager;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ColourSetting;
import me.ritomg.ananta.util.misc.Timer;

import java.awt.*;

@Module.Info(name = "Notification", description = "Shows client message on screen", category = Category.Client)
public class Notification extends Hud {

    public BooleanSetting stopChat =  new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("StopChat")
            .withDescription("should Ananta chatMessages stop")
            .withIsOn(false)
            .build();
    public BooleanSetting sortUp =  new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("Sortup")
            .withDescription("name what you expect")
            .withIsOn(false)
            .build();
    public BooleanSetting sortRight = new BooleanSetting.BooleanSettingBuilder()
            .withParent(this)
            .withName("SortRight")
            .withDescription("name what you expect")
            .withIsOn(false)
            .build();
    public ColourSetting color = new ColourSetting.ColourSettingBuilder()
            .withParent(this)
            .withName("Color")
            .withDescription("The color text")
            .withColor(new Color(255,0,255))
            .build();

    public Notification() {
        super(3,-3);
    }

    Timer waitTimer  = new Timer();

    public void onUpdate() {
        if (waitTimer.passed(1000)) {
                if (NotificationManager.notifications.size() > 3) {
                    NotificationManager.notifications.remove(0);
                }

            waitTimer.reset();
        }
    }

    @Override
    public void populate(ITheme theme) {
        component = new ListComponent(new Labeled(getName(),null,()->true), position, getName(), new NotificationsList(), HudGui.FONT_HEIGHT, LIST_BORDER);
    }

    private static class NotificationsList implements HUDList {

        @Override
        public int getSize() {
            return NotificationManager.notifications.size();
        }

        @Override
        public String getItem(int index) {
            return NotificationManager.notifications.get(index).getPrefix() + " - "  + NotificationManager.notifications.get(index).getText();
        }

        @Override
        public Color getItemColor(int index) {
            return ((Notification) HudManager.getHud(Notification.class)).color.getColor();
        }

        @Override
        public boolean sortUp() {
            return ((Notification) HudManager.getHud(Notification.class)).sortUp.isOn();
        }

        @Override
        public boolean sortRight() {
            return ((Notification) HudManager.getHud(Notification.class)).sortRight.isOn();
        }
    }
}
