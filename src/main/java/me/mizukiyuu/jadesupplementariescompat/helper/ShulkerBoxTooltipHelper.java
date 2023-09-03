package me.mizukiyuu.jadesupplementariescompat.helper;

import me.mizukiyuu.jadesupplementariescompat.utils.ColorMap;
import me.mizukiyuu.jadesupplementariescompat.utils.ColorUtil;
import me.mizukiyuu.jadesupplementariescompat.utils.InventoryUtil;
import me.mizukiyuu.jadesupplementariescompat.utils.TooltipUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import snownee.jade.api.ITooltip;
import snownee.jade.api.ui.IElementHelper;

public class ShulkerBoxTooltipHelper {
    public static void appendTooltip(ITooltip tooltip, IElementHelper helper, ItemStack itemStack){
        TooltipUtil.addIconANDItemName(tooltip, itemStack);

        if(BlockItem.getBlockEntityData(itemStack).contains("Items", 9)){
            NonNullList<ItemStack> inventory = getShulkerBoxItemStacks(itemStack);
            ITooltip itemTooltip = helper.tooltip();
            for (int i = 0; i < InventoryUtil.getMinFilledCount(inventory); i++){
                if(i % 9 == 0){
                    itemTooltip.add(helper.item(inventory.get(i)));
                }else{
                    itemTooltip.append(helper.item(inventory.get(i)));
                }
            }
            ColorMap color = ColorUtil.getColorFromShulkerBox(itemStack.getItem());
            TooltipUtil.addBackground(tooltip, itemTooltip, color.withAlpha(0xaa), color.withAlpha(0x40));
        }
    }

    public static NonNullList<ItemStack> getShulkerBoxItemStacks(ItemStack item){
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(ShulkerBoxBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(BlockItem.getBlockEntityData(item), itemStacks);
        return itemStacks;
    }
}
