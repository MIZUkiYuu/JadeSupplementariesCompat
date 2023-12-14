package me.mizukiyuu.jadesupplementariescompat;

import me.mizukiyuu.jadesupplementariescompat.provider.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.supplementaries.common.block.blocks.HangingSignBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.PresentBlock;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(SupplementariesPlugin.ID)
public class SupplementariesPlugin implements IWailaPlugin {

    public static final String ID = "supplementaries";
    public static final ResourceLocation ADVANCED_TOOLTIP = new ResourceLocation(ID,  "advanced_tooltip");

    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(ADVANCED_TOOLTIP, true);

        registration.addConfig(HandingSignBlockTileProvider.SHULKERBOX_INVENTORY, true);
        registration.registerBlockComponent(HandingSignBlockTileProvider.INSTANCE, HangingSignBlock.class);

        registration.registerBlockComponent(PresentBlockTileProvider.INSTANCE, PresentBlock.class);
    }
}

