package net.dakotapride.garnishedstoneautomation;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static net.dakotapride.garnishedstoneautomation.GarnishedStoneAutomation.REGISTRATE;

public class ModItems {
	// Incomplete Processing Items
	public static final ItemEntry<Item> INCOMPLETE_CRIMSITE = REGISTRATE.item("incomplete_crimsite", Item::new).register();
	public static final ItemEntry<Item> INCOMPLETE_VERIDIUM = REGISTRATE.item("incomplete_veridium", Item::new).register();
	public static final ItemEntry<Item> INCOMPLETE_ASURINE = REGISTRATE.item("incomplete_asurine", Item::new).register();
	public static final ItemEntry<Item> INCOMPLETE_OCHRUM = REGISTRATE.item("incomplete_ochrum", Item::new).register();

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
