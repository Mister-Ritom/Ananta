package me.ritomg.ananta.util.misc;

import me.ritomg.ananta.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;

import java.util.HashMap;
import java.util.Map;

public class InventoryUtil extends Util {

    public static int findItemInHotbar(Item item) {
        for (int i = 0; i<9; i++) {
           ItemStack stack = mc.player.inventory.getStackInSlot(i);
           if (stack == ItemStack.EMPTY) continue;
           if (stack.getItem() == item) return i;
        }
        return -1;
    }

    public static void switchToHotbarSlot ( int slot , boolean silent ) {
        if ( mc.player.inventory.currentItem == slot || slot < 0 ) {
            return;
        }

        if ( silent ) {
            mc.player.connection.sendPacket ( new CPacketHeldItemChange ( slot ) );
            mc.playerController.updateController ( );
        } else {
            mc.player.connection.sendPacket ( new CPacketHeldItemChange ( slot ) );
            mc.player.inventory.currentItem = slot;
            mc.playerController.updateController ( );
        }
    }

    public static void confirmSlot ( int slot ) {
        mc.player.connection.sendPacket ( new CPacketHeldItemChange( slot ) );
        mc.player.inventory.currentItem = slot;
        mc.playerController.updateController ( );
    }

}
