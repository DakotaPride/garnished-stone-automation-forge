package net.dakotapride.garnishedstoneautomation;

import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.infrastructure.config.CStress;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.dakotapride.garnishedstoneautomation.extractor.MechanicalExtractorBlock;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import java.util.function.UnaryOperator;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.dakotapride.garnishedstoneautomation.GarnishedStoneAutomation.REGISTRATE;

public class ModBlocks {
    public static final BlockEntry<MechanicalExtractorBlock> MECHANICAL_EXTRACTOR =
            REGISTRATE.block("mechanical_extractor", MechanicalExtractorBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN).noOcclusion().requiresCorrectToolForDrops())
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                    //.transform(CStress.setImpact(8.0D))
                    .item()
                    .transform(customItemModel())
                    // .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .register();

    // Block Tag
    public static final TagKey<Block> HEAT_SOURCES_C;
    public static final TagKey<Block> HEAT_SOURCES_FORGE;

    public static void init() {
        // load the class and register everything
        GarnishedStoneAutomation.LOGGER.info("Registering blocks for " + GarnishedStoneAutomation.NAME);

        BlockStressValues.IMPACTS.registerProvider((block) -> {
            if (block == MECHANICAL_EXTRACTOR.get()) return () -> 8.0D;
            else return null;
        });
    }

    static {
        HEAT_SOURCES_C = commonTag("mechanical_extractor/heat_sources", BuiltInRegistries.BLOCK, false);
        HEAT_SOURCES_FORGE = commonTag("mechanical_extractor/heat_sources", BuiltInRegistries.BLOCK, true);
    }


    private static <T> TagKey<T> commonTag(String name, DefaultedRegistry<T> registry, boolean isForge) {
        if (isForge) {
            return TagKey.create(registry.key(), new ResourceLocation("forge", name));
        } else {
            return TagKey.create(registry.key(), new ResourceLocation("c", name));
        }
    }
}
