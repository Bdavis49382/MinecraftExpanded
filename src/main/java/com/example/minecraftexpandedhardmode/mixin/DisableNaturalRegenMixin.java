package com.example.minecraftexpandedhardmode.mixin;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class DisableNaturalRegenMixin {

    @Inject(method = "tickRegeneration", at = @At("HEAD"), cancellable = true)
    private void disableNaturalRegen(CallbackInfo ci) {
        // Cancel vanilla natural regeneration entirely
        ci.cancel();
    }
}
