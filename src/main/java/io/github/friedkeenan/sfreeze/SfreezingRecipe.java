package io.github.friedkeenan.sfreeze;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class SfreezingRecipe implements Recipe<Container> {
    public final Ingredient ingredient;
    public final Item result;

    public SfreezingRecipe(Ingredient ingredient, Item result) {
        this.ingredient = ingredient;
        this.result     = result;
    }

    @Override
    public boolean matches(Container var1, Level var2) {
        /* Stub implementation. */

        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registries) {
        /* Stub implementation. */

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int var1, int var2) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registries) {
        return new ItemStack(this.result);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(this.ingredient);

        return ingredients;
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
        private static final Codec<SfreezingRecipe> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                Ingredient.CODEC_NONEMPTY
                    .fieldOf("ingredient")
                    .forGetter(recipe -> recipe.ingredient),

                BuiltInRegistries.ITEM.byNameCodec()
                    .fieldOf("result")
                    .forGetter(recipe -> recipe.result)
            ).apply(instance, SfreezingRecipe::new)
        );

        @Override
        public Codec<SfreezingRecipe> codec() {
            return CODEC;
        }

        @Override
        public SfreezingRecipe fromNetwork(FriendlyByteBuf buf) {
            throw new AssertionError("Sfreezing recipes should never be sent to the client");
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SfreezingRecipe recipe) {
            throw new AssertionError("Sfreezing recipes should never be sent to the client");
        }

    }
}
