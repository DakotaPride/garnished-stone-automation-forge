package net.dakotapride.garnishedstoneautomation.extractor;

import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.content.equipment.sandPaper.SandPaperItem;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.foundation.ponder.*;
import com.simibubi.create.foundation.ponder.element.BeltItemElement;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.dakotapride.garnishedstoneautomation.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class ExtractorPonderScenes {
    public static class Intro implements PonderStoryBoardEntry.PonderStoryBoard {

        public Intro() {}

        @Override
        public void program(SceneBuilder scene, SceneBuildingUtil util) {
            scene.title("intro", "Mechanical Extractor Requirements");
            scene.configureBasePlate(0, 0, 5);
            scene.world.showSection(util.select.layer(0), Direction.UP);

            BlockPos magmaBlockPosition = util.grid.at(2, 1, 2);
            BlockPos extractorPosition = util.grid.at(2, 2, 2);
            Selection mechanicalExtractor = util.select.position(extractorPosition);
            Selection magmaBlock = util.select.position(magmaBlockPosition);
            scene.world.showSection(magmaBlock, Direction.DOWN);
            scene.world.showSection(mechanicalExtractor, Direction.DOWN);
            scene.world.setKineticSpeed(mechanicalExtractor, 0);
            scene.idle(5);
            scene.addKeyframe();
            scene.overlay.showText(100)
                    .placeNearTarget()
                    .text("The Mechanical Extractor requires a heat source to be placed underneath in order to function or to pick up any items")
                    .pointAt(util.vector.of(2, 2.5, 2));
            scene.idle(110);
            scene.overlay.showText(50)
                    .attachKeyFrame()
                    .independent(40)
                    .placeNearTarget()
                    .text("Example Heat Sources:");
            scene.idle(10);

            String[] heatSources = new String[] { "Magma Blocks", "Lava", "Lava filled Cauldron" };

            int y = 60;
            for (String s : heatSources) {
                scene.overlay.showText(40)
                        .colored(PonderPalette.MEDIUM)
                        .placeNearTarget()
                        .independent(y)
                        .text(s);
                y += 20;
            }
            scene.idle(55);
            scene.addKeyframe();
            scene.overlay.showText(100)
                    .placeNearTarget()
                    .text("It can only be powered by cogs - similar to the Mechanical Mixer or Mechanical Crafter - and cannot be powered from the bottom... Surprising")
                    .pointAt(util.vector.of(2, 2.5, 2));
            scene.idle(110);

            scene.markAsFinished();
        }
    }

    public static class CreateStoneProcessing implements PonderStoryBoardEntry.PonderStoryBoard {

        public CreateStoneProcessing() {}

        @Override
        public void program(SceneBuilder scene, SceneBuildingUtil util) {
            scene.title("processing", "Processing Items in the Mechanical Extractor");
            scene.configureBasePlate(0, 0, 5);

            Selection belt = util.select.fromTo(1, 1, 5, 0, 1, 2)
                    .add(util.select.position(1, 2, 2));
            Selection beltCog = util.select.position(2, 0, 5);

            scene.world.showSection(util.select.layer(0), Direction.UP);

            BlockPos mechanicalExtractorPosition = util.grid.at(2, 2, 2);
            Selection selectMechanicalExtractor = util.select.position(2, 2, 2);
            Selection cogs = util.select.fromTo(3, 1, 2, 3, 2, 2);
            scene.world.setKineticSpeed(selectMechanicalExtractor, 0);

            scene.idle(5);
            scene.world.showSection(util.select.position(4, 1, 3), Direction.DOWN);
            scene.world.showSection(util.select.position(2, 1, 2), Direction.DOWN);
            scene.idle(5);
            scene.world.showSection(cogs, Direction.DOWN);
            scene.idle(10);
            scene.world.showSection(util.select.position(mechanicalExtractorPosition), Direction.DOWN);
            scene.idle(10);
            scene.world.setKineticSpeed(selectMechanicalExtractor, 32);
            scene.effects.indicateSuccess(mechanicalExtractorPosition);
            Vec3 mechanicalExtractorTop = util.vector.topOf(mechanicalExtractorPosition);
            scene.overlay.showText(60)
                    .attachKeyFrame()
                    .text("Mechanical Extractors can be used to make Vehement Clusters")
                    .pointAt(mechanicalExtractorTop)
                    .placeNearTarget();
            scene.idle(70);

            // scene.world.showSection(cogs, Direction.DOWN);

            ItemStack itemStack = new ItemStack(AllPaletteStoneTypes.ASURINE.getBaseBlock().get());
            Vec3 entitySpawn = util.vector.topOf(mechanicalExtractorPosition.above(3));

            ElementLink<EntityElement> entity1 =
                    scene.world.createItemEntity(entitySpawn, util.vector.of(0, 0.2, 0), itemStack);
            scene.idle(18);
            scene.world.modifyEntity(entity1, Entity::discard);
            scene.world.modifyBlockEntity(mechanicalExtractorPosition, MechanicalExtractorBlockEntity.class,
                    ms -> ms.inputInv.setStackInSlot(0, itemStack));
            scene.idle(10);
            scene.overlay.showControls(new InputWindowElement(mechanicalExtractorTop, Pointing.DOWN).withItem(itemStack), 30);
            scene.idle(7);

            scene.overlay.showText(40)
                    .attachKeyFrame()
                    .text("Throw or Insert items at the top (or at the sides with funnels)")
                    .pointAt(mechanicalExtractorTop)
                    .placeNearTarget();
            scene.idle(60);

            scene.world.modifyBlockEntity(mechanicalExtractorPosition, MechanicalExtractorBlockEntity.class,
                    ms -> ms.inputInv.setStackInSlot(0, ItemStack.EMPTY));

            scene.overlay.showText(50)
                    .text("After some time, the result can be obtained via Right-click")
                    .pointAt(util.vector.blockSurface(mechanicalExtractorPosition, Direction.WEST))
                    .placeNearTarget();
            scene.idle(60);

            ItemStack vehementCluster = ModItems.ASURINE_CLUSTER.asStack();
            scene.overlay.showControls(
                    new InputWindowElement(util.vector.blockSurface(mechanicalExtractorPosition, Direction.NORTH), Pointing.RIGHT).rightClick()
                            .withItem(vehementCluster),
                    40);
            scene.idle(50);

            scene.addKeyframe();
            scene.world.showSection(beltCog, Direction.UP);
            scene.world.showSection(belt, Direction.EAST);
            scene.idle(15);

            BlockPos beltPos = util.grid.at(1, 1, 2);
            scene.world.createItemOnBelt(beltPos, Direction.EAST, vehementCluster);
            scene.idle(15);
            scene.world.createItemOnBelt(beltPos, Direction.EAST, new ItemStack(ModItems.ASURINE_CLUSTER, 2));
            scene.idle(20);

            scene.overlay.showText(50)
                    .text("The outputs can also be extracted by automation")
                    .pointAt(util.vector.blockSurface(mechanicalExtractorPosition, Direction.WEST)
                            .add(-.5, .4, 0))
                    .placeNearTarget();
            scene.idle(60);

            scene.world.hideSection(util.select.layersFrom(1), Direction.UP);
            scene.world.hideSection(util.select.position(util.grid.at(5, 0, 3)), Direction.UP);
            scene.world.hideSection(util.select.position(util.grid.at(2, 0, 5)), Direction.UP);
            scene.idle(40);

            scene.addKeyframe();

            // scene.world.modifyBlock(util.grid.at(2, 1, 2), s);
            ItemStack itemStack1 = new ItemStack(ModItems.ASURINE_CLUSTER);
            Vec3 entitySpawn1 = util.vector.topOf(util.grid.at(2, 1, 1));
            ItemStack itemStack2 = new ItemStack(ModItems.VERIDIUM_CLUSTER);
            Vec3 entitySpawn2 = util.vector.topOf(util.grid.at(3, 0, 3));
            ItemStack itemStack3 = new ItemStack(ModItems.CRIMSITE_CLUSTER);
            Vec3 entitySpawn3 = util.vector.topOf(util.grid.at(4, 0, 2));
            ItemStack itemStack4 = new ItemStack(ModItems.OCHRUM_CLUSTER);
            Vec3 entitySpawn4 = util.vector.topOf(util.grid.at(3, 0, 0));

            ElementLink<EntityElement> entity2 =
                    scene.world.createItemEntity(entitySpawn1, util.vector.of(0, 0.2, 0), itemStack1);
            ElementLink<EntityElement> entity3 =
                    scene.world.createItemEntity(entitySpawn2, util.vector.of(0, 0.2, 0), itemStack2);
            ElementLink<EntityElement> entity4 =
                    scene.world.createItemEntity(entitySpawn3, util.vector.of(0, 0.2, 0), itemStack3);
            ElementLink<EntityElement> entity5 =
                    scene.world.createItemEntity(entitySpawn4, util.vector.of(0, 0.2, 0), itemStack4);
            scene.overlay.showText(200)
                    .text("Vehement Clusters can be used to automate certain stones - such as Asurine or Veridium")
                    .pointAt(util.vector.blockSurface(util.grid.at(2, 1, 1), Direction.WEST)
                            .add(-.5, .4, 0))
                    .placeNearTarget();
            scene.idle(210);
            scene.world.modifyEntity(entity2, Entity::discard);
            scene.world.modifyEntity(entity3, Entity::discard);
            scene.world.modifyEntity(entity4, Entity::discard);
            scene.world.modifyEntity(entity5, Entity::discard);

            scene.markAsFinished();
        }
    }

    public static class CreatingStones implements PonderStoryBoardEntry.PonderStoryBoard {

        public CreatingStones() {}

        @Override
        public void program(SceneBuilder scene, SceneBuildingUtil util) {
            scene.title("processing_stones", "Assembling from Vehement Clusters");
            scene.configureBasePlate(0, 0, 5);
            scene.world.showSection(util.select.layer(0), Direction.UP);
            scene.idle(5);

            Selection deployerSelection = util.select.position(2, 3, 2);
            BlockPos deployerPosition = util.grid.at(2, 3, 2);
            scene.world.setKineticSpeed(deployerSelection, 0);
            scene.world.showSection(deployerSelection, Direction.DOWN);
            scene.idle(10);

            scene.world.showSection(util.select.fromTo(2, 1, 3, 2, 1, 5), Direction.NORTH);
            scene.idle(3);
            scene.world.showSection(util.select.position(2, 2, 3), Direction.SOUTH);
            scene.idle(3);
            scene.world.showSection(util.select.position(2, 3, 3), Direction.NORTH);
            scene.world.setKineticSpeed(deployerSelection, -32);
            scene.effects.indicateSuccess(deployerPosition);
            scene.idle(10);

            ItemStack vehementCluster = ModItems.ASURINE_CLUSTER.asStack();
            scene.overlay.showControls(new InputWindowElement(util.vector.blockSurface(deployerPosition.below(), Direction.EAST)
                    .add(0, 0.15, 0), Pointing.RIGHT).withItem(vehementCluster), 30);
            scene.idle(7);
            scene.world.modifyBlockEntityNBT(deployerSelection, DeployerBlockEntity.class,
                    nbt -> nbt.put("HeldItem", vehementCluster.serializeNBT()));
            scene.idle(25);

            scene.world.showSection(util.select.fromTo(4, 1, 2, 0, 1, 2), Direction.SOUTH);
            scene.idle(20);
            BlockPos beltPos = util.grid.at(0, 1, 2);

            Vec3 targetV = util.vector.centerOf(deployerPosition).subtract(0, 1.65, 0);
            ItemStack stone = Blocks.STONE.asItem().getDefaultInstance();
            ItemStack asurine = AllPaletteStoneTypes.ASURINE.getBaseBlock().get().asItem().getDefaultInstance();

            ElementLink<BeltItemElement> stoneElement1 = scene.world.createItemOnBelt(beltPos, Direction.SOUTH, stone);
            scene.idle(15);
            ElementLink<BeltItemElement> stoneElement2 = scene.world.createItemOnBelt(beltPos, Direction.SOUTH, stone);
            scene.idle(15);
            scene.world.stallBeltItem(stoneElement1, true);
            scene.idle(10);
            scene.overlay.showText(100)
                    .placeNearTarget()
                    .text("Deploying a Vehement Cluster onto a piece of any stone (granite, diorite, stone, etc.) 9 times will provide you with a given stone type from the Create mod")
                    .pointAt(util.vector.of(2, 3, 2));
            scene.idle(120);
            scene.world.moveDeployer(deployerPosition, 1, 30);

            scene.idle(30);
            scene.world.moveDeployer(deployerPosition, -1, 30);
            scene.debug.enqueueCallback(s -> SandPaperItem.spawnParticles(targetV, stone, s.getWorld()));
            scene.world.removeItemsFromBelt(deployerPosition.below(2));
            stoneElement1 = scene.world.createItemOnBelt(deployerPosition.below(2), Direction.UP, asurine);
            scene.world.stallBeltItem(stoneElement1, true);
            scene.idle(15);
            scene.world.stallBeltItem(stoneElement1, false);
            scene.idle(15);
            scene.world.stallBeltItem(stoneElement2, true);
            scene.world.moveDeployer(deployerPosition, 1, 30);
            scene.idle(30);
            scene.world.moveDeployer(deployerPosition, -1, 30);
            scene.debug.enqueueCallback(s -> SandPaperItem.spawnParticles(targetV, stone, s.getWorld()));
            scene.world.removeItemsFromBelt(deployerPosition.below(2));
            stoneElement2 = scene.world.createItemOnBelt(deployerPosition.below(2), Direction.UP, asurine);
            scene.world.stallBeltItem(stoneElement2, true);
            scene.idle(15);
            scene.world.stallBeltItem(stoneElement2, false);
            scene.idle(40);

            scene.markAsFinished();
        }
    }
}