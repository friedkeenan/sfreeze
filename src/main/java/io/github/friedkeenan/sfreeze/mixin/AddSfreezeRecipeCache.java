package io.github.friedkeenan.sfreeze.mixin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import io.github.friedkeenan.sfreeze.SfreezeRecipeCacher;
import io.github.friedkeenan.sfreeze.SfreezingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

@Mixin(RecipeManager.class)
public abstract class AddSfreezeRecipeCache implements SfreezeRecipeCacher {
    @Nullable
    private Map<ResourceLocation, SfreezingRecipe> old_sfreezing_recipes = null;
    private Map<Item, Optional<Item>> sfreezing_cache = new HashMap<>();

    @Shadow
    protected abstract <C extends Container, T extends Recipe<C>> Map<ResourceLocation, T> byType(RecipeType<T> type);

    @Override
    @Nullable
    public Optional<Item> findResult(Item item) {
        final var sfreezing_recipes = this.byType(SfreezeMod.SFREEZING);
        if (sfreezing_recipes != this.old_sfreezing_recipes) {
            this.sfreezing_cache.clear();
        }

        this.old_sfreezing_recipes = sfreezing_recipes;

        if (this.sfreezing_cache.containsKey(item)) {
            return this.sfreezing_cache.get(item);
        }

        for (final var recipe : sfreezing_recipes.values()) {
            if (recipe.ingredient.test(new ItemStack(item))) {
                final var result = Optional.of(recipe.result);
                this.sfreezing_cache.put(item, result);

                return result;
            }
        }

        final var result = Optional.<Item>empty();
        this.sfreezing_cache.put(item, result);

        return result;
    }
}
