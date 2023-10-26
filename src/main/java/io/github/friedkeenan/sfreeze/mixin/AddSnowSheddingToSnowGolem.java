package io.github.friedkeenan.sfreeze.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import io.github.friedkeenan.sfreeze.SnowShedder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.SnowGolem;

@Mixin(SnowGolem.class)
public class AddSnowSheddingToSnowGolem implements SnowShedder {
    @Nullable
    private BlockPos cauldron_pos = null;

    @Override
    @Nullable
    public BlockPos getCauldronPos() {
        return this.cauldron_pos;
    }

    @Override
    public void setCauldronPos(@Nullable BlockPos pos) {
        this.cauldron_pos = pos;
    }
}
