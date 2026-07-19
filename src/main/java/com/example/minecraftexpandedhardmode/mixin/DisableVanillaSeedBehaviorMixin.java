package com.example.minecraftexpandedhardmode.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.item.BlockItem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.example.minecraftexpandedhardmode.MinecraftExpandedMod;

@Mixin(BlockItem.class)
public abstract class DisableVanillaSeedBehaviorMixin {

    @Inject(
        method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void disableCarrotPotatoPlanting(ItemPlacementContext context,
                                             BlockState state,
                                             CallbackInfoReturnable<Boolean> cir) {

        Item item = context.getStack().getItem();

        // Only intercept carrot/potato
        if (item != Items.CARROT && item != Items.POTATO) {
            return;
        }

        System.out.println("[SeedDebug] Prevented planting: " + item);

        cir.setReturnValue(false);  // planting fails
    }
}
