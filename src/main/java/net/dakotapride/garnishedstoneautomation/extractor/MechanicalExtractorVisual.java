package net.dakotapride.garnishedstoneautomation.extractor;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;

public class MechanicalExtractorVisual extends SingleAxisRotatingVisual<MechanicalExtractorBlockEntity> implements SimpleDynamicVisual {

    private final MechanicalExtractorBlockEntity extractor;

    public MechanicalExtractorVisual(VisualizationContext context, MechanicalExtractorBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL));
        this.extractor = blockEntity;

        animate(partialTick);
    }

    @Override
    public void beginFrame(DynamicVisual.Context ctx) {
        animate(ctx.partialTick());
    }

    private void animate(float pt) {}
}
