package com.example.minecraftexpandedhardmode.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.tag.BlockTags;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    
    /**
     * Mixin into getBlockBreakingSpeed to apply mining penalties.
     * Hand mining and wrong tools get significant speed reduction.
     */
    @Inject(
        method = "getBlockBreakingSpeed",
        at = @At("RETURN"),
        cancellable = true
    )
    private void modifyBlockBreakingSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack mainHand = player.getMainHandStack();
        
        // If not holding an item (hand mining), apply heavy penalty
        if (mainHand.isEmpty()) {
            cir.setReturnValue(original * 0.1f); // 90% slowdown
            return;
        }
        
        // Check if tool is appropriate for the block
        if (!isToolEffectiveForBlock(mainHand, state)) {
            cir.setReturnValue(original * 0.2f); // 80% slowdown for wrong tool
            return;
        }
        
        // Correct tool or generic item: use vanilla speed (no modification)
    }
    
    /**
     * Simple check: if it's a ToolItem, verify it's appropriate for this block.
     * More sophisticated logic can be added later (e.g., check tag-based tool types).
     */
    private boolean isToolEffectiveForBlock(ItemStack tool, BlockState state) {
        if (tool.getItem() instanceof ToolItem toolItem) {
            boolean correctToolType =
                (state.isIn(BlockTags.PICKAXE_MINEABLE) && toolItem instanceof PickaxeItem) ||
                (state.isIn(BlockTags.AXE_MINEABLE) && tool.getItem() instanceof AxeItem) ||
                (state.isIn(BlockTags.SHOVEL_MINEABLE) && tool.getItem() instanceof ShovelItem) ||
                (state.isIn(BlockTags.HOE_MINEABLE) && tool.getItem() instanceof HoeItem);

            // Check if the tool's effective blocks include this block state
            return correctToolType;
        }
        // Non-tool items are considered "wrong tool"
        return false;
    }
}

