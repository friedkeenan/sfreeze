package io.github.friedkeenan.sfreeze;

import net.minecraft.core.BlockPos;

public interface Sfreezable {
    public void markCheckedInsideBlocks();
    public void setSfreezeSourcePos(BlockPos pos);
}
