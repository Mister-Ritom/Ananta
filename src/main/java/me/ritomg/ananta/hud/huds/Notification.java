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
import me.ritomg.ananta.util.Timer;

import java.awt.*;

@Module.Info(name = "Notification", description = "Shows client message on screen", category = Category.Client)
public class Notification extends Hud {

    public BooleanSetting stopChat = addBooleanSetting("StopChat", true);
    public BooleanSetting sortUp = addBooleanSetting("SorUp", true);
    public BooleanSetting sortRight = addBooleanSetting("SortRight", false);
    public ColourSetting color = addColorSetting("Color", new Color(255, 0, 0));

    public Notification() {
        super(3,3);
    }

    Timer waitTimer  = new Timer();

    public void onUpdate() {
        for (me.ritomg.ananta.notifcation.Notification n : NotificationManager.notifications) {
            if (waitTimer.passed(n.getDisableTime())) {
                    NotificationManager.notifications.remove(0);
                waitTimer.reset();
            }
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
            return NotificationManager.notifications.get(index).getText();
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
