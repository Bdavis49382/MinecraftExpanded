package com.example.minecraftexpandedhardmode.mixin;


import com.example.minecraftexpandedhardmode.hunger.FoodTierRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.FoodComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class FoodTierMixin {

    @Inject(method = "getFoodComponent", at = @At("HEAD"), cancellable = true)
    private void overrideFoodTier(CallbackInfoReturnable<FoodComponent> cir) {
        Item item = (Item)(Object)this;

        FoodComponent tier = FoodTierRegistry.getTier(item);
        if (tier != null) {
            cir.setReturnValue(tier);
        }
    }
}
