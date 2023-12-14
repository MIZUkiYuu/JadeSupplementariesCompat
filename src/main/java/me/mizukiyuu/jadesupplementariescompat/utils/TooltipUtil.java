package me.mizukiyuu.jadesupplementariescompat.utils;

import me.mizukiyuu.jadesupplementariescompat.SupplementariesPlugin;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IWailaConfig;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IBoxElement;
import snownee.jade.api.ui.ITooltipRenderer;
import snownee.jade.impl.ui.HorizontalLineElement;

import java.util.List;

public class TooltipUtil {
    private static final Vec2 TEXT_OFFSET = new Vec2(0, 3);
    private static final float ITEM_SIZE = 0.8f;

    private static final int BG_COLOR = 0x80;
    private static final int BORDER_COLOR = 0xaa;
    private static final float BORDER_WIDTH = 0.75f;

    public static void addIconANDItemName(ITooltip tooltip, ItemStack item){
        addIconANDItemName(tooltip, item.getHoverName(), item);
    }

    public static void addIconANDItemName(ITooltip tooltip, Component component, ItemStack item){
        tooltip.add(tooltip.getElementHelper().text(component).translate(TEXT_OFFSET));
        tooltip.append(tooltip.getElementHelper().item(item, ITEM_SIZE));
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip){
        addBackground(mainTooltip, subTooltip, ColorMap.BLACK.withAlpha(BG_COLOR));
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip, int bgColor){
        addBackground(mainTooltip, subTooltip, ColorMap.BLACK.withAlpha(BORDER_COLOR), bgColor, BORDER_WIDTH);
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip, int borderColor, int bgColor){
        addBackground(mainTooltip, subTooltip, borderColor, bgColor, BORDER_WIDTH);
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip, int borderCol, int bgCol, float borderWid){
        BoxStyle style = new BoxStyle(){
            {
                borderColor = borderCol;
                bgColor = bgCol;
                borderWidth = borderWid;
            }
        };

        IBoxElement box = mainTooltip.getElementHelper().box(subTooltip, style);

        box.getTooltipRenderer().setPadding(ITooltipRenderer.TOP, 2);
        box.getTooltipRenderer().setPadding(ITooltipRenderer.BOTTOM, 3);

        mainTooltip.add(box);
    }

    public static void addItemTooltip(ITooltip tooltip, ItemStack itemStack){
        List<Component> tooltipLines = itemStack.getTooltipLines(null, () -> IWailaConfig.get().getPlugin().get(SupplementariesPlugin.ADVANCED_TOOLTIP));
        TooltipUtil.addIconANDItemName(tooltip, tooltipLines.get(0), itemStack);
        tooltipLines.stream().skip(1).forEach(tooltip::add);
        tooltip.add(new HorizontalLineElement());
    }

    public static void addShulkerBoxItemTooltip(ITooltip tooltip, ItemStack itemStack){
        List<Component> tooltipLines = itemStack.getTooltipLines(null, () -> IWailaConfig.get().getPlugin().get(SupplementariesPlugin.ADVANCED_TOOLTIP));
        tooltipLines.stream().skip(tooltipLines.size() - 3).forEach(tooltip::add);  // only keep the last 3 lines of the tooltipLines
        tooltip.add(new HorizontalLineElement());
    }
}
