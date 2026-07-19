package com.example.minecraftexpandedhardmode.registry;

import com.example.minecraftexpandedhardmode.item.SeedPotatoItem;
import com.example.minecraftexpandedhardmode.item.CarrotSeedsItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SEED_POTATO = register("seed_potato",
            new SeedPotatoItem(Blocks.POTATOES, new Item.Settings()));

    public static final Item CARROT_SEEDS = register("carrot_seeds",
            new CarrotSeedsItem(Blocks.CARROTS, new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier("minecraftexpandedhardmode", name),
                item);
    }

    public static void init() {
        // triggers class loading
    }
}
