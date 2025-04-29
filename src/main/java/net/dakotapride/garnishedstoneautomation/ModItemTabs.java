package net.dakotapride.garnishedstoneautomation;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModItemTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GarnishedStoneAutomation.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GARNISHED_STONE_AUTOMATION = REGISTER.register("create.garnished.stone_automation",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.create.garnished.stone_automation"))
                    .icon(ModItems.ASURINE_CLUSTER::asStack)
                    .displayItems(new GarnishedStoneAutomationDisplayItemsGenerator(true, ModItemTabs.GARNISHED_STONE_AUTOMATION))
                    .build());


    public static class GarnishedStoneAutomationDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

        private final boolean addItems;
        private final DeferredHolder<CreativeModeTab, CreativeModeTab> tabFilter;

        public GarnishedStoneAutomationDisplayItemsGenerator(boolean addItems, DeferredHolder<CreativeModeTab, CreativeModeTab> tabFilter) {
            this.addItems = addItems;
            this.tabFilter = tabFilter;
        }

        @Override
        public void accept(CreativeModeTab.@NotNull ItemDisplayParameters parameters, CreativeModeTab.@NotNull Output output) {
            output.accept(ModBlocks.MECHANICAL_EXTRACTOR.asStack());
            output.accept(ModItems.ASURINE_CLUSTER.asStack());
            output.accept(ModItems.CRIMSITE_CLUSTER.asStack());
            output.accept(ModItems.OCHRUM_CLUSTER.asStack());
            output.accept(ModItems.VERIDIUM_CLUSTER.asStack());
        }
    }

    @ApiStatus.Internal
    public static void init(IEventBus bus) {
        REGISTER.register(bus);
    }
}
