package io.github.friedkeenan.sfreeze;

import java.util.Optional;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface SfreezeRecipeCacher {
    public Optional<Item> findResult(Item item);

    default public Optional<Item> findResult(ItemStack item) {
        return this.findResult(item.getItem());
    }
}
