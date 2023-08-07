package me.mizukiyuu.jadesupplementariescompat;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(SupplementariesPlugin.ID)
public class SupplementariesPlugin implements IWailaPlugin {
    public static final String ID = "supplementaries";

    static SupplementariesPlugin supplementariesPlugin = new SupplementariesPlugin();

    @Override
    public void register(IWailaCommonRegistration registration) {
        supplementariesPlugin.register(registration);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerClient(IWailaClientRegistration registration) {
        supplementariesPlugin.registerClient(registration);
    }
}
