package io.github.friedkeenan.sfreeze;

import java.util.Optional;

import net.minecraft.world.item.Item;

public interface SfreezingResultHolder {
    public Optional<Item> getResult();
    public void setResult(Optional<Item> result);
}
