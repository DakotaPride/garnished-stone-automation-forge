package net.dakotapride.garnishedstoneautomation;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.dakotapride.garnishedstoneautomation.extractor.ExtractorPonderScenes;

public class ModPonderScenes {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper("garnishedstoneautomation");

    public static PonderRegistrationHelper.MultiSceneBuilder forComponents(ItemProviderEntry<?>... entries) {
        return HELPER.forComponents(entries);
    }

    public static void init() {
        forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/intro", new ExtractorPonderScenes.Intro());
        forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/create_stones_processing", new ExtractorPonderScenes.CreateStoneProcessing());
        forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/creating_stone", new ExtractorPonderScenes.CreatingStones());

        forComponents(ModItems.ASURINE_CLUSTER, ModItems.CRIMSITE_CLUSTER, ModItems.OCHRUM_CLUSTER, ModItems.VERIDIUM_CLUSTER).addStoryBoard("mechanical_extractor/creating_stone", new ExtractorPonderScenes.CreatingStones());
    }

}
