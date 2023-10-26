package io.github.friedkeenan.sfreeze.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.friedkeenan.sfreeze.SfreezingResultHolder;
import io.github.friedkeenan.sfreeze.Sfreezable;
import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(ItemEntity.class)
public abstract class MakeItemsFreeze extends Entity implements Sfreezable {
    private static final int AVERAGE_ITEMS_BEFORE_POWDER_SNOW_POOFS = Item.MAX_STACK_SIZE;

    private boolean checked_inside_blocks = false;
    private BlockPos sfreeze_source;

    private MakeItemsFreeze(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void markCheckedInsideBlocks() {
        this.checked_inside_blocks = true;
    }

    @Shadow
    protected abstract ItemStack getItem();

    private Optional<Item> sfreezeResult() {
        return ((SfreezingResultHolder) this.getItem().getItem()).getResult();
    }

    @Override
    public void setSfreezeSourcePos(BlockPos pos) {
        this.sfreeze_source = pos;
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void doFreezing(CallbackInfo info) {
        if (this.isRemoved()) {
            return;
        }

        if (this.level().isClientSide()) {
            return;
        }

        final var maybe_sfreeze_result = this.sfreezeResult();
        if (maybe_sfreeze_result.isEmpty()) {
            return;
        }

        final var sfreeze_result = maybe_sfreeze_result.get();

        /*
            The 'isInPowderSnow' field gets set by 'PowderSnowBlock::onEntityInside',
            but that only gets called when 'Entity::move' is called, which is not
            every tick for items. Therefore we call 'tryCheckInsideBlocks' manually
            if it has not already been called.
        */
        if (!this.checked_inside_blocks) {
            this.tryCheckInsideBlocks();
        }

        this.checked_inside_blocks = false;

        /* This is the same logic that 'LivingEntity' uses. */
        final var frozen_time = this.getTicksFrozen();
        if (this.isInPowderSnow && this.canFreeze()) {
            this.setTicksFrozen(Math.min(frozen_time + 1, this.getTicksRequiredToFreeze()));
        } else {
            this.setTicksFrozen(Math.max(frozen_time - 2, 0));
        }

        if (frozen_time >= this.getTicksRequiredToFreeze()) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SfreezeMod.SFREEZE_SOUND, SoundSource.NEUTRAL, 1.0f, 1.0f);

            var count = this.getItem().getCount();

            if (this.random.nextInt(AVERAGE_ITEMS_BEFORE_POWDER_SNOW_POOFS) < count) {
                this.level().destroyBlock(this.sfreeze_source, false);
                this.level().playSound(null, this.sfreeze_source, SoundEvents.POWDER_SNOW_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
            }

            while (count > 0) {
                final var result_count = Math.min(sfreeze_result.getMaxStackSize(), count);
                final var result_entity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), new ItemStack(sfreeze_result, result_count));
                this.level().addFreshEntity(result_entity);

                count -= result_count;
            }

            this.discard();
        }
    }
}
