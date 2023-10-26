package io.github.friedkeenan.sfreeze;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;

public interface SnowShedder {
    public @Nullable BlockPos getCauldronPos();
    public void setCauldronPos(@Nullable BlockPos pos);
}
