package me.ritomg.ananta.notifcation;

public class Notification {

    private String prefix = "Ananta";
    private String text;
    private long disableTime = 500L;

    public Notification(String prefix, String text, long disableTime) {
        this.prefix = prefix;
        this.text = text;
        this.disableTime = disableTime;
    }

    public Notification(String text) {
        this.text = text;
    }

    public Notification(String text, long delay) {
        this.text = text;
        this.disableTime = delay;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(long disableTime) {
        this.disableTime = disableTime;
    }
}
