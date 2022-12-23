package io.github.friedkeenan.sfreeze.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import io.github.friedkeenan.sfreeze.SfreezeRecipeCacher;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

@Mixin(PlayerList.class)
public class StopClientboundSfreezingRecipe {
    private static Collection<Recipe<?>> NonSfreezingRecipes(RecipeManager manager) {
        return ((SfreezeRecipeCacher) manager).getNonSfreezingRecipes();
    }

    @Redirect(
        at = @At(
            value  = "INVOKE",
            target = "Lnet/minecraft/world/item/crafting/RecipeManager;getRecipes()Ljava/util/Collection;"
        ),

        method = "placeNewPlayer"
    )
    private Collection<Recipe<?>> neglectSendingSfreezingRecipesToNewPlayer(RecipeManager manager) {
        return NonSfreezingRecipes(manager);
    }

    @Redirect(
        at = @At(
            value  = "INVOKE",
            target = "Lnet/minecraft/world/item/crafting/RecipeManager;getRecipes()Ljava/util/Collection;"
        ),

        method = "reloadResources"
    )
    private Collection<Recipe<?>> neglectSendingSfreezingRecipesOnReload(RecipeManager manager) {
        return NonSfreezingRecipes(manager);
    }
}
