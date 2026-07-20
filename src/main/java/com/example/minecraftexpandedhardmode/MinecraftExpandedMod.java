package com.example.minecraftexpandedhardmode;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.minecraftexpandedhardmode.mining.MiningManager;
import com.example.minecraftexpandedhardmode.hunger.HungerManager;
import com.example.minecraftexpandedhardmode.loot.CropDropsHandler;
import com.example.minecraftexpandedhardmode.farming.FarmingManager;
import com.example.minecraftexpandedhardmode.animals.AnimalManager;
import com.example.minecraftexpandedhardmode.transport.TransportManager;
import com.example.minecraftexpandedhardmode.mobs.MobManager;
import com.example.minecraftexpandedhardmode.registry.ModItemGroups;
import com.example.minecraftexpandedhardmode.registry.ModItems;
import com.example.minecraftexpandedhardmode.death.DeathManager;
import com.example.minecraftexpandedhardmode.storage.StorageManager;
import com.example.minecraftexpandedhardmode.smelting.SmeltingManager;
import com.example.minecraftexpandedhardmode.furnace.FurnaceManager;

public class MinecraftExpandedMod implements ModInitializer {
    public static final String MOD_ID = "minecraftexpanded";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Minecraft Expanded Hard Mode initialized!");
        MiningConfig.loadConfig();
        
        // Initialize all module systems
        MiningManager.init();
        HungerManager.init();
        FarmingManager.init();
        AnimalManager.init();
        TransportManager.init();
        MobManager.init();
        DeathManager.init();
        StorageManager.init();
        SmeltingManager.init();
        FurnaceManager.init();
        ModItems.init();
        ModItemGroups.init();
        CropDropsHandler.init();
        
        LOGGER.info("All modules loaded successfully!");
    }
}
