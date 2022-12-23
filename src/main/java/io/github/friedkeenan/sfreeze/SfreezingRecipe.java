package io.github.friedkeenan.sfreeze;

import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class SfreezingRecipe implements Recipe<Container> {
    public final ResourceLocation id;
    public final Ingredient ingredient;
    public final Item result;

    public SfreezingRecipe(ResourceLocation id, Ingredient ingredient, Item result) {
        this.id         = id;
        this.ingredient = ingredient;
        this.result     = result;
    }

    @Override
    public boolean matches(Container var1, Level var2) {
        /* Stub implementation. */

        return false;
    }

    @Override
    public ItemStack assemble(Container var1) {
        /* Stub implementation. */

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int var1, int var2) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(this.result);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(this.ingredient);

        return ingredients;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SfreezeMod.SFREEZING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return SfreezeMod.SFREEZING;
    }

    public static class Serializer implements RecipeSerializer<SfreezingRecipe> {

        @Override
        public SfreezingRecipe fromJson(ResourceLocation id, JsonObject json) {
            final var ingredient = Ingredient.fromJson(
                GsonHelper.isArrayNode(json, "ingredient") ?

                GsonHelper.getAsJsonArray(json,  "ingredient") :
                GsonHelper.getAsJsonObject(json, "ingredient")
            );

            final var result_location = GsonHelper.getAsString(json, "result");
            final var result = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(result_location)).orElseThrow(
                () -> new IllegalStateException("Item: " + result_location + " does not exist")
            );

            return new SfreezingRecipe(id, ingredient, result);
        }

        @Override
        public SfreezingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            final var ingredient = Ingredient.fromNetwork(buf);
            final var result     = buf.readById(BuiltInRegistries.ITEM);

            return new SfreezingRecipe(id, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SfreezingRecipe recipe) {
            recipe.ingredient.toNetwork(buf);
            buf.writeId(BuiltInRegistries.ITEM, recipe.result);
        }

    }
}
