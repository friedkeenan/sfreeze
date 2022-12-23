package io.github.friedkeenan.sfreeze;

import java.util.Collection;
import java.util.Optional;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public interface SfreezeRecipeCacher {
    public Optional<Item> findResult(Item item);

    default public Optional<Item> findResult(ItemStack item) {
        return this.findResult(item.getItem());
    }

    public Collection<Recipe<?>> getNonSfreezingRecipes();
}
