package io.github.friedkeenan.sfreeze.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.Sfreezable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(PowderSnowBlock.class)
public class TrackSfreezeSource {
    @Inject(at = @At("HEAD"), method = "entityInside")
    private void informSfreezeSource(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo info) {
        if (entity instanceof Sfreezable) {
            ((Sfreezable) entity).setSfreezeSourcePos(pos);
        }
    }
}
