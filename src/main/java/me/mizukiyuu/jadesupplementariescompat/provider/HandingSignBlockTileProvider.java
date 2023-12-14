package me.mizukiyuu.jadesupplementariescompat.provider;

import me.mizukiyuu.jadesupplementariescompat.SupplementariesPlugin;
import me.mizukiyuu.jadesupplementariescompat.utils.ColorMap;
import me.mizukiyuu.jadesupplementariescompat.utils.ColorUtil;
import me.mizukiyuu.jadesupplementariescompat.utils.InventoryUtil;
import me.mizukiyuu.jadesupplementariescompat.utils.TooltipUtil;
import net.mehvahdjukaar.supplementaries.common.block.TextHolder;
import net.mehvahdjukaar.supplementaries.common.block.tiles.HangingSignBlockTile;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.impl.ui.HorizontalLineElement;

public enum HandingSignBlockTileProvider implements IBlockComponentProvider {

    INSTANCE;

    public static final ResourceLocation HANGING_SIGN = new ResourceLocation(SupplementariesPlugin.ID, "hanging_sign");
    public static final ResourceLocation SHULKERBOX_INVENTORY = new ResourceLocation(SupplementariesPlugin.ID,  "hanging_sign.show_inventory");

    private static final Vec2 TEXTLINE_OFFSET = new Vec2(2, 0);

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig config) {
        if (blockAccessor.getBlockEntity() instanceof HangingSignBlockTile hangingSignBlockTile){
            IElementHelper helper = iTooltip.getElementHelper();

            if (!hangingSignBlockTile.isEmpty()){
                ItemStack itemStack = hangingSignBlockTile.getItem();

                // shulker box
                if(config.get(SHULKERBOX_INVENTORY) && Block.byItem(itemStack.getItem()) instanceof ShulkerBoxBlock){
                    TooltipUtil.addIconANDItemName(iTooltip, itemStack);

                    if(BlockItem.getBlockEntityData(itemStack).contains("Items", 9)){
                        NonNullList<ItemStack> inventory = InventoryUtil.getShulkerBoxItemStacks(itemStack);
                        ITooltip itemTooltip = helper.tooltip();
                        for (int i = 0; i < InventoryUtil.getMinFilledCount(inventory); i++){
                            if(i % 9 == 0){
                                itemTooltip.add(helper.item(inventory.get(i)));
                            }else{
                                itemTooltip.append(helper.item(inventory.get(i)));
                            }
                        }
                        ColorMap color = ColorUtil.getColorFromShulkerBox(itemStack.getItem());
                        TooltipUtil.addBackground(iTooltip, itemTooltip, color.withAlpha(0xaa), color.withAlpha(0x40));
                    }
                }else {
                    // item toolTips
                    TooltipUtil.addItemTooltip(iTooltip, itemStack);
                }
            }

            // text
            TextHolder textHolder = hangingSignBlockTile.getTextHolder();
            if(!textHolder.isEmpty()){
                ITooltip textTip = helper.tooltip();

                textTip.add(new HorizontalLineElement());
                for (Component line: textHolder.getTextLines()) {
                    if(line.getString().isEmpty()){ continue; }
                    textTip.add(helper.text(line).translate(TEXTLINE_OFFSET));
                }
                textTip.add(new HorizontalLineElement());

                TooltipUtil.addBackground(iTooltip, textTip);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HANGING_SIGN;
    }
}
