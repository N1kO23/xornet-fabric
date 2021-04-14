package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Xornet implements ModInitializer {

	// Mod namespace
	public static final String MOD_ID = "xornet";

	// Create new item with a max stack size of 32
	public static final Item FABRIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(32));
	public static final Block SILICON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(2.0f));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// When the game initializes register our items!
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fabric_item"), FABRIC_ITEM);

		// Silicon ore!
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "silicon_ore"), SILICON_ORE);

		// Make an item for that block!
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_ore"), new BlockItem(SILICON_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		System.out.println("Hello Fabric world!");
	}
}