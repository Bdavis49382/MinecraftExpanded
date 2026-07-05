package com.example.minecraftexpandedhardmode.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ConstantFoodDrainMixin {

    private float drainAccumulator = 0f;

    // Drop hunger bar to just 2 sticks over the course of a day.
    private static final float DRAIN_PER_TICK = 16f / 24000f;

    @Inject(method = "tick", at = @At("HEAD"))
    private void applyConstantFoodDrain(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        HungerManager hunger = player.getHungerManager();

        drainAccumulator += DRAIN_PER_TICK;

        while (drainAccumulator >= 1f) {
            drainAccumulator -= 1f;

            int current = hunger.getFoodLevel();
            if (current > 0) {
                hunger.setFoodLevel(current - 1);
            }
        }
    }
}

