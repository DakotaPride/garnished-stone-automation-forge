package net.dakotapride.garnishedstoneautomation;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.dakotapride.garnishedstoneautomation.extractor.MechanicalExtractorBlockEntity;
import net.dakotapride.garnishedstoneautomation.extractor.MechanicalExtractorRenderer;
import net.dakotapride.garnishedstoneautomation.extractor.MechanicalExtractorVisual;

import static net.dakotapride.garnishedstoneautomation.GarnishedStoneAutomation.REGISTRATE;

public class ModBlockEntityTypes {

    public static final BlockEntityEntry<MechanicalExtractorBlockEntity> EXTRACTOR = REGISTRATE
            .blockEntity("mechanical_extractor", MechanicalExtractorBlockEntity::new)
            //.instance(() -> MechanicalExtractorCogInstance::new, false)
            .visual(() -> MechanicalExtractorVisual::new)
            .validBlocks(ModBlocks.MECHANICAL_EXTRACTOR)
            .renderer(() -> MechanicalExtractorRenderer::new)
            .register();

    public static void init() {}
}
