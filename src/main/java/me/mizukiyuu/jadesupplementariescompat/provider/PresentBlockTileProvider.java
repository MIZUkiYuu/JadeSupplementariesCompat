package me.mizukiyuu.jadesupplementariescompat.provider;

import me.mizukiyuu.jadesupplementariescompat.SupplementariesPlugin;
import me.mizukiyuu.jadesupplementariescompat.utils.TooltipUtil;
import net.mehvahdjukaar.supplementaries.common.block.tiles.PresentBlockTile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum PresentBlockTileProvider implements IBlockComponentProvider {

    INSTANCE;

    public static final ResourceLocation PRESENT = new ResourceLocation(SupplementariesPlugin.ID, "present");


    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if(blockAccessor.getBlockEntity() instanceof PresentBlockTile presentBlockTile){
            TooltipUtil.addItemTooltip(iTooltip, ItemStack.of(presentBlockTile.getUpdateTag()));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return PRESENT;
    }
}
