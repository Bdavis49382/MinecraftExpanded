package com.example.minecraftexpandedhardmode;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class MiningConfig {
    private static final String CONFIG_FILE_NAME = "minecraftexpanded.properties";
    private static final Path PROJECT_CONFIG_PATH = Path.of("config", CONFIG_FILE_NAME);
    private static final Path ROOT_CONFIG_PATH = Path.of(CONFIG_FILE_NAME);
    private static final Map<Integer, Float> tierRatios = new HashMap<>();

    static {
        tierRatios.put(0, 0.2f);
        tierRatios.put(1, 0.5f);
        tierRatios.put(2, 0.6667f);
        tierRatios.put(3, 0.75f);
        tierRatios.put(4, 0.8889f);
    }

    private MiningConfig() {
    }

    public static void loadConfig() {
        Path configPath = findConfigPath();
        if (configPath == null) {
            MinecraftExpandedMod.LOGGER.warn("Could not determine config path for mining ratios; using defaults.");
            return;
        }

        if (Files.notExists(configPath)) {
            createDefaultConfig(configPath);
        }

        loadProperties(configPath);
    }

    public static float getTierDowngradeRatio(ToolMaterial material) {
        return tierRatios.getOrDefault(material.getMiningLevel(), 1.0f);
    }

    private static Path findConfigPath() {
        if (Files.exists(ROOT_CONFIG_PATH)) {
            return ROOT_CONFIG_PATH;
        }
        if (Files.exists(PROJECT_CONFIG_PATH)) {
            return PROJECT_CONFIG_PATH;
        }

        try {
            Path fabricConfigPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME);
            if (Files.exists(fabricConfigPath)) {
                return fabricConfigPath;
            }
        } catch (Throwable ignored) {
            // Fabric loader may not be available at all times during some tests.
        }

        return PROJECT_CONFIG_PATH;
    }

    private static void createDefaultConfig(Path path) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            String defaultContent = "# Minecraft Expanded mining downgrade ratios\n"
                    + "# Use mining.level=N values where N is the tool material mining level.\n"
                    + "# Example: 3=0.75 means diamond tools mine at 75% of their original speed.\n"
                    + "0=0.2\n"
                    + "1=0.5\n"
                    + "2=0.6667\n"
                    + "3=0.75\n"
                    + "4=0.8889\n";
            Files.writeString(path, defaultContent);
            MinecraftExpandedMod.LOGGER.info("Created default mining config at {}", path);
        } catch (IOException e) {
            MinecraftExpandedMod.LOGGER.error("Unable to create default mining config: {}", e.getMessage());
        }
    }

    private static void loadProperties(Path path) {
        Properties properties = new Properties();
        try (InputStream stream = Files.newInputStream(path)) {
            properties.load(stream);
            for (String key : properties.stringPropertyNames()) {
                if (!key.matches("\\d+")) {
                    MinecraftExpandedMod.LOGGER.warn("Ignoring invalid mining config key: {}", key);
                    continue;
                }

                int level = Integer.parseInt(key);
                try {
                    float ratio = Float.parseFloat(properties.getProperty(key));
                    tierRatios.put(level, ratio);
                } catch (NumberFormatException ex) {
                    MinecraftExpandedMod.LOGGER.warn("Invalid ratio for mining level {}: {}", level, properties.getProperty(key));
                }
            }
            MinecraftExpandedMod.LOGGER.info("Loaded mining ratio config from {}", path);
        } catch (IOException e) {
            MinecraftExpandedMod.LOGGER.error("Unable to load mining config {}: {}", path, e.getMessage());
        }
    }
}
