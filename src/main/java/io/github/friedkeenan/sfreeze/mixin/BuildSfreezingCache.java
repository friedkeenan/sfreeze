package io.github.friedkeenan.sfreeze.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import io.github.friedkeenan.sfreeze.SfreezingResultHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.item.crafting.RecipeManager;

@Mixin(ReloadableServerResources.class)
public class BuildSfreezingCache {
    @Shadow
    @Final
    private RecipeManager recipes;

    private void clearCache() {
        BuiltInRegistries.ITEM.forEach(item -> ((SfreezingResultHolder) item).setResult(Optional.empty()));
    }

    private void refreshCache() {
        this.clearCache();

        for (final var recipe : this.recipes.getAllRecipesFor(SfreezeMod.SFREEZING)) {
            for (final var ingredient : recipe.value().ingredient.getItems()) {
                final var result_holder = (SfreezingResultHolder) ingredient.getItem();

                result_holder.setResult(Optional.of(recipe.value().result));
            }
        }
    }

    /*
        We inject in the tag update function because that is always called on reload and is easy.
        The game also rebuilds its cache of whether block states have a dynamic shape or not here.
    */
    @Inject(at = @At("TAIL"), method = "updateRegistryTags")
    private void refreshCacheOnDataLoad(RegistryAccess access, CallbackInfo info) {
        this.refreshCache();
    }
}
