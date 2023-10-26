package io.github.friedkeenan.sfreeze.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.Sfreezable;
import io.github.friedkeenan.sfreeze.SnowShedder;
import net.minecraft.world.entity.Entity;

@Mixin(Entity.class)
public class HandleCheckInsideBlocks {
    @Inject(at = @At("HEAD"), method = "checkInsideBlocks")
    private void onCheckInsideBlocks(CallbackInfo info) {
        if (this instanceof Sfreezable) {
            ((Sfreezable) this).markCheckedInsideBlocks();
        }

        if (this instanceof SnowShedder) {
            ((SnowShedder) this).setCauldronPos(null);
        }
    }
}
