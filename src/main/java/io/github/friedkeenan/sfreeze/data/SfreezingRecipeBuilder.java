package io.github.friedkeenan.sfreeze.data;

import java.util.function.Consumer;

import com.google.gson.JsonObject;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

public class SfreezingRecipeBuilder {
    public static class Result implements FinishedRecipe {
        public final ResourceLocation id;
        public final Ingredient ingredient;
        public final Item result;

        public Result(ResourceLocation id, Ingredient ingredient, Item result) {
            this.id         = id;
            this.ingredient = ingredient;
            this.result     = result;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredient", this.ingredient.toJson());
            json.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return SfreezeMod.SFREEZING_SERIALIZER;
        }

        @Override
        public JsonObject serializeAdvancement() {
            /* Stub implementation. */
            return null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            /* Stub implementation. */
            return null;
        }
    }

    /* We defer saving so that we cannot forget to do it. */
    public static class DeferredSaver {
        public final Consumer<FinishedRecipe> exporter;

        public DeferredSaver(Consumer<FinishedRecipe> exporter) {
            this.exporter = exporter;
        }

        public void sfreezing(ItemLike ingredient, ItemLike result) {
            this.exporter.accept(new Result(RecipeBuilder.getDefaultRecipeId(result), Ingredient.of(ingredient), result.asItem()));
        }
    }

    public static DeferredSaver save(Consumer<FinishedRecipe> exporter) {
        return new DeferredSaver(exporter);
    }
}
