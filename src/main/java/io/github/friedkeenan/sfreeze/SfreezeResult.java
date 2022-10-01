package io.github.friedkeenan.sfreeze;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

public class SfreezeResult {
    @Nullable
    private static <C extends Container, T extends Recipe<C>> SfreezeResult FindForType(RecipeType<T> type, RecipeManager manager, Item item, int count) {
        for (final var recipe : manager.getAllRecipesFor(type)) {
            final var result = recipe.getResultItem();

            if (result.is(item) && count >= result.getCount()) {
                final var ingredient = recipe.getIngredients().get(0).getItems()[0];

                return new SfreezeResult(ingredient.getItem(), ingredient.getCount() * (count / result.getCount()));
            }
        }

        return null;
    }

    @Nullable
    public static SfreezeResult find(RecipeManager manager, Item item, int count) {
        var cached_result = FindForType(RecipeType.SMELTING, manager, item, count);
        if (cached_result != null) {
            return cached_result;
        }

        cached_result = FindForType(RecipeType.SMOKING, manager, item, count);
        if (cached_result != null) {
            return cached_result;
        }

        cached_result = FindForType(RecipeType.BLASTING, manager, item, count);
        if (cached_result != null) {
            return cached_result;
        }

        return FindForType(RecipeType.CAMPFIRE_COOKING, manager, item, count);
    }

    public Item item;
    public int count;

    private SfreezeResult(Item item, int count) {
        this.item  = item;
        this.count = count;
    }
}
