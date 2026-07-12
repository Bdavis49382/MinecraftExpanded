package com.example.minecraftexpandedhardmode.mixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.player.HungerManager;

@Mixin(HungerManager.class)
public abstract class DisableNaturalRegenMixin {

    @Inject(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;heal(F)V"
        ),
        cancellable = true
    )
    private void disableNaturalRegen(PlayerEntity player, CallbackInfo ci) {
        ci.cancel();
    }
}
