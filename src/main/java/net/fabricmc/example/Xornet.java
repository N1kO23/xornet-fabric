package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.item.*;
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

class ZeusEnchantment extends Enchantment {

	public ZeusEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if(target instanceof LivingEntity) {
			LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, target.world);
			lightning.setPos(target.getX(), target.getY(), target.getZ());
			lightning.tick();
		};

		super.onTargetDamaged(user, target, level);
	}
}
public class Xornet implements ModInitializer {

	// Mod namespace
	public static final String MOD_ID = "xornet";

	// Servers

	// Overworld Ore Modifications
	private static ConfiguredFeature<?, ?> ORE_SILICON_OVERWORLD = Feature.ORE
		.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Blocks.SILICON_ORE.getDefaultState(), 9)) // vein size
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 46)))
		.spreadHorizontally()
		.repeat(8); // number of veins per chunk

	// Nether Ore Modifications
	private static ConfiguredFeature<?, ?> ORE_AURA_QUARTZ_NETHER = Feature.ORE
		.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, Blocks.NETHER_AURA_QUARTZ_ORE.getDefaultState(),9))
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0,64)))
		.spreadHorizontally()
		.repeat(4);

	// Instantiate Silicon Tools
	public static final AuraQuartzToolMaterial INSTANCE = 	new AuraQuartzToolMaterial();

	// Creative item group
	public static final ItemGroup XORNET_GROUP = FabricItemGroupBuilder.create(
			new Identifier(MOD_ID, "general"))
				.icon(() -> new ItemStack(Items.AURA_QUARTZ))
				.build();

	@Override
	public void onInitialize() {

		// When the game initializes register our items!
		RegisterItems.register();
		RegisterBlocks.register();

		// ENCHANTMENTS ------------------------------------------------------------------------------------------
		Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "zeus"), new ZeusEnchantment());

		// Register overworld ore biome modifications!
		RegistryKey<ConfiguredFeature<?, ?>> oreSiliconOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MOD_ID, "ore_silicon_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSiliconOverworld.getValue(), ORE_SILICON_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreSiliconOverworld);
		System.out.println("[Xornet] Adding overworld ores");

		/// register nether ore biome modifications!
		RegistryKey<ConfiguredFeature<?, ?>> oreAuraQuartzNether = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(MOD_ID, "ore_aura_quartz_nether"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreAuraQuartzNether.getValue(), ORE_AURA_QUARTZ_NETHER);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, oreAuraQuartzNether);
		System.out.println("[Xornet] Adding nether ores");

		System.out.println("[Xornet] Hello Fabric world!");
	}
}
