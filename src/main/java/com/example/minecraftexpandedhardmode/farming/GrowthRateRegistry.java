package com.example.minecraftexpandedhardmode.farming;

import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class GrowthRateRegistry {

    public static float getGrowthMultiplier(Block block, ServerWorld world, BlockPos pos) {

        ClimateType climate = ClimateResolver.resolve(world, pos);
        ClimateType affinity = GrowthAffinityRegistry.getAffinity(block);

        if (climate == affinity) {
            return 1.1f; // grows faster in preferred climate
        }

        // Opposing climates grow slower
        return 0.6f;
    }
}
