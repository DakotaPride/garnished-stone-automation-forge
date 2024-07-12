package net.dakotapride.garnishedstoneautomation.extractor;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalExtractorRenderer extends KineticBlockEntityRenderer<MechanicalExtractorBlockEntity> {

    public MechanicalExtractorRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(MechanicalExtractorBlockEntity be, BlockState state) {
        return CachedBufferer.partial(AllPartialModels.SHAFTLESS_COGWHEEL, state);
    }

}