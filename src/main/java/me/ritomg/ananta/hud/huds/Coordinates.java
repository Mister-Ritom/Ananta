package me.ritomg.ananta.hud.huds;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.setting.Labeled;
import com.lukflug.panelstudio.theme.ITheme;
import me.ritomg.ananta.Ananta;
import me.ritomg.ananta.hud.Hud;
import me.ritomg.ananta.hud.HudGui;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.setting.settings.BooleanSetting;
import me.ritomg.ananta.setting.settings.ColourSetting;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

@Module.Info(name = "Coordinates", description = "shows your coordinates on screen", category = Category.Misc)
public class Coordinates extends Hud {

    ColourSetting color  = addColorSetting("Color", new Color(255,255,255));
    BooleanSetting nether  = addBooleanSetting("OtherDimension", false);

    public String[] list  = new String[] {"", ""};

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        Entity renderViewEntity = mc.getRenderViewEntity();
        if (renderViewEntity == null) {
            if (mc.player != null)
                renderViewEntity = mc.player;
        }

        int dimension = renderViewEntity.dimension;
        list[0] = getXYZ(renderViewEntity.posX, renderViewEntity.posY, renderViewEntity.posZ);

        if (nether.isOn()) {
            if (dimension == 0) {
                getXYZ(renderViewEntity.posX/8, renderViewEntity.posY, renderViewEntity.posZ/8);
            }
            else if (dimension == -1) {
                list[1] = getXYZ(renderViewEntity.posX*8, renderViewEntity.posY, renderViewEntity.posZ*8);
            }
        }

    }

    public String getXYZ(double x, double y, double z) {
        return "X:"+ x+ "Y:" + y + "Z:"+z;
    }

    public Coordinates() {
        super(5,-10);
    }

    @Override
    public void populate(ITheme theme) {
        component = new ListComponent(new Labeled(getName(),null,()->true), position, getName(), new CoordiantesList(), HudGui.FONT_HEIGHT, Hud.LIST_BORDER);
    }

    private class CoordiantesList implements HUDList {

        @Override
        public int getSize() {
            return list.length;
        }

        @Override
        public String getItem(int index) {
            return list[index];
        }

        @Override
        public Color getItemColor(int index) {
            return color.getColor();
        }

        @Override
        public boolean sortUp() {
            return false;
        }

        @Override
        public boolean sortRight() {
            return false;
        }
    }

}
