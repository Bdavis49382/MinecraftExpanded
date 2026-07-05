package com.example.minecraftexpandedhardmode.mixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class SleepHealingMixin extends LivingEntity {

    public SleepHealingMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "wakeUp", at = @At("TAIL"))
    private void healOnWakeUp(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;

        // Hunger threshold for healing (vanilla uses >= 18)
        if (player.getHungerManager().getFoodLevel() >= 18) {

            float current = player.getHealth();
            float max = player.getMaxHealth();

            // Heal up to 10 HP (5 hearts)
            float healed = Math.min(current + 10.0f, max);

            player.setHealth(healed);
        }
    }
}

