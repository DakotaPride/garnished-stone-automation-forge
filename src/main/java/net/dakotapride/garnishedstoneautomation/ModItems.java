package net.dakotapride.garnishedstoneautomation;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static net.dakotapride.garnishedstoneautomation.GarnishedStoneAutomation.REGISTRATE;

public class ModItems {
	// Incomplete Processing Items
	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_CRIMSITE = REGISTRATE.item("incomplete_crimsite", SequencedAssemblyItem::new).register();
	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_VERIDIUM = REGISTRATE.item("incomplete_veridium", SequencedAssemblyItem::new).register();
	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_ASURINE = REGISTRATE.item("incomplete_asurine", SequencedAssemblyItem::new).register();
	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_OCHRUM = REGISTRATE.item("incomplete_ochrum", SequencedAssemblyItem::new).register();

	// Vehement Clusters
	public static final ItemEntry<Item> CRIMSITE_CLUSTER = REGISTRATE.item("crimsite_cluster", Item::new).register();
	public static final ItemEntry<Item> VERIDIUM_CLUSTER = REGISTRATE.item("veridium_cluster", Item::new).register();
	public static final ItemEntry<Item> ASURINE_CLUSTER = REGISTRATE.item("asurine_cluster", Item::new).register();
	public static final ItemEntry<Item> OCHRUM_CLUSTER = REGISTRATE.item("ochrum_cluster", Item::new).register();

	public static void init() {
		// load the class and register everything
		GarnishedStoneAutomation.LOGGER.info("Registering items for " + GarnishedStoneAutomation.NAME);
	}
}
