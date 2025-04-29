package net.dakotapride.garnishedstoneautomation;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.dakotapride.garnishedstoneautomation.extractor.ExtractorPonderScenes;
import net.minecraft.resources.ResourceLocation;

public class ModPonderScenes implements PonderPlugin {

    public static void init(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);
        HELPER.forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/intro", new ExtractorPonderScenes.Intro());
        HELPER.forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/create_stones_processing", new ExtractorPonderScenes.CreateStoneProcessing());
        HELPER.forComponents(ModBlocks.MECHANICAL_EXTRACTOR).addStoryBoard("mechanical_extractor/creating_stone", new ExtractorPonderScenes.CreatingStones());

        HELPER.forComponents(ModItems.ASURINE_CLUSTER, ModItems.CRIMSITE_CLUSTER, ModItems.OCHRUM_CLUSTER, ModItems.VERIDIUM_CLUSTER).addStoryBoard("mechanical_extractor/creating_stone", new ExtractorPonderScenes.CreatingStones());
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        //PonderPlugin.super.registerScenes(helper);
        init(helper);
    }

    @Override
    public String getModId() {
        return GarnishedStoneAutomation.MOD_ID;
    }
}
