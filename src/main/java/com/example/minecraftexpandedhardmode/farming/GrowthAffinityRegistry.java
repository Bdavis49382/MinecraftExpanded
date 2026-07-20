package com.example.minecraftexpandedhardmode.farming;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.minecraftexpandedhardmode.registry.ModItems;

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
    private static final Map<ClimateType, List<Item>> CLIMATE_SEEDS = new HashMap<>();

    static {
        // Initialize lists
        CLIMATE_SEEDS.put(ClimateType.GRASSY, new ArrayList<>());
        CLIMATE_SEEDS.put(ClimateType.SNOWY, new ArrayList<>());
        CLIMATE_SEEDS.put(ClimateType.DRY, new ArrayList<>());
        CLIMATE_SEEDS.put(ClimateType.WET, new ArrayList<>());

        // Map crops to their seed items
        registerSeed(Blocks.WHEAT, Items.WHEAT_SEEDS);
        registerSeed(Blocks.CARROTS, ModItems.CARROT_SEEDS);
        registerSeed(Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
        registerSeed(Blocks.POTATOES, ModItems.SEED_POTATO);
        registerSeed(Blocks.MELON_STEM, Items.MELON_SEEDS);
        registerSeed(Blocks.PUMPKIN_STEM, Items.PUMPKIN_SEEDS);
    }

    private static void registerSeed(Block crop, Item seed) {
        ClimateType climate = getAffinity(crop);
        CLIMATE_SEEDS.get(climate).add(seed);
    }

    public static Item getRandomSeedForBiome(ServerWorld world, BlockPos pos) {
        ClimateType climate = ClimateResolver.resolve(world, pos);
        List<Item> seeds = CLIMATE_SEEDS.get(climate);

        if (seeds == null || seeds.isEmpty()) {
            return Items.WHEAT_SEEDS; // fallback
        }

        return seeds.get(world.random.nextInt(seeds.size()));
    }


}
