package com.example.minecraftexpandedhardmode;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecraftExpandedMod implements ModInitializer {
    public static final String MOD_ID = "minecraftexpanded";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Minecraft Expanded initialized!");
    }
}
