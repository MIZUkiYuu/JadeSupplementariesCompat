package me.mizukiyuu.jadesupplementariescompat;

import me.mizukiyuu.jadesupplementariescompat.provider.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.supplementaries.common.block.blocks.HangingSignBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(SupplementariesPlugin.ID)
public class SupplementariesPlugin implements IWailaPlugin {
    public static final String ID = "supplementaries";
    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(HandingSignBlockTileProvider.ADVANCED_TOOLTIP, true);
        registration.addConfig(HandingSignBlockTileProvider.SHULKERBOX_INVENTORY, true);
        registration.registerBlockComponent(HandingSignBlockTileProvider.INSTANCE, HangingSignBlock.class);
    }
}

