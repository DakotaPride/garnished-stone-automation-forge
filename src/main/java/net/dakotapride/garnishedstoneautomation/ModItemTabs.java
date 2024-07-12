package net.dakotapride.garnishedstoneautomation;

import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemTabs {
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GarnishedStoneAutomation.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GARNISHED_STONE_AUTOMATION = TAB_REGISTER.register("create.garnished.stone_automation",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.create.garnished.stone_automation"))
                    .icon(() -> ModItems.ASURINE_CLUSTER.get().getDefaultInstance())
                    .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
                    .displayItems(new GarnishedStoneAutomationDisplayItemsGenerator()).build());

    public static class GarnishedStoneAutomationDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {
        public GarnishedStoneAutomationDisplayItemsGenerator() {
        }

        @Override
        public void accept(CreativeModeTab.@NotNull ItemDisplayParameters params, CreativeModeTab.@NotNull Output output) {
            output.accept(ModBlocks.MECHANICAL_EXTRACTOR.asStack());
            output.accept(ModItems.ASURINE_CLUSTER.asStack());
            output.accept(ModItems.CRIMSITE_CLUSTER.asStack());
            output.accept(ModItems.OCHRUM_CLUSTER.asStack());
            output.accept(ModItems.VERIDIUM_CLUSTER.asStack());
        }
    }

    public static void init(IEventBus bus) {
        TAB_REGISTER.register(bus);
    }
}
