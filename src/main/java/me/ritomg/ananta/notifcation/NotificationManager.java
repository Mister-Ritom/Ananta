package me.ritomg.ananta.notifcation;

import me.ritomg.ananta.hud.HudManager;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    public static List<Notification> notifications = new ArrayList<>();

    public static void addNotification(Notification notification) {
            notifications.add(notification);
    }

}
