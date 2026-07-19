package com.example.minecraftexpandedhardmode.mixin;

import com.example.minecraftexpandedhardmode.farming.GrowthRateRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.util.math.random.Random;

@Mixin(CropBlock.class)
public abstract class CropGrowthMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void modifyCropGrowth(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        float multiplier = GrowthRateRegistry.getGrowthMultiplier(state.getBlock(), world, pos);

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
