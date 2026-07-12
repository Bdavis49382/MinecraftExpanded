package com.example.minecraftexpandedhardmode.farming;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class GrowthAffinityRegistry {

    private static final Map<Block, ClimateType> AFFINITIES = new HashMap<>();

    static {
        // Crops
        AFFINITIES.put(Blocks.WHEAT, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.CARROTS, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.BEETROOTS, ClimateType.SNOWY); // beets grow well in cold climates
        AFFINITIES.put(Blocks.POTATOES, ClimateType.DRY);    // potatoes tolerate dry climates
        AFFINITIES.put(Blocks.PUMPKIN_STEM, ClimateType.DRY);
        AFFINITIES.put(Blocks.MELON_STEM, ClimateType.WET);

        // Wet crops
        AFFINITIES.put(Blocks.COCOA, ClimateType.WET);
        AFFINITIES.put(Blocks.BROWN_MUSHROOM, ClimateType.WET);
        AFFINITIES.put(Blocks.RED_MUSHROOM, ClimateType.WET);

        // Trees
        AFFINITIES.put(Blocks.OAK_SAPLING, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.BIRCH_SAPLING, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.SPRUCE_SAPLING, ClimateType.SNOWY);
        AFFINITIES.put(Blocks.JUNGLE_SAPLING, ClimateType.WET);
        AFFINITIES.put(Blocks.ACACIA_SAPLING, ClimateType.DRY);
        AFFINITIES.put(Blocks.DARK_OAK_SAPLING, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.CHERRY_SAPLING, ClimateType.GRASSY);
        AFFINITIES.put(Blocks.MANGROVE_PROPAGULE, ClimateType.WET);

        // Desert plants
        AFFINITIES.put(Blocks.CACTUS, ClimateType.DRY);
        AFFINITIES.put(Blocks.DEAD_BUSH, ClimateType.DRY);

        // Nether plants (treated as DRY)
        AFFINITIES.put(Blocks.NETHER_WART, ClimateType.DRY);
        AFFINITIES.put(Blocks.CRIMSON_FUNGUS, ClimateType.DRY);
        AFFINITIES.put(Blocks.WARPED_FUNGUS, ClimateType.DRY);

        // Wet plants
        AFFINITIES.put(Blocks.SUGAR_CANE, ClimateType.WET);
        AFFINITIES.put(Blocks.BAMBOO, ClimateType.WET);
    }

    public static ClimateType getAffinity(Block block) {
        return AFFINITIES.getOrDefault(block, ClimateType.GRASSY);
    }
}
