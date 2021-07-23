package me.ritomg.ananta.util.render;

import me.ritomg.ananta.util.Util;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtil extends Util {

    public static void entityESPBox(Entity entity, Color color)
    {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(3.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
            GL11.glColor4d(
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue(), color.getAlpha());
        RenderGlobal.drawSelectionBoundingBox(
                new AxisAlignedBB(
                        entity.getEntityBoundingBox().minX
                                - 0.05
                                - entity.posX
                                + (entity.posX - mc
                                .getRenderManager().viewerPosX),
                        entity.getEntityBoundingBox().minY
                                - entity.posY
                                + (entity.posY - mc
                                .getRenderManager().viewerPosY),
                        entity.getEntityBoundingBox().minZ
                                - 0.05
                                - entity.posZ
                                + (entity.posZ - mc
                                .getRenderManager().viewerPosZ),
                        entity.getEntityBoundingBox().maxX
                                + 0.05
                                - entity.posX
                                + (entity.posX - mc
                                .getRenderManager().viewerPosX),
                        entity.getEntityBoundingBox().maxY
                                + 0.1
                                - entity.posY
                                + (entity.posY - mc
                                .getRenderManager().viewerPosY),
                        entity.getEntityBoundingBox().maxZ
                                + 0.05
                                - entity.posZ
                                + (entity.posZ -mc
                                .getRenderManager().viewerPosZ)), color.getRed(), color.getGreen(), color.getBlue(), 1f);//why the alpha can't be over 1 i don't understand
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }

}
