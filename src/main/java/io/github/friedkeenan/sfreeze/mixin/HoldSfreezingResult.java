package io.github.friedkeenan.sfreeze.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;

import io.github.friedkeenan.sfreeze.SfreezingResultHolder;
import net.minecraft.world.item.Item;

@Mixin(Item.class)
public class HoldSfreezingResult implements SfreezingResultHolder {
    private Optional<Item> sfreeze_result;

    @Override
    public Optional<Item> getResult() {
        return this.sfreeze_result;
    }

    @Override
    public void setResult(Optional<Item> result) {
        this.sfreeze_result = result;
    }
}
