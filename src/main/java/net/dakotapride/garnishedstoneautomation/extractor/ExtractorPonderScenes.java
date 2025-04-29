package net.dakotapride.garnishedstoneautomation.extractor;

import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.content.equipment.sandPaper.SandPaperItem;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import com.simibubi.create.foundation.ponder.element.BeltItemElement;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.dakotapride.garnishedstoneautomation.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class ExtractorPonderScenes {
    public static class Intro implements PonderStoryBoard {

        public Intro() {}

        @Override
        public void program(SceneBuilder builder, SceneBuildingUtil util) {
            CreateSceneBuilder scene = new CreateSceneBuilder(builder);

            builder.title("intro", "Mechanical Extractor Requirements");
            builder.configureBasePlate(0, 0, 5);
            builder.world().showSection(util.select().layer(0), Direction.UP);

            BlockPos magmaBlockPosition = util.grid().at(2, 1, 2);
            BlockPos extractorPosition = util.grid().at(2, 2, 2);
            Selection mechanicalExtractor = util.select().position(extractorPosition);
            Selection magmaBlock = util.select().position(magmaBlockPosition);
            builder.world().showSection(magmaBlock, Direction.DOWN);
            builder.world().showSection(mechanicalExtractor, Direction.DOWN);
            scene.world().setKineticSpeed(mechanicalExtractor, 0);
            builder.idle(5);
            builder.addKeyframe();
            builder.overlay().showText(100)
                    .placeNearTarget()
                    .text("The Mechanical Extractor requires a heat source to be placed underneath in order to function or to pick up any items")
                    .pointAt(util.vector().of(2, 2.5, 2));
            builder.idle(110);
            builder.overlay().showText(50)
                    .attachKeyFrame()
                    .independent(40)
                    .placeNearTarget()
                    .text("Example Heat Sources:");
            builder.idle(10);

            String[] heatSources = new String[] { "Magma Blocks", "Lava", "Lava filled Cauldron" };

            int y = 60;
            for (String s : heatSources) {
                builder.overlay().showText(40)
                        .colored(PonderPalette.MEDIUM)
                        .placeNearTarget()
                        .independent(y)
                        .text(s);
                y += 20;
            }
            builder.idle(55);
            builder.addKeyframe();
            builder.overlay().showText(100)
                    .placeNearTarget()
                    .text("It can only be powered by cogs - similar to the Mechanical Mixer or Mechanical Crafter - and cannot be powered from the bottom... Surprising")
                    .pointAt(util.vector().of(2, 2.5, 2));
            builder.idle(110);

            builder.markAsFinished();
        }
    }

    public static class CreateStoneProcessing implements PonderStoryBoard {

        public CreateStoneProcessing() {}

        @Override
        public void program(SceneBuilder builder, SceneBuildingUtil util) {
            CreateSceneBuilder scene = new CreateSceneBuilder(builder);

            builder.title("processing", "Processing Items in the Mechanical Extractor");
            builder.configureBasePlate(0, 0, 5);

            Selection belt = util.select().fromTo(1, 1, 5, 0, 1, 2)
                    .add(util.select().position(1, 2, 2));
            Selection beltCog = util.select().position(2, 0, 5);

            builder.world().showSection(util.select().layer(0), Direction.UP);

            BlockPos mechanicalExtractorPosition = util.grid().at(2, 2, 2);
            Selection selectMechanicalExtractor = util.select().position(2, 2, 2);
            Selection cogs = util.select().fromTo(3, 1, 2, 3, 2, 2);
            scene.world().setKineticSpeed(selectMechanicalExtractor, 0);

            builder.idle(5);
            builder.world().showSection(util.select().position(4, 1, 3), Direction.DOWN);
            builder.world().showSection(util.select().position(2, 1, 2), Direction.DOWN);
            builder.idle(5);
            builder.world().showSection(cogs, Direction.DOWN);
            builder.idle(10);
            builder.world().showSection(util.select().position(mechanicalExtractorPosition), Direction.DOWN);
            builder.idle(10);
            scene.world().setKineticSpeed(selectMechanicalExtractor, 32);
            builder.effects().indicateSuccess(mechanicalExtractorPosition);
            Vec3 mechanicalExtractorTop = util.vector().topOf(mechanicalExtractorPosition);
            builder.overlay().showText(60)
                    .attachKeyFrame()
                    .text("Mechanical Extractors can be used to make Vehement Clusters")
                    .pointAt(mechanicalExtractorTop)
                    .placeNearTarget();
            builder.idle(70);

            // scene.world().showSection(cogs, Direction.DOWN);

            ItemStack itemStack = new ItemStack(AllPaletteStoneTypes.ASURINE.getBaseBlock().get());
            Vec3 entitySpawn = util.vector().topOf(mechanicalExtractorPosition.above(3));

            ElementLink<EntityElement> entity1 =
                    builder.world().createItemEntity(entitySpawn, util.vector().of(0, 0.2, 0), itemStack);
            builder.idle(18);
            builder.world().modifyEntity(entity1, Entity::discard);
            builder.world().modifyBlockEntity(mechanicalExtractorPosition, MechanicalExtractorBlockEntity.class,
                    ms -> ms.inputInv.setStackInSlot(0, itemStack));
            builder.idle(10);
            builder.overlay().showControls(mechanicalExtractorTop, Pointing.DOWN, 30).withItem(itemStack);
            builder.idle(7);

            builder.overlay().showText(40)
                    .attachKeyFrame()
                    .text("Throw or Insert items at the top (or at the sides with funnels)")
                    .pointAt(mechanicalExtractorTop)
                    .placeNearTarget();
            builder.idle(60);

            builder.world().modifyBlockEntity(mechanicalExtractorPosition, MechanicalExtractorBlockEntity.class,
                    ms -> ms.inputInv.setStackInSlot(0, ItemStack.EMPTY));

            builder.overlay().showText(50)
                    .text("After some time, the result can be obtained via Right-click")
                    .pointAt(util.vector().blockSurface(mechanicalExtractorPosition, Direction.WEST))
                    .placeNearTarget();
            builder.idle(60);

            ItemStack vehementCluster = ModItems.ASURINE_CLUSTER.asStack();
            builder.overlay().showControls(
                    util.vector().blockSurface(mechanicalExtractorPosition, Direction.NORTH), Pointing.RIGHT, 40).rightClick().withItem(vehementCluster);
            builder.idle(50);

            builder.addKeyframe();
            builder.world().showSection(beltCog, Direction.UP);
            builder.world().showSection(belt, Direction.EAST);
            builder.idle(15);

            BlockPos beltPos = util.grid().at(1, 1, 2);
            scene.world().createItemOnBelt(beltPos, Direction.EAST, vehementCluster);
            builder.idle(15);
            scene.world().createItemOnBelt(beltPos, Direction.EAST, new ItemStack(ModItems.ASURINE_CLUSTER.get(), 2));
            builder.idle(20);

            builder.overlay().showText(50)
                    .text("The outputs can also be extracted by automation")
                    .pointAt(util.vector().blockSurface(mechanicalExtractorPosition, Direction.WEST)
                            .add(-.5, .4, 0))
                    .placeNearTarget();
            builder.idle(60);

            builder.world().hideSection(util.select().layersFrom(1), Direction.UP);
            builder.world().hideSection(util.select().position(util.grid().at(5, 0, 3)), Direction.UP);
            builder.world().hideSection(util.select().position(util.grid().at(2, 0, 5)), Direction.UP);
            builder.idle(40);

            builder.addKeyframe();

            // scene.world().modifyBlock(util.grid().at(2, 1, 2), s);
            ItemStack itemStack1 = new ItemStack(ModItems.ASURINE_CLUSTER.get());
            Vec3 entitySpawn1 = util.vector().topOf(util.grid().at(2, 1, 1));
            ItemStack itemStack2 = new ItemStack(ModItems.VERIDIUM_CLUSTER.get());
            Vec3 entitySpawn2 = util.vector().topOf(util.grid().at(3, 0, 3));
            ItemStack itemStack3 = new ItemStack(ModItems.CRIMSITE_CLUSTER.get());
            Vec3 entitySpawn3 = util.vector().topOf(util.grid().at(4, 0, 2));
            ItemStack itemStack4 = new ItemStack(ModItems.OCHRUM_CLUSTER.get());
            Vec3 entitySpawn4 = util.vector().topOf(util.grid().at(3, 0, 0));

            ElementLink<EntityElement> entity2 =
                    builder.world().createItemEntity(entitySpawn1, util.vector().of(0, 0.2, 0), itemStack1);
            ElementLink<EntityElement> entity3 =
                    builder.world().createItemEntity(entitySpawn2, util.vector().of(0, 0.2, 0), itemStack2);
            ElementLink<EntityElement> entity4 =
                    builder.world().createItemEntity(entitySpawn3, util.vector().of(0, 0.2, 0), itemStack3);
            ElementLink<EntityElement> entity5 =
                    builder.world().createItemEntity(entitySpawn4, util.vector().of(0, 0.2, 0), itemStack4);
            builder.overlay().showText(200)
                    .text("Vehement Clusters can be used to automate certain stones - such as Asurine or Veridium")
                    .pointAt(util.vector().blockSurface(util.grid().at(2, 1, 1), Direction.WEST)
                            .add(-.5, .4, 0))
                    .placeNearTarget();
            builder.idle(210);
            builder.world().modifyEntity(entity2, Entity::discard);
            builder.world().modifyEntity(entity3, Entity::discard);
            builder.world().modifyEntity(entity4, Entity::discard);
            builder.world().modifyEntity(entity5, Entity::discard);

            builder.markAsFinished();
        }
    }

    public static class CreatingStones implements PonderStoryBoard {

        public CreatingStones() {}

        @Override
        public void program(SceneBuilder builder, SceneBuildingUtil util) {
            CreateSceneBuilder scene = new CreateSceneBuilder(builder);

            builder.title("processing_stones", "Assembling from Vehement Clusters");
            builder.configureBasePlate(0, 0, 5);
            builder.world().showSection(util.select().layer(0), Direction.UP);
            builder.idle(5);

            Selection deployerSelection = util.select().position(2, 3, 2);
            BlockPos deployerPosition = util.grid().at(2, 3, 2);
            scene.world().setKineticSpeed(deployerSelection, 0);
            builder.world().showSection(deployerSelection, Direction.DOWN);
            builder.idle(10);

            builder.world().showSection(util.select().fromTo(2, 1, 3, 2, 1, 5), Direction.NORTH);
            builder.idle(3);
            builder.world().showSection(util.select().position(2, 2, 3), Direction.SOUTH);
            builder.idle(3);
            builder.world().showSection(util.select().position(2, 3, 3), Direction.NORTH);
            scene.world().setKineticSpeed(deployerSelection, -32);
            builder.effects().indicateSuccess(deployerPosition);
            builder.idle(10);

            ItemStack vehementCluster = ModItems.ASURINE_CLUSTER.asStack();
            builder.overlay().showControls(util.vector().blockSurface(deployerPosition.below(), Direction.EAST).add(0, 0.15, 0), Pointing.RIGHT, 30).withItem(vehementCluster);
            builder.idle(7);
            builder.world().modifyBlockEntityNBT(deployerSelection, DeployerBlockEntity.class,
                    nbt -> nbt.put("HeldItem", vehementCluster.saveOptional(scene.world().getHolderLookupProvider())));
            builder.idle(25);

            builder.world().showSection(util.select().fromTo(4, 1, 2, 0, 1, 2), Direction.SOUTH);
            builder.idle(20);
            BlockPos beltPos = util.grid().at(0, 1, 2);

            Vec3 targetV = util.vector().centerOf(deployerPosition).subtract(0, 1.65, 0);
            ItemStack stone = Blocks.STONE.asItem().getDefaultInstance();
            ItemStack asurine = AllPaletteStoneTypes.ASURINE.getBaseBlock().get().asItem().getDefaultInstance();

            ElementLink<BeltItemElement> stoneElement1 = scene.world().createItemOnBelt(beltPos, Direction.SOUTH, stone);
            builder.idle(15);
            ElementLink<BeltItemElement> stoneElement2 = scene.world().createItemOnBelt(beltPos, Direction.SOUTH, stone);
            builder.idle(15);
            scene.world().stallBeltItem(stoneElement1, true);
            builder.idle(10);
            builder.overlay().showText(100)
                    .placeNearTarget()
                    .text("Deploying a Vehement Cluster onto a piece of any stone (granite, diorite, stone, etc.) 9 times will provide you with a given stone type from the Create mod")
                    .pointAt(util.vector().of(2, 3, 2));
            builder.idle(120);
            scene.world().moveDeployer(deployerPosition, 1, 30);

            builder.idle(30);
            scene.world().moveDeployer(deployerPosition, -1, 30);
            builder.debug().enqueueCallback(s -> SandPaperItem.spawnParticles(targetV, stone, s.getWorld()));
            scene.world().removeItemsFromBelt(deployerPosition.below(2));
            stoneElement1 = scene.world().createItemOnBelt(deployerPosition.below(2), Direction.UP, asurine);
            scene.world().stallBeltItem(stoneElement1, true);
            builder.idle(15);
            scene.world().stallBeltItem(stoneElement1, false);
            builder.idle(15);
            scene.world().stallBeltItem(stoneElement2, true);
            scene.world().moveDeployer(deployerPosition, 1, 30);
            builder.idle(30);
            scene.world().moveDeployer(deployerPosition, -1, 30);
            builder.debug().enqueueCallback(s -> SandPaperItem.spawnParticles(targetV, stone, s.getWorld()));
            scene.world().removeItemsFromBelt(deployerPosition.below(2));
            stoneElement2 = scene.world().createItemOnBelt(deployerPosition.below(2), Direction.UP, asurine);
            scene.world().stallBeltItem(stoneElement2, true);
            builder.idle(15);
            scene.world().stallBeltItem(stoneElement2, false);
            builder.idle(40);

            builder.markAsFinished();
        }
    }
}