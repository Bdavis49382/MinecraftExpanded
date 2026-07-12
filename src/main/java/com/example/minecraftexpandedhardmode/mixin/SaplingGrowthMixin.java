package com.example.minecraftexpandedhardmode.mixin;


import com.example.minecraftexpandedhardmode.farming.ClimateResolver;
import com.example.minecraftexpandedhardmode.farming.ClimateType;
import com.example.minecraftexpandedhardmode.farming.GrowthRateRegistry;
import com.example.minecraftexpandedhardmode.MinecraftExpandedMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaplingBlock.class)
public abstract class SaplingGrowthMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void modifySaplingGrowth(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        // Trees should be in general slower than other plants, so they get a -.3 chance for growth.
        float multiplier = GrowthRateRegistry.getGrowthMultiplier(state.getBlock(), world, pos) -.3F;

        ClimateType climate = ClimateResolver.resolve(world, pos);
        MinecraftExpandedMod.LOGGER.info("[GrowthDebug] Sapling " + state.getBlock().getName().getString() +
                    " at " + pos +
                    " in climate " + climate +
                    " has multiplier " + multiplier);

        // Debug log: fires occasionally when sapling gets a growth tick
        if (world.getRandom().nextInt(200) == 0) {
            MinecraftExpandedMod.LOGGER.info("[GrowthDebug] Sapling " + state.getBlock().getName().getString() +
                    " at " + pos +
                    " in climate " + climate +
                    " has multiplier " + multiplier);
        }


        if (multiplier < 1.0f) {
            if (random.nextFloat() > multiplier) {
                ci.cancel();
                return;
            }
        }

        if (multiplier > 1.0f) {
            if (random.nextFloat() < (multiplier - 1.0f)) {
                ((SaplingBlock)(Object)this).generate(world, pos, state, random);
            }
        }
    }
}
