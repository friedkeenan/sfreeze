package io.github.friedkeenan.sfreeze.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.friedkeenan.sfreeze.SnowShedder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

@Mixin(LivingEntity.class)
abstract public class ShedSnowIntoCauldron extends Entity {
    public ShedSnowIntoCauldron(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("TAIL"), method = "hurt")
    private void shedOnDamaged(DamageSource source, float damage, CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValueZ()) {
            return;
        }

        if (!(this instanceof SnowShedder)) {
            return;
        }

        if (this.level.isClientSide) {
            return;
        }

        final var shedder = (SnowShedder) this;

        final @Nullable var cauldron_pos = shedder.getCauldronPos();
        if (cauldron_pos == null) {
            return;
        }

        if (this.random.nextInt(3) == 0 && this.mayInteract(this.level, cauldron_pos)) {
            final var inside_block = this.level.getBlockState(cauldron_pos);

            final BlockState new_state;
            if (inside_block.is(Blocks.CAULDRON)) {
                new_state = Blocks.POWDER_SNOW_CAULDRON.defaultBlockState();
            } else {
                new_state = inside_block.cycle(LayeredCauldronBlock.LEVEL);
            }

            this.level.setBlockAndUpdate(cauldron_pos, new_state);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, cauldron_pos, GameEvent.Context.of(new_state));
        }
    }
}
