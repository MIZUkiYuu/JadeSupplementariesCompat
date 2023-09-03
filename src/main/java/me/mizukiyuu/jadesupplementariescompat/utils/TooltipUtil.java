package me.mizukiyuu.jadesupplementariescompat.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.ITooltip;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IBoxElement;
import snownee.jade.api.ui.ITooltipRenderer;

public class TooltipUtil {
    public static final Vec2 TEXT_OFFSET = new Vec2(0, 3);

    public static void addIconANDItemName(ITooltip tooltip, ItemStack item){
        addIconANDItemName(tooltip, item.getHoverName(), item);
    }

    public static void addIconANDItemName(ITooltip tooltip, Component component, ItemStack item){
        tooltip.add(tooltip.getElementHelper().text(component).translate(TEXT_OFFSET));
        tooltip.append(tooltip.getElementHelper().item(item, 0.8f));
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip){
        addBackground(mainTooltip, subTooltip, ColorMap.BLACK.withAlpha(0x80));
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip, int bgColor){
        addBackground(mainTooltip, subTooltip, ColorMap.BLACK.withAlpha(0xaa), bgColor, 0.75F);
    }

    public static void addBackground(ITooltip mainTooltip, ITooltip subTooltip, int borderColor, int bgColor){
        addBackground(mainTooltip, subTooltip, borderColor, bgColor, 0.75F);
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
}
