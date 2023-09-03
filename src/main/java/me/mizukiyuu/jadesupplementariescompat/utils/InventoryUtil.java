package me.mizukiyuu.jadesupplementariescompat.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class InventoryUtil {

    public static int getMinFilledCount(NonNullList<ItemStack> list){
        int l = list.size();
        for(; l > 0; l--){
            if(!list.get(l - 1).is(Items.AIR)){ break; }
        }
        return l;
    }
}
