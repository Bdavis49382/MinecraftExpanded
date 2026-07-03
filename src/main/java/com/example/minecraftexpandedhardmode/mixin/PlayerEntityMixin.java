package com.example.minecraftexpandedhardmode.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import com.example.minecraftexpandedhardmode.MinecraftExpandedMod;
import com.example.minecraftexpandedhardmode.MiningConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    
    /**
     * Mixin into getBlockBreakingSpeed to apply mining penalties.
     * Correct tools mine with the speed of the next lower tier.
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
        
        if (mainHand.isEmpty()) {
            cir.setReturnValue(original * 0.1f);
            return;
        }

        if (!(mainHand.getItem() instanceof ToolItem toolItem) || !isToolEffectiveForBlock(mainHand, state)) {
            float newSpeed = original * 0.2f;
            MinecraftExpandedMod.LOGGER.info("Mining speed adjusted: original={} new={} tool={}", original, newSpeed, mainHand.getItem().getTranslationKey());
            cir.setReturnValue(newSpeed);
            return;
        }

        ToolMaterial material = toolItem.getMaterial();
        float tierRatio = MiningConfig.getTierDowngradeRatio(material);
        float newSpeed = original * tierRatio;
        MinecraftExpandedMod.LOGGER.info("Mining speed adjusted: original={} new={} tool={} material={} ratio={}",
                original, newSpeed, mainHand.getItem().getTranslationKey(), material.getClass().getName(), tierRatio);
        cir.setReturnValue(newSpeed);
    }

    private static float getTierDowngradeRatio(ToolMaterial material) {
        return MiningConfig.getTierDowngradeRatio(material);
    }



    /**
     * Simple check: if it's a ToolItem, verify it's appropriate for this block.
     * More sophisticated logic can be added later (e.g., check tag-based tool types).
     */
    private boolean isToolEffectiveForBlock(ItemStack tool, BlockState state) {
        if (tool.getItem() instanceof ToolItem toolItem) {
            boolean correctToolType =
                (state.isIn(BlockTags.PICKAXE_MINEABLE) && toolItem instanceof PickaxeItem) ||
                (state.isIn(BlockTags.AXE_MINEABLE) && toolItem instanceof AxeItem) ||
                (state.isIn(BlockTags.SHOVEL_MINEABLE) && toolItem instanceof ShovelItem) ||
                (state.isIn(BlockTags.HOE_MINEABLE) && toolItem instanceof HoeItem);

            return correctToolType;
        }
        return false;
    }
}

