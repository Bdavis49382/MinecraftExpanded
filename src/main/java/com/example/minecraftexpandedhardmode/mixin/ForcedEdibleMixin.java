package com.example.minecraftexpandedhardmode.mixin;

import com.example.minecraftexpandedhardmode.hunger.FoodTierRegistry;
import com.example.minecraftexpandedhardmode.hunger.FoodTiers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ForcedEdibleMixin {

    // 1. Make the item edible by intercepting right-click
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void forceUse(World world, PlayerEntity user, Hand hand,
                          CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {

        Item item = (Item)(Object)this;

        if (FoodTierRegistry.isForcedEdible(item)) {
            ItemStack stack = user.getStackInHand(hand);

            // Begin eating
            user.setCurrentHand(hand);
            cir.setReturnValue(TypedActionResult.consume(stack));
        }
    }

    // 2. Provide a FoodComponent so Minecraft knows hunger/saturation
    @Inject(method = "getFoodComponent", at = @At("HEAD"), cancellable = true)
    private void forceFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        Item item = (Item)(Object)this;

        if (FoodTierRegistry.isForcedEdible(item)) {
            cir.setReturnValue(FoodTiers.SNACK); // or whichever tier you want
        }
    }

    // 3. Eating animation type (EAT)
    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    private void forceUseAction(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        Item item = (Item)(Object)this;

        if (FoodTierRegistry.isForcedEdible(item)) {
            cir.setReturnValue(UseAction.EAT);
        }
    }

    // 4. Eating duration (matches your eating-duration mixin)
    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void forceUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        Item item = (Item)(Object)this;

        if (FoodTierRegistry.isForcedEdible(item)) {
            cir.setReturnValue(48); // 2.4 seconds
        }
    }

    // 5. Apply hunger when eating finishes
    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void forceFinish(ItemStack stack, World world, LivingEntity user,
                             CallbackInfoReturnable<ItemStack> cir) {

        Item item = (Item)(Object)this;

        if (FoodTierRegistry.isForcedEdible(item)) {
            FoodComponent food = FoodTiers.SNACK;

            if (user instanceof PlayerEntity player) {
                player.getHungerManager().add(food.getHunger(), food.getSaturationModifier());

                // Consume one item (vanilla behavior)
                ItemStack result = stack.copy();
                result.decrement(1);
                cir.setReturnValue(result);
            }

        }
    }
}
