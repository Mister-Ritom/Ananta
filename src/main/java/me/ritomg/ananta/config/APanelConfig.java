package me.ritomg.ananta.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lukflug.panelstudio.config.IPanelConfig;

import java.awt.*;
//thanks lukflug
public class APanelConfig implements IPanelConfig {

    private final JsonObject configObject;

    public APanelConfig(JsonObject configObject) {
        this.configObject = configObject;

    }

    @Override
    public void savePositon(Point position) {
        configObject.add("GuiPositionX", new JsonPrimitive(position.x));
        configObject.add("GuiPositionY", new JsonPrimitive(position.y));
    }

    @Override
    public void saveSize(Dimension size) {
        configObject.add("Width", new JsonPrimitive(size.getWidth()));
        configObject.add("Height", new JsonPrimitive(size.getHeight()));
    }

    @Override
    public Point loadPosition() {
        Point point = new Point();
        JsonElement panelGuiPositionXObject = configObject.get("GuiPositionX");
        if (panelGuiPositionXObject != null && panelGuiPositionXObject.isJsonPrimitive()) {
            point.x = panelGuiPositionXObject.getAsInt();
        } else return null;
        JsonElement panelGuiPositionYObject = configObject.get("GuiPositionY");
        if (panelGuiPositionYObject != null && panelGuiPositionYObject.isJsonPrimitive()) {
            point.y = panelGuiPositionYObject.getAsInt();
        } else return null;
        return point;
    }

    @Override
    public Dimension loadSize() {
        Dimension dimension =new Dimension();
        JsonElement panelWidth = configObject.get("Width");
        JsonElement panelHeight = configObject.get("Height");
        if (panelWidth != null && panelWidth.isJsonPrimitive()) {
            dimension.width = panelWidth.getAsInt();
        }
        if (panelHeight != null && panelHeight.isJsonPrimitive()) {
            dimension.height = panelHeight.getAsInt();
        }
        return dimension;
    }

    @Override
    public void saveState(boolean state) {
        configObject.add("State", new JsonPrimitive(state));
    }

    @Override
    public boolean loadState() {
        JsonElement panelOpenObject = configObject.get("State");
        if (panelOpenObject != null && panelOpenObject.isJsonPrimitive()) {
            return panelOpenObject.getAsBoolean();
        }
        return false;
    }
}
