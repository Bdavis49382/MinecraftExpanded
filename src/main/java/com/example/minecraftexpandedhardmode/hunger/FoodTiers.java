package com.example.minecraftexpandedhardmode.hunger;

import net.minecraft.item.FoodComponent;

public class FoodTiers {

    // Pops = hunger points (each pop = 2 hunger)
    public static final FoodComponent FULL_MEAL =
        new FoodComponent.Builder().hunger(16).saturationModifier(0.8f).build();

    public static final FoodComponent MAIN_DISH =
        new FoodComponent.Builder().hunger(10).saturationModifier(0.6f).build();

    public static final FoodComponent SIDE =
        new FoodComponent.Builder().hunger(6).saturationModifier(0.2f).build();

    public static final FoodComponent SNACK =
        new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent TRASH =
        new FoodComponent.Builder().hunger(1).saturationModifier(0.05f).build();
}

