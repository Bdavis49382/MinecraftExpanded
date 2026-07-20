package com.example.minecraftexpandedhardmode.loot;

import com.example.minecraftexpandedhardmode.farming.GrowthAffinityRegistry;
import com.example.minecraftexpandedhardmode.registry.ModItems;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.state.property.IntProperty;


import java.util.Random;

public class CropDropsHandler {

    private static final Random RANDOM = new Random();

    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (!(world instanceof ServerWorld serverWorld)) return;

            Block block = state.getBlock();

            // --- GRASS DROPS ---
            if (block == Blocks.GRASS ||
                block == Blocks.TALL_GRASS ||
                block == Blocks.FERN ||
                block == Blocks.LARGE_FERN) {

                // Prevent vanilla drops
                state.getBlock().onBreak(world, pos, state, player);
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);

                handleGrassDrops(serverWorld, player, pos);
                return; // Grass never needs crop logic
            }

            if (!isTargetCrop(block)) return;

            // Prevent vanilla drops
            state.getBlock().onBreak(world, pos, state, player);

            handleCropDrops(serverWorld, player, pos, state);
        });
    }

    private static boolean isTargetCrop(Block block) {
        return block == Blocks.WHEAT
                || block == Blocks.CARROTS
                || block == Blocks.POTATOES
                || block == Blocks.BEETROOTS;
    }

    private static void handleGrassDrops(ServerWorld world, PlayerEntity player, BlockPos pos) {

        // Vanilla grass drop chance: 12.5%
        if (RANDOM.nextDouble() > 0.125) return;

        int fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, player.getMainHandStack());

        Item seed = GrowthAffinityRegistry.getRandomSeedForBiome(world, pos);
        if (seed == Items.AIR) return;

        drop(world, pos, new ItemStack(seed, 1 + fortune));
    }


    private static void handleCropDrops(ServerWorld world,
                                        PlayerEntity player,
                                        BlockPos pos,
                                        net.minecraft.block.BlockState state) {

        if (!(state.getBlock() instanceof CropBlock crop)) return;

        IntProperty ageProperty;

        if (state.getBlock() == Blocks.BEETROOTS) {
            ageProperty = BeetrootsBlock.AGE; // 0–3
        } else {
            ageProperty = CropBlock.AGE;      // 0–7 for wheat, carrots, potatoes
        }

        int age = state.get(ageProperty);
        boolean fullyGrown = age >= crop.getMaxAge();



        Item seed = getSeedItem(state.getBlock());
        Item produce = getProduceItem(state.getBlock());

        // Hoe-specific harvest rules (fully grown only)
        ItemStack tool = player.getMainHandStack();
        boolean isHoe = tool.isIn(ItemTags.HOES);

        if (fullyGrown && isHoe) {
            double roll = RANDOM.nextDouble();
            int fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, tool);

            if (roll < 0.70) {
                // 70% → 2 seeds
                drop(world, pos, new ItemStack(seed, 2 + fortune));
            } else if (roll < 0.90) {
                // 20% → 1 seed
                drop(world, pos, new ItemStack(seed, 1 + fortune));
            } else {
                // 10% → nothing
            }

            return; // Skip normal drop logic
        }

        int fortune = EnchantmentHelper.getLevel(Enchantments.FORTUNE, player.getMainHandStack());

        if (!fullyGrown) {
            // 50% seed, 50% nothing
            if (RANDOM.nextDouble() < 0.5) {
                drop(world, pos, new ItemStack(seed, 1 + fortune));
            }
            return;
        }

        // Fully grown probability table
        double roll = RANDOM.nextDouble();

        int seedCount = 0;
        int produceCount = 0;

        if (roll < 0.30) {
            seedCount = 1;
            produceCount = 1;
        } else if (roll < 0.50) {
            seedCount = 2;
        } else if (roll < 0.70) {
            produceCount = 2;
        } else if (roll < 0.80) {
            produceCount = 1;
        } else if (roll < 0.90) {
            seedCount = 1;
        } else {
            // 10% nothing
        }

        // Apply fortune uniformly
        if (seedCount > 0) {
            drop(world, pos, new ItemStack(seed, seedCount + fortune));
        }
        if (produceCount > 0) {
            if (produce == Items.POTATO) {
                dropMutatedPotatoes(world, pos, produceCount + fortune);
            } else {
                drop(world, pos, new ItemStack(produce, produceCount + fortune));
            }
        }
    }

    private static Item getSeedItem(Block block) {
        if (block == Blocks.CARROTS) return ModItems.CARROT_SEEDS;
        if (block == Blocks.POTATOES) return ModItems.SEED_POTATO;
        if (block == Blocks.WHEAT) return Items.WHEAT_SEEDS;
        if (block == Blocks.BEETROOTS) return Items.BEETROOT_SEEDS;
        return Items.AIR;
    }

    private static Item getProduceItem(Block block) {
        if (block == Blocks.CARROTS) return Items.CARROT;
        if (block == Blocks.POTATOES) return Items.POTATO;
        if (block == Blocks.WHEAT) return Items.WHEAT;
        if (block == Blocks.BEETROOTS) return Items.BEETROOT;
        return Items.AIR;
    }

    private static void drop(ServerWorld world, BlockPos pos, ItemStack stack) {
        Block.dropStack(world, pos, stack);
    }

    private static void dropMutatedPotatoes(ServerWorld world, BlockPos pos, int total) {
        int normal = total;
        int poisonous = 0;

        for (int i = 0; i < total; i++) {
            if (RANDOM.nextDouble() < 0.01) {
                poisonous++;
                normal--;
            }
        }

        if (normal > 0) {
            drop(world, pos, new ItemStack(Items.POTATO, normal));
        }
        if (poisonous > 0) {
            drop(world, pos, new ItemStack(Items.POISONOUS_POTATO, poisonous));
        }
    }
}
