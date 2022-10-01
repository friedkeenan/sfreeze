package io.github.friedkeenan.sfreeze;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SfreezeMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("sfreeze");

    public static final RecipeType<SfreezingRecipe> SFREEZING = new RecipeType<SfreezingRecipe>() {
        @Override
        public String toString() {
            return "sfreeze:sfreezing";
        }
    };

    public static final SfreezingRecipe.Serializer SFREEZING_SERIALIZER = new SfreezingRecipe.Serializer();

    public static final SoundEvent SFREEZE_SOUND = new SoundEvent(new ResourceLocation("sfreeze:entity.item.sfreeze"));

    public static boolean IsEntityInsideCauldron(Entity entity, BlockPos pos) {
        return entity.getY() < ((double) pos.getY()) + 1.0 && entity.getBoundingBox().maxY > ((double) pos.getY()) + 0.25;
    }

    public static void TrackSnowShedder(BlockState block, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof SnowShedder)) {
            return;
        }

        if (level.isClientSide) {
            return;
        }

        if (block.is(Blocks.POWDER_SNOW_CAULDRON) && block.getValue(LayeredCauldronBlock.LEVEL) >= LayeredCauldronBlock.MAX_FILL_LEVEL) {
            return;
        }

        if (!IsEntityInsideCauldron(entity, pos)) {
            return;
        }

        /* We need to make a copy of the position as it is a 'MutableBlockPos' and is later changed. */
        ((SnowShedder) entity).setCauldronPos(new BlockPos(pos));
    }

    public void onInitialize() {
        Registry.register(Registry.RECIPE_TYPE, "sfreeze:sfreezing", SFREEZING);
        Registry.register(Registry.RECIPE_SERIALIZER, "sfreeze:sfreezing", SFREEZING_SERIALIZER);

        Registry.register(Registry.SOUND_EVENT, SFREEZE_SOUND.getLocation(), SFREEZE_SOUND);

        LOGGER.info("sfreeze initialized!");
    }
}
