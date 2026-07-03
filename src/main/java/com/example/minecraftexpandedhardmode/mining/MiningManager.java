package com.example.minecraftexpandedhardmode.mining;

import com.example.minecraftexpandedhardmode.MinecraftExpandedMod;

/**
 * Handles block mining speed rebalancing and ore processing pipeline.
 * - Slower block break speeds by tool type (via PlayerEntityMixin)
 * - Hand mining: 95% slowdown (5% vanilla speed)
 * - Wrong tool: 90% slowdown (10% vanilla speed)
 * - Ore extraction: cobblestone+ore → gravel+ore via water (TODO)
 */
public class MiningManager {
    public static void init() {
        MinecraftExpandedMod.LOGGER.info("MiningManager initialized - hand mining and wrong tools are now significantly slower");
        // Mixins handle the mining speed reduction automatically
        // TODO: Implement ore processing pipeline
        // TODO: Set up water-based ore extraction listener
    }
}

