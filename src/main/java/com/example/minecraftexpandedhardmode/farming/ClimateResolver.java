package com.example.minecraftexpandedhardmode.farming;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class ClimateResolver {

    public static ClimateType resolve(ServerWorld world, BlockPos pos) {
        RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
        Biome biome = biomeEntry.value();

        // Base temperature (0.0 = cold, 2.0 = hot)
        float temp = biome.getTemperature();

        // Local temperature (affected by height)
        float localTemp = biome.getTemperature();

        // Precipitation type (NONE, RAIN, SNOW)
        Biome.Precipitation precip = biome.getPrecipitation(pos);

        // Elevation (for mountain cold)
        int elevation = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ());

        // --- SNOWY CLIMATE ---
        // Cold biomes OR high elevation OR snow precipitation
        if (localTemp < 0.3f || precip == Biome.Precipitation.SNOW || elevation > 180) {
            return ClimateType.SNOWY;
        }

        // --- DRY CLIMATE ---
        // Hot biomes with no precipitation
        if (temp > 1.0f && precip == Biome.Precipitation.NONE) {
            return ClimateType.DRY;
        }

        // --- WET CLIMATE ---
        // Warm + rain OR jungle-like humidity
        if (precip == Biome.Precipitation.RAIN && temp > 0.8f) {
            return ClimateType.WET;
        }

        // --- GRASSY CLIMATE ---
        // Temperate, moderate rainfall
        return ClimateType.GRASSY;
    }
}
