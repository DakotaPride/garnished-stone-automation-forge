package net.dakotapride.garnishedstoneautomation;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;

public class GarnishedStoneAutomationProcessingSerializer<T extends ProcessingRecipe<?>> extends ProcessingRecipeSerializer<T> {

    public final MapCodec<T> CODEC = ModRecipeTypes.CODEC.dispatchMap(t ->
            (ModRecipeTypes) t.getTypeInfo(), ModRecipeTypes::processingCodec);


    public GarnishedStoneAutomationProcessingSerializer(ProcessingRecipeBuilder.ProcessingRecipeFactory<T> factory) {
        super(factory);
    }


    @Override
    public MapCodec<T> codec() {
        return CODEC;
    }
}
