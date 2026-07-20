package com.example.minecraftexpandedhardmode.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.minecraftexpandedhardmode.transport.MovementModifierRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Mixin(PlayerEntity.class)
public abstract class PlayerMovementMixin {

    @Inject(method = "travel", at = @At("TAIL"))
    private void applyTerrainMovementModifier(Vec3d movementInput, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;

        if (!player.isOnGround()) return;

        var world = player.getWorld();

        BlockPos feetPos = player.getBlockPos();
        BlockPos belowPos = feetPos.down();

        Block blockFeet = world.getBlockState(feetPos).getBlock();
        Block blockBelow = world.getBlockState(belowPos).getBlock();

        // Prefer the block at the player's feet if it's a terrain block
        float modifier = MovementModifierRegistry.getModifier(blockFeet);

        // If feet block is normal (1.0), try the block below
        if (modifier == 1.0f) {
            modifier = MovementModifierRegistry.getModifier(blockBelow);
        }

        // Apply modifier to horizontal velocity only
        Vec3d vel = player.getVelocity();
        player.setVelocity(vel.x * modifier, vel.y, vel.z * modifier);
    }
}
