package com.example.minecraftexpandedhardmode.transport;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class MovementModifierRegistry {

    private static final Map<Block, Float> MODIFIERS = new HashMap<>();

    static {
        // Slower terrain
        float slow = 0.85f;
        MODIFIERS.put(Blocks.GRASS_BLOCK, slow);
        MODIFIERS.put(Blocks.DIRT, slow);
        MODIFIERS.put(Blocks.COARSE_DIRT, slow);
        MODIFIERS.put(Blocks.ROOTED_DIRT, slow);
        MODIFIERS.put(Blocks.NETHERRACK, slow);
        MODIFIERS.put(Blocks.SNOW_BLOCK, slow);

        // Very slow terrain
        float verySlow = 0.50f;
        MODIFIERS.put(Blocks.FARMLAND, verySlow);
        MODIFIERS.put(Blocks.SAND, verySlow);
        MODIFIERS.put(Blocks.GRAVEL, verySlow);
        MODIFIERS.put(Blocks.SNOW, verySlow);

        // Normalize
        MODIFIERS.put(Blocks.DIRT_PATH, 1.01f);


    }

    public static float getModifier(Block block) {
        return MODIFIERS.getOrDefault(block, 1.0f);
    }
}
