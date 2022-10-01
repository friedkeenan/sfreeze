package io.github.friedkeenan.sfreeze.mixin;

import java.util.Map;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;

import io.github.friedkeenan.sfreeze.SfreezeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(PowderSnowCauldronBlock.class)
public abstract class TrackSnowGolemPowderSnowCauldron extends LayeredCauldronBlock {
    public TrackSnowGolemPowderSnowCauldron(BlockBehaviour.Properties properties, Predicate<Biome.Precipitation> predicate, Map<Item, CauldronInteraction> map) {
        super(properties, predicate, map);
    }

    @Override
    public void entityInside(BlockState block, Level level, BlockPos pos, Entity entity) {
        super.entityInside(block, level, pos, entity);

        SfreezeMod.TrackSnowShedder(block, level, pos, entity);
    }
}
