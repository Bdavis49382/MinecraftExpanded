package com.example.minecraftexpandedhardmode.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup MAIN_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier("minecraftexpandedhardmode", "main"),
            ItemGroup.create(ItemGroup.Row.TOP, 0)
                    .displayName(Text.literal("Minecraft Expanded"))
                    .icon(() -> new ItemStack(ModItems.SEED_POTATO))
                    .entries((context, entries) -> {
                        entries.add(ModItems.SEED_POTATO);
                        entries.add(ModItems.CARROT_SEEDS);
                    })
                    .build()
    );

    public static void init() {}
}
