package com.example.minecraftexpandedhardmode.hunger;

import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.FoodComponent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
    
public class FoodTierRegistry {

    private static final Map<Item, FoodComponent> FOOD_MAP = new HashMap<>();

    static {
        //
        // FULL MEALS (8 pops)
        //
        FOOD_MAP.put(Items.CAKE, FoodTiers.FULL_MEAL);
        FOOD_MAP.put(Items.PUMPKIN_PIE, FoodTiers.FULL_MEAL);
        FOOD_MAP.put(Items.GOLDEN_CARROT, FoodTiers.FULL_MEAL);
        FOOD_MAP.put(Items.GOLDEN_APPLE, FoodTiers.FULL_MEAL);
        FOOD_MAP.put(Items.ENCHANTED_GOLDEN_APPLE, FoodTiers.FULL_MEAL);

        //
        // MAIN DISHES (5 pops)
        //
        FOOD_MAP.put(Items.COOKED_BEEF, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.COOKED_PORKCHOP, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.COOKED_MUTTON, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.COOKED_CHICKEN, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.COOKED_RABBIT, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.BREAD, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.BAKED_POTATO, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.RABBIT_STEW, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.MUSHROOM_STEW, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.BEETROOT_SOUP, FoodTiers.MAIN_DISH);
        FOOD_MAP.put(Items.SUSPICIOUS_STEW, FoodTiers.MAIN_DISH);

        //
        // SIDES (3 pops)
        //
        FOOD_MAP.put(Items.CARROT, FoodTiers.SIDE);
        FOOD_MAP.put(Items.APPLE, FoodTiers.SIDE);
        FOOD_MAP.put(Items.MELON_SLICE, FoodTiers.SIDE);
        FOOD_MAP.put(Items.BEETROOT, FoodTiers.SIDE);
        FOOD_MAP.put(Items.COOKIE, FoodTiers.SIDE);
        FOOD_MAP.put(Items.CHORUS_FRUIT, FoodTiers.SIDE);

        //
        // SNACKS (1 pop)
        //
        FOOD_MAP.put(Items.SWEET_BERRIES, FoodTiers.SNACK);
        FOOD_MAP.put(Items.GLOW_BERRIES, FoodTiers.SNACK);
        FOOD_MAP.put(Items.DRIED_KELP, FoodTiers.SNACK);
        FOOD_MAP.put(Items.HONEY_BOTTLE, FoodTiers.SNACK);

        //
        // TRASH (.5 pop)
        // Raw meats
        FOOD_MAP.put(Items.BEEF, FoodTiers.TRASH);
        FOOD_MAP.put(Items.PORKCHOP, FoodTiers.TRASH);
        FOOD_MAP.put(Items.MUTTON, FoodTiers.TRASH);
        FOOD_MAP.put(Items.CHICKEN, FoodTiers.TRASH);
        FOOD_MAP.put(Items.RABBIT, FoodTiers.TRASH);
        FOOD_MAP.put(Items.SALMON, FoodTiers.TRASH);
        FOOD_MAP.put(Items.COD, FoodTiers.TRASH);

        // Truly bad foods
        FOOD_MAP.put(Items.ROTTEN_FLESH, FoodTiers.TRASH);
        FOOD_MAP.put(Items.POISONOUS_POTATO, FoodTiers.TRASH);
        FOOD_MAP.put(Items.SPIDER_EYE, FoodTiers.TRASH);
        FOOD_MAP.put(Items.TROPICAL_FISH, FoodTiers.TRASH);
        FOOD_MAP.put(Items.PUFFERFISH, FoodTiers.TRASH);

        //
        // FORCED-EDIBLE PLANTS
        //

        // Flowers → SNACK
        FOOD_MAP.put(Items.DANDELION, FoodTiers.SNACK);
        FOOD_MAP.put(Items.POPPY, FoodTiers.SNACK);
        FOOD_MAP.put(Items.BLUE_ORCHID, FoodTiers.SNACK);
        FOOD_MAP.put(Items.ALLIUM, FoodTiers.SNACK);
        FOOD_MAP.put(Items.AZURE_BLUET, FoodTiers.SNACK);
        FOOD_MAP.put(Items.RED_TULIP, FoodTiers.SNACK);
        FOOD_MAP.put(Items.ORANGE_TULIP, FoodTiers.SNACK);
        FOOD_MAP.put(Items.WHITE_TULIP, FoodTiers.SNACK);
        FOOD_MAP.put(Items.PINK_TULIP, FoodTiers.SNACK);
        FOOD_MAP.put(Items.OXEYE_DAISY, FoodTiers.SNACK);
        FOOD_MAP.put(Items.CORNFLOWER, FoodTiers.SNACK);
        FOOD_MAP.put(Items.LILY_OF_THE_VALLEY, FoodTiers.SNACK);

        // Tall Flowers → SNACK
        FOOD_MAP.put(Items.SUNFLOWER, FoodTiers.SNACK);
        FOOD_MAP.put(Items.LILAC, FoodTiers.SNACK);
        FOOD_MAP.put(Items.ROSE_BUSH, FoodTiers.SNACK);
        FOOD_MAP.put(Items.PEONY, FoodTiers.SNACK);

        // Mushrooms → SNACK
        FOOD_MAP.put(Items.RED_MUSHROOM, FoodTiers.SNACK);
        FOOD_MAP.put(Items.BROWN_MUSHROOM, FoodTiers.SNACK);

        // Wild Greens → TRASH
        FOOD_MAP.put(Items.GRASS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.TALL_GRASS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.FERN, FoodTiers.TRASH);
        FOOD_MAP.put(Items.LARGE_FERN, FoodTiers.TRASH);
        FOOD_MAP.put(Items.DEAD_BUSH, FoodTiers.TRASH);
        FOOD_MAP.put(Items.SEA_PICKLE, FoodTiers.TRASH);

        // Saplings → TRASH
        FOOD_MAP.put(Items.OAK_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.SPRUCE_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.BIRCH_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.JUNGLE_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.ACACIA_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.DARK_OAK_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.CHERRY_SAPLING, FoodTiers.TRASH);
        FOOD_MAP.put(Items.MANGROVE_PROPAGULE, FoodTiers.TRASH);

        // Seeds → SNACK
        FOOD_MAP.put(Items.WHEAT_SEEDS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.BEETROOT_SEEDS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.MELON_SEEDS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.PUMPKIN_SEEDS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.TORCHFLOWER_SEEDS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.PITCHER_POD, FoodTiers.SNACK);

        // Vines & Lichen → TRASH
        FOOD_MAP.put(Items.VINE, FoodTiers.TRASH);
        FOOD_MAP.put(Items.GLOW_LICHEN, FoodTiers.TRASH);

        // Nether Plants → TRASH
        FOOD_MAP.put(Items.CRIMSON_FUNGUS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.WARPED_FUNGUS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.NETHER_SPROUTS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.WEEPING_VINES, FoodTiers.TRASH);
        FOOD_MAP.put(Items.TWISTING_VINES, FoodTiers.TRASH);
        FOOD_MAP.put(Items.CRIMSON_ROOTS, FoodTiers.TRASH);
        FOOD_MAP.put(Items.WARPED_ROOTS, FoodTiers.TRASH);

        // Underwater Plants → SNACK
        FOOD_MAP.put(Items.SEAGRASS, FoodTiers.SNACK);
        FOOD_MAP.put(Items.KELP, FoodTiers.TRASH); // Cook to get a snack
    }

