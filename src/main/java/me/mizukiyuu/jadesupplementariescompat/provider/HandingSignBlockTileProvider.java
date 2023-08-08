package me.mizukiyuu.jadesupplementariescompat.provider;

import me.mizukiyuu.jadesupplementariescompat.SupplementariesPlugin;
import net.mehvahdjukaar.supplementaries.common.block.TextHolder;
import net.mehvahdjukaar.supplementaries.common.block.tiles.HangingSignBlockTile;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.*;
import snownee.jade.impl.ui.HorizontalLineElement;

import java.util.Arrays;
import java.util.List;

public enum HandingSignBlockTileProvider implements IBlockComponentProvider {

    INSTANCE;

    public static final ResourceLocation HANGING_SIGN = new ResourceLocation(SupplementariesPlugin.ID, "hanging_sign");
    public static final ResourceLocation ADVANCED_TOOLTIP = new ResourceLocation(SupplementariesPlugin.ID,  "hanging_sign.advanced_tooltip");

    public static final Vec2 ICON_OFFSET = new Vec2(-1, -1);

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig config) {
        if (blockAccessor.getBlockEntity() instanceof HangingSignBlockTile hangingSignBlockTile){
            // show item toolTips
            if (!hangingSignBlockTile.isEmpty()){
                ItemStack item = hangingSignBlockTile.getItem();
                List<Component> tooltipLines = item.getTooltipLines(null, () -> config.get(ADVANCED_TOOLTIP));

                tooltip.add(tooltipLines.get(0));
                tooltip.append(tooltip.getElementHelper().item(item, 0.5f).translate(ICON_OFFSET));

                tooltipLines.stream().skip(1).forEach(tooltip::add);
            }

            // show text
            TextHolder textHolder = hangingSignBlockTile.getTextHolder();
            if(!textHolder.isEmpty()){
                IElementHelper element = tooltip.getElementHelper();
                ITooltip textTip = element.tooltip();

                textTip.add(new HorizontalLineElement());
                Arrays.stream(
                        textHolder.getTextLines())
                        .filter(line -> !line.getString().isEmpty())
                        .forEach(textTip::add);
                textTip.add(new HorizontalLineElement());

                BoxStyle style = new BoxStyle();
                style.borderColor = 0x88000000;
                style.bgColor = 0x88000000;
                style.borderWidth = 0.75F;
                IBoxElement box = element.box(textTip, style);
                box.getTooltipRenderer().setPadding(ITooltipRenderer.TOP, 2);
                box.getTooltipRenderer().setPadding(ITooltipRenderer.BOTTOM, 3);
                tooltip.add(box);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HANGING_SIGN;
    }
}
