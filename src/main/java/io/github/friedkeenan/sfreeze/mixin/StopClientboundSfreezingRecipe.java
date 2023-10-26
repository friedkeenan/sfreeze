package io.github.friedkeenan.sfreeze.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import io.github.friedkeenan.sfreeze.SfreezeRecipeCacher;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

@Mixin(PlayerList.class)
public class StopClientboundSfreezingRecipe {
    private static Collection<Recipe<?>> NonSfreezingRecipes(RecipeManager manager) {
        return ((SfreezeRecipeCacher) manager).getNonSfreezingRecipes();
    }

    @WrapOperation(
        at = @At(
            value  = "INVOKE",
            target = "Lnet/minecraft/world/item/crafting/RecipeManager;getRecipes()Ljava/util/Collection;"
        ),

        method = "placeNewPlayer"
    )
    private Collection<Recipe<?>> neglectSendingSfreezingRecipesToNewPlayer(RecipeManager manager, Operation<Collection<Recipe<?>>> original) {
        return NonSfreezingRecipes(manager);
    }

    @WrapOperation(
        at = @At(
            value  = "INVOKE",
            target = "Lnet/minecraft/world/item/crafting/RecipeManager;getRecipes()Ljava/util/Collection;"
        ),

        method = "reloadResources"
    )
    private Collection<Recipe<?>> neglectSendingSfreezingRecipesOnReload(RecipeManager manager, Operation<Collection<Recipe<?>>> original) {
        return NonSfreezingRecipes(manager);
    }
}
