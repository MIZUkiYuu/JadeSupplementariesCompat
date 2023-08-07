package me.mizukiyuu.jadesupplementariescompat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;


@WailaPlugin
public class JadeSupplementariesCompat implements IWailaPlugin {
    public static final String ID = "jadecompataddon";
    public static final String NAME = "Jade Compat Addon";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Override
    public void register(IWailaCommonRegistration registration) {

    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {

    }
}
