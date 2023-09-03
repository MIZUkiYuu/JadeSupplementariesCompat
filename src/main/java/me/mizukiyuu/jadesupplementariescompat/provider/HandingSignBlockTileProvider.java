package me.mizukiyuu.jadesupplementariescompat.provider;

import me.mizukiyuu.jadesupplementariescompat.SupplementariesPlugin;
import me.mizukiyuu.jadesupplementariescompat.helper.ShulkerBoxTooltipHelper;
import me.mizukiyuu.jadesupplementariescompat.utils.TooltipUtil;
import net.mehvahdjukaar.supplementaries.common.block.TextHolder;
import net.mehvahdjukaar.supplementaries.common.block.tiles.HangingSignBlockTile;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

import java.util.List;

public enum HandingSignBlockTileProvider implements IBlockComponentProvider {

    INSTANCE;

    public static final ResourceLocation HANGING_SIGN = new ResourceLocation(SupplementariesPlugin.ID, "hanging_sign");
    public static final ResourceLocation ADVANCED_TOOLTIP = new ResourceLocation(SupplementariesPlugin.ID,  "hanging_sign.advanced_tooltip");
    public static final ResourceLocation SHULKERBOX_INVENTORY = new ResourceLocation(SupplementariesPlugin.ID,  "hanging_sign.shulkerbox_inventory");

    private static final Vec2 TEXTLINE_OFFSET = new Vec2(2, 0);

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig config) {
        if (blockAccessor.getBlockEntity() instanceof HangingSignBlockTile hangingSignBlockTile){
            IElementHelper helper = tooltip.getElementHelper();

            if (!hangingSignBlockTile.isEmpty()){
                ItemStack itemStack = hangingSignBlockTile.getItem();

                // shulker box
                if(config.get(SHULKERBOX_INVENTORY) && Block.byItem(itemStack.getItem()) instanceof ShulkerBoxBlock){
                    ShulkerBoxTooltipHelper.appendTooltip(tooltip, helper, itemStack);
                }else {
                    // item toolTips
                    List<Component> tooltipLines = itemStack.getTooltipLines(null, () -> config.get(ADVANCED_TOOLTIP));
                    TooltipUtil.addIconANDItemName(tooltip, tooltipLines.get(0), itemStack);
                    tooltipLines.stream().skip(1).forEach(tooltip::add);
                    tooltip.add(new HorizontalLineElement());
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

                TooltipUtil.addBackground(tooltip, textTip);
            }
        }
    }


    @Override
    public ResourceLocation getUid() {
        return HANGING_SIGN;
    }
}
