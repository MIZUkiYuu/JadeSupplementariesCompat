package me.mizukiyuu.jadesupplementariescompat.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;

public class InventoryUtil {

    public static int getMinFilledCount(NonNullList<ItemStack> list){
        int l = list.size();
        for(; l > 0; l--){
            if(!list.get(l - 1).is(Items.AIR)){ break; }
        }
        return l;
    }

    public static NonNullList<ItemStack> getShulkerBoxItemStacks(ItemStack item){
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(ShulkerBoxBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(BlockItem.getBlockEntityData(item), itemStacks);
        return itemStacks;
    }
}
