package io.github.friedkeenan.sfreeze.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(CauldronBlock.class)
public abstract class TrackSnowGolemEmptyCauldron extends AbstractCauldronBlock {
    public TrackSnowGolemEmptyCauldron(Properties properties, Map<Item, CauldronInteraction> map) {
        super(properties, map);
    }

    @Override
    public void entityInside(BlockState block, Level level, BlockPos pos, Entity entity) {
        SfreezeMod.TrackSnowShedder(block, level, pos, entity);
    }
}
