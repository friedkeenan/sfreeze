package io.github.friedkeenan.sfreeze.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockBehaviour.class)
public abstract class TrackSnowShedderEmptyCauldron {
    @Inject(at = @At("TAIL"), method = "entityInside")
    public void trackSnowShedder(BlockState block, Level level, BlockPos pos, Entity entity, CallbackInfo info) {
        if ((Object) this instanceof CauldronBlock) {
            SfreezeMod.TrackSnowShedder(block, level, pos, entity);
        }
    }
}
