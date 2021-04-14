package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Xornet implements ModInitializer {

	// Mod namespace
	public static final String MOD_ID = "xornet";

	// Create new item with a max stack size of 32
	public static final Item SILICON_CHUNK = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
	public static final Block SILICON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(2.0f));
	private static ConfiguredFeature<?, ?> ORE_SILICON_OVERWORLD = Feature.ORE
	  .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, SILICON_ORE.getDefaultState(), 9)) // vein size
	  .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 46)))
	  .spreadHorizontally()
	  .repeat(8); // number of veins per chunk


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mald caution.

		// When the game initializes register our items!
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_chunk"), SILICON_CHUNK);

		// Silicon ore!
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "silicon_ore"), SILICON_ORE);

		// Make an item for that block!
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_ore"), new BlockItem(SILICON_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		// Register Biome Modifications
		RegistryKey<ConfiguredFeature<?, ?>> oreSiliconOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MOD_ID, "ore_silicon_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSiliconOverworld.getValue(), ORE_SILICON_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreSiliconOverworld);

		System.out.println("Hello Fabric world!");
	}
}
