package io.github.friedkeenan.sfreeze.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;

@Environment(EnvType.CLIENT)
@Mixin(ClientRecipeBook.class)
public class RemoveSfreezeRecipeWarning {
    @Inject(at = @At("HEAD"), method = "getCategory", cancellable = true)
    private static void interceptSfreezeRecipe(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> info) {
        if (recipe.getType() == SfreezeMod.SFREEZING) {
            info.setReturnValue(RecipeBookCategories.UNKNOWN);
            return;
        }
    }
}
