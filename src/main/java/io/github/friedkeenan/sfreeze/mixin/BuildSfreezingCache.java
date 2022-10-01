package io.github.friedkeenan.sfreeze.mixin;

import java.util.Map;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import io.github.friedkeenan.sfreeze.SfreezingResultHolder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

@Mixin(RecipeManager.class)
public class BuildSfreezingCache {
    private RecipeManager asRecipeManager() {
        return (RecipeManager) (Object) this;
    }

    private void clearCache() {
        Registry.ITEM.forEach(item -> ((SfreezingResultHolder) item).setResult(Optional.empty()));
    }

    private void refreshCache() {
        this.clearCache();

        for (final var recipe : this.asRecipeManager().getAllRecipesFor(SfreezeMod.SFREEZING)) {
            for (final var ingredient : recipe.ingredient.getItems()) {
                final var result_holder = (SfreezingResultHolder) ingredient.getItem();

                result_holder.setResult(Optional.of(recipe.result));
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "apply")
    private void refreshCacheOnApply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo info) {
        this.refreshCache();
    }

    @Inject(at = @At("TAIL"), method = "replaceRecipes")
    private void refreshCacheOnReplaceRecipes(Iterable<Recipe<?>> recipes, CallbackInfo info) {
        this.refreshCache();
    }
}
