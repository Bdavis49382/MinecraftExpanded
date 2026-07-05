package com.example.minecraftexpandedhardmode.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class EatingDurationMixin {

    // Change this to whatever duration you want.
    // Vanilla is 32 ticks (1.6 seconds). 48 = 2.4 seconds. 64 = 3.2 seconds.
    private static final int EXTRA_EATING_DURATION = 64;

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void modifyEatingDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        Item item = (Item)(Object)this;

        // Only modify food items
        FoodComponent food = item.getFoodComponent();
        if (food != null) {
            cir.setReturnValue(EXTRA_EATING_DURATION);
        }
    }
}

