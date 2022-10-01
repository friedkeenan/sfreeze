package io.github.friedkeenan.sfreeze.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import io.github.friedkeenan.sfreeze.SnowShedder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

@Mixin(SnowGolem.class)
public abstract class AddSnowSheddingToSnowGolem extends AbstractGolem implements SnowShedder{
    @Nullable
    private BlockPos cauldron_pos = null;

    protected AddSnowSheddingToSnowGolem(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setCauldronPos(BlockPos pos) {
        this.cauldron_pos = pos;
    }

    @Override
    public void checkInsideBlocks() {
        this.cauldron_pos = null;

        super.checkInsideBlocks();
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (!super.hurt(source, damage)) {
            return false;
        }

        if (level.isClientSide) {
            return true;
        }

        if (this.cauldron_pos != null && this.random.nextInt(3) == 0 && this.mayInteract(this.level, this.cauldron_pos)) {
            final var inside_block = this.level.getBlockState(this.cauldron_pos);

            final BlockState new_state;
            if (inside_block.is(Blocks.CAULDRON)) {
                new_state = Blocks.POWDER_SNOW_CAULDRON.defaultBlockState();
            } else {
                new_state = inside_block.cycle(LayeredCauldronBlock.LEVEL);
            }

            this.level.setBlockAndUpdate(this.cauldron_pos, new_state);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.cauldron_pos, GameEvent.Context.of(new_state));
        }

        return true;
    }
}
