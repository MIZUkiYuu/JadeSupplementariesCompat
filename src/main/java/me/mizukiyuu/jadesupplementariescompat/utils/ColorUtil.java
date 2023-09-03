package me.mizukiyuu.jadesupplementariescompat.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class ColorUtil {
    public static ColorMap getColorFromShulkerBox(Item item){
        if(item == Items.SHULKER_BOX){
            return ColorMap.PURPLE;
        }else {
            return ColorMap.getColorById(ShulkerBoxBlock.getColorFromItem(item).getId());
        }
    }
}

