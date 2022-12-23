package io.github.friedkeenan.sfreeze.data;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

public class SfreezeDataGenerator implements DataGeneratorEntrypoint {
    public static class SfreezeRecipeGenerator extends FabricRecipeProvider {
        public SfreezeRecipeGenerator(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void buildRecipes(Consumer<FinishedRecipe> exporter) {
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.BAKED_POTATO,                       Items.POTATO);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.BLACK_GLAZED_TERRACOTTA,            Items.BLACK_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.BLUE_GLAZED_TERRACOTTA,             Items.BLUE_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.BRICK,                              Items.CLAY_BALL);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.BROWN_GLAZED_TERRACOTTA,            Items.BROWN_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CHARCOAL,                           Items.OAK_LOG);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_BEEF,                        Items.BEEF);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_CHICKEN,                     Items.CHICKEN);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_COD,                         Items.COD);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_MUTTON,                      Items.MUTTON);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_PORKCHOP,                    Items.PORKCHOP);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_RABBIT,                      Items.RABBIT);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COOKED_SALMON,                      Items.SALMON);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.COPPER_INGOT,                       Items.RAW_COPPER);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CRACKED_DEEPSLATE_BRICKS,           Items.DEEPSLATE_BRICKS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CRACKED_DEEPSLATE_TILES,            Items.DEEPSLATE_TILES);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CRACKED_NETHER_BRICKS,              Items.NETHER_BRICKS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICKS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CRACKED_STONE_BRICKS,               Items.STONE_BRICKS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.CYAN_GLAZED_TERRACOTTA,             Items.CYAN_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.DEEPSLATE,                          Items.COBBLED_DEEPSLATE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.DRIED_KELP,                         Items.KELP);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.GLASS,                              Items.SAND);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.GOLD_INGOT,                         Items.RAW_GOLD);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.GRAY_GLAZED_TERRACOTTA,             Items.GRAY_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.GREEN_DYE,                          Items.CACTUS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.GREEN_GLAZED_TERRACOTTA,            Items.GREEN_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.IRON_INGOT,                         Items.RAW_IRON);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.LIGHT_BLUE_GLAZED_TERRACOTTA,       Items.LIGHT_BLUE_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.LIGHT_GRAY_GLAZED_TERRACOTTA,       Items.LIGHT_GRAY_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.LIME_DYE,                           Items.SEA_PICKLE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.LIME_GLAZED_TERRACOTTA,             Items.LIME_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.MAGENTA_GLAZED_TERRACOTTA,          Items.MAGENTA_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.NETHERITE_SCRAP,                    Items.ANCIENT_DEBRIS);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.NETHER_BRICK,                       Items.NETHERRACK);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.ORANGE_GLAZED_TERRACOTTA,           Items.ORANGE_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.PINK_GLAZED_TERRACOTTA,             Items.PINK_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.POPPED_CHORUS_FRUIT,                Items.CHORUS_FRUIT);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.PURPLE_GLAZED_TERRACOTTA,           Items.PURPLE_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.RED_GLAZED_TERRACOTTA,              Items.RED_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SMOOTH_BASALT,                      Items.BASALT);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SMOOTH_QUARTZ,                      Items.QUARTZ_BLOCK);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SMOOTH_RED_SANDSTONE,               Items.RED_SANDSTONE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SMOOTH_SANDSTONE,                   Items.SANDSTONE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SMOOTH_STONE,                       Items.STONE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.SPONGE,                             Items.WET_SPONGE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.STONE,                              Items.COBBLESTONE);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.TERRACOTTA,                         Items.CLAY);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.WHITE_GLAZED_TERRACOTTA,            Items.WHITE_TERRACOTTA);
            SfreezingRecipeBuilder.save(exporter).sfreezing(Items.YELLOW_GLAZED_TERRACOTTA,           Items.YELLOW_TERRACOTTA);
        }
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        final var pack = generator.createPack();

        pack.addProvider(SfreezeRecipeGenerator::new);
    }
}