    public static FoodComponent getTier(Item item) {
        return FOOD_MAP.get(item);
    }

    private static final Set<Item> FORCED_EDIBLE = new HashSet<>();

    static {
        // Flowers
        FORCED_EDIBLE.add(Items.DANDELION);
        FORCED_EDIBLE.add(Items.POPPY);
        FORCED_EDIBLE.add(Items.BLUE_ORCHID);
        FORCED_EDIBLE.add(Items.ALLIUM);
        FORCED_EDIBLE.add(Items.AZURE_BLUET);
        FORCED_EDIBLE.add(Items.RED_TULIP);
        FORCED_EDIBLE.add(Items.ORANGE_TULIP);
        FORCED_EDIBLE.add(Items.WHITE_TULIP);
        FORCED_EDIBLE.add(Items.PINK_TULIP);
        FORCED_EDIBLE.add(Items.OXEYE_DAISY);
        FORCED_EDIBLE.add(Items.CORNFLOWER);
        FORCED_EDIBLE.add(Items.LILY_OF_THE_VALLEY);
        FORCED_EDIBLE.add(Items.SUNFLOWER);
        FORCED_EDIBLE.add(Items.LILAC);
        FORCED_EDIBLE.add(Items.ROSE_BUSH);
        FORCED_EDIBLE.add(Items.PEONY);

        // Mushrooms
        FORCED_EDIBLE.add(Items.RED_MUSHROOM);
        FORCED_EDIBLE.add(Items.BROWN_MUSHROOM);

        // Wild Greens
        FORCED_EDIBLE.add(Items.GRASS);
        FORCED_EDIBLE.add(Items.TALL_GRASS);
        FORCED_EDIBLE.add(Items.FERN);
        FORCED_EDIBLE.add(Items.LARGE_FERN);
        FORCED_EDIBLE.add(Items.DEAD_BUSH);
        FORCED_EDIBLE.add(Items.SEA_PICKLE);
        FORCED_EDIBLE.add(Items.OAK_SAPLING);
        FORCED_EDIBLE.add(Items.SPRUCE_SAPLING);
        FORCED_EDIBLE.add(Items.BIRCH_SAPLING);
        FORCED_EDIBLE.add(Items.JUNGLE_SAPLING);
        FORCED_EDIBLE.add(Items.ACACIA_SAPLING);
        FORCED_EDIBLE.add(Items.DARK_OAK_SAPLING);
        FORCED_EDIBLE.add(Items.CHERRY_SAPLING);
        FORCED_EDIBLE.add(Items.MANGROVE_PROPAGULE);
        FORCED_EDIBLE.add(Items.WHEAT_SEEDS);
        FORCED_EDIBLE.add(Items.BEETROOT_SEEDS);
        FORCED_EDIBLE.add(Items.MELON_SEEDS);
        FORCED_EDIBLE.add(Items.PUMPKIN_SEEDS);
        FORCED_EDIBLE.add(Items.TORCHFLOWER_SEEDS);
        FORCED_EDIBLE.add(Items.PITCHER_POD);
        FORCED_EDIBLE.add(Items.VINE);
        FORCED_EDIBLE.add(Items.GLOW_LICHEN);
        FORCED_EDIBLE.add(Items.CRIMSON_FUNGUS);
        FORCED_EDIBLE.add(Items.WARPED_FUNGUS);
        FORCED_EDIBLE.add(Items.NETHER_SPROUTS);
        FORCED_EDIBLE.add(Items.WEEPING_VINES);
        FORCED_EDIBLE.add(Items.TWISTING_VINES);
        FORCED_EDIBLE.add(Items.CRIMSON_ROOTS);
        FORCED_EDIBLE.add(Items.WARPED_ROOTS);




        FORCED_EDIBLE.add(Items.SEAGRASS);
        FORCED_EDIBLE.add(Items.KELP); // already edible when dried, but raw kelp could be edible too



    }

    public static boolean isForcedEdible(Item item) {
        return FORCED_EDIBLE.contains(item);
    }

}
