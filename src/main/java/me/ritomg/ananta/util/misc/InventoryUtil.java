package me.ritomg.ananta.util.misc;

import me.ritomg.ananta.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//Thanks neko
//Tobe used in killaura
public class InventoryUtil extends Util {

    public static int findItemInventorySlot (Item item , boolean offHand ) {
        AtomicInteger slot = new AtomicInteger( );
        slot.set ( - 1 );
        for (Map.Entry < Integer, ItemStack> entry : getInventoryAndHotbarSlots ( ).entrySet ( )) {
            if ( entry.getValue ( ).getItem ( ) == item ) {
                if ( entry.getKey ( ) == 45 && ! offHand ) {
                    continue;
                }
                slot.set ( entry.getKey ( ) );
                return slot.get ( );
            }
        }
        return slot.get ( );
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

    public static
    Map < Integer, ItemStack > getInventoryAndHotbarSlots ( ) {
        return getInventorySlots ( 9 , 44 );
    }

    private static
    Map < Integer, ItemStack > getInventorySlots ( int currentI , int last ) {
        int current = currentI;
        Map < Integer, ItemStack > fullInventorySlots = new HashMap<>( );
        while ( current <= last ) {
            fullInventorySlots.put ( current , mc.player.inventoryContainer.getInventory ( ).get ( current ) );
            current++;
        }
        return fullInventorySlots;
    }

}
