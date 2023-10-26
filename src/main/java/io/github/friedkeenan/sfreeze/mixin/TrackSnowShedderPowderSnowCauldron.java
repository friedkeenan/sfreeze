package io.github.friedkeenan.sfreeze.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(LayeredCauldronBlock.class)
public abstract class TrackSnowShedderPowderSnowCauldron {
    @Inject(at = @At("TAIL"), method = "entityInside")
    private void trackSnowShedder(BlockState block, Level level, BlockPos pos, Entity entity, CallbackInfo info) {
        if ((Object) this instanceof PowderSnowCauldronBlock) {
            SfreezeMod.TrackSnowShedder(block, level, pos, entity);
        }
    }
}
