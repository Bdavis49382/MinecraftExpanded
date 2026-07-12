package com.example.minecraftexpandedhardmode.mixin;

import com.example.minecraftexpandedhardmode.farming.ClimateResolver;
import com.example.minecraftexpandedhardmode.farming.ClimateType;
import com.example.minecraftexpandedhardmode.farming.GrowthRateRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.example.minecraftexpandedhardmode.MinecraftExpandedMod;

import net.minecraft.util.math.random.Random;

@Mixin(CropBlock.class)
public abstract class CropGrowthMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void modifyCropGrowth(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        float multiplier = GrowthRateRegistry.getGrowthMultiplier(state.getBlock(), world, pos);

        ClimateType climate = ClimateResolver.resolve(world, pos);
        MinecraftExpandedMod.LOGGER.info("resolved: " + climate.name());

        // Debug log: fires only when a crop receives a growth tick
        if (world.getRandom().nextInt(200) == 0) { // 0.5% chance to avoid spam
            MinecraftExpandedMod.LOGGER.info("[GrowthDebug] Crop " + state.getBlock().getName().getString() +
                    " at " + pos +
                    " in climate " + climate +
                    " has multiplier " + multiplier);
        }

        if (multiplier < 1.0f) {
            if (random.nextFloat() > multiplier) {
                ci.cancel();
            }
        }

        if (multiplier > 1.0f) {
            if (random.nextFloat() < (multiplier - 1.0f)) {
                ((CropBlock)(Object)this).applyGrowth(world, pos, state);
            }
        }
    }
}
