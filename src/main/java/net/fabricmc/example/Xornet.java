package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
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

class Server4UBlock extends Block {
	public Server4UBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		float reduceVoxelSize = (1f/16f);
		return VoxelShapes.cuboid(reduceVoxelSize, 0f, reduceVoxelSize, 1f - reduceVoxelSize, 0.5f, 1f - reduceVoxelSize);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING);
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
	}
}

public class Xornet implements ModInitializer {

	// Mod namespace
	public static final String MOD_ID = "xornet";

	// Create new item with a max stack size of 32
	public static final Item SILICON_CHUNK = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
	public static final Item SILICON_CRYSTAL = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
	public static final Item AURA_QUARTZ = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));

	// Create CPU items
	public static final Item BASTEL_PROCESSOR_L1 = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(1));
	public static final Item RAZOR_PROCESSOR_R1 = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(1));
	public static final Item MEMORY_1VB = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16));
	public static final Item MEMORY_4VB = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16));
	public static final Item MEMORY_16VB = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16));

	// Servers
	public static final Block SERVER_4U = new Server4UBlock(FabricBlockSettings.of(Material.METAL).strength(3.0f));
	public static final BlockItem SERVER_4U_BLOCKITEM = new BlockItem(SERVER_4U, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
	
	// Overworld Ores
	public static final Block SILICON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final BlockItem SILICON_ORE_BLOCKITEM = new BlockItem(SILICON_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

	// Nether Ores
	public static final Block NETHER_AURA_QUARTZ_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
	public static final BlockItem NETHER_AURA_QUARTZ_ORE_BLOCKITEM = new BlockItem(NETHER_AURA_QUARTZ_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

	// Overworld Ore Modifications
	private static ConfiguredFeature<?, ?> ORE_SILICON_OVERWORLD = Feature.ORE
		.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, SILICON_ORE.getDefaultState(), 9)) // vein size
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 46)))
		.spreadHorizontally()
		.repeat(8); // number of veins per chunk

	// Nether Ore Modifications
	private static ConfiguredFeature<?, ?> ORE_AURA_QUARTZ_NETHER = Feature.ORE
		.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, NETHER_AURA_QUARTZ_ORE.getDefaultState(),9))
		.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0,64)))
		.spreadHorizontally()
		.repeat(4);

	// Instantiate Silicon Tools
	public static final SiliconToolMaterial INSTANCE = 	new SiliconToolMaterial();
	public static ToolItem SILICON_PICKAXE = 			new CustomPickaxeItem(SiliconToolMaterial.INSTANCE, 4, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static ToolItem SILICON_SWORD =  			new CustomSwordItem(SiliconToolMaterial.INSTANCE, 	8, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static ToolItem SILICON_AXE =  	    		new CustomAxeItem(SiliconToolMaterial.INSTANCE, 	9, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
	public static ToolItem SILICON_SHOVEL = 			new CustomShovelItem(SiliconToolMaterial.INSTANCE,  3, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));

	// Creative item group
	public static final ItemGroup XORNET_ITEM_GROUP = FabricItemGroupBuilder.create(
			new Identifier(MOD_ID, "general"))
				.icon(() -> new ItemStack(AURA_QUARTZ))
				.appendItems(stacks -> {
					stacks.add(new ItemStack(SILICON_PICKAXE));
					stacks.add(new ItemStack(SILICON_SWORD));
					stacks.add(new ItemStack(SILICON_AXE));
					stacks.add(new ItemStack(SILICON_SHOVEL));
					stacks.add(new ItemStack(SILICON_CHUNK));
					stacks.add(new ItemStack(SILICON_CRYSTAL));
					stacks.add(new ItemStack(SILICON_ORE_BLOCKITEM));
					stacks.add(new ItemStack(NETHER_AURA_QUARTZ_ORE_BLOCKITEM));
					stacks.add(new ItemStack(AURA_QUARTZ));
					stacks.add(new ItemStack(BASTEL_PROCESSOR_L1));
					stacks.add(new ItemStack(RAZOR_PROCESSOR_R1));
					stacks.add(new ItemStack(MEMORY_1VB));
					stacks.add(new ItemStack(MEMORY_4VB));
					stacks.add(new ItemStack(MEMORY_16VB));
					stacks.add(new ItemStack(SERVER_4U_BLOCKITEM));
				})
				.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mald caution.

		// ITEMS ------------------------------------------------------------------------------------------
		// When the game initializes register our items!
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_pickaxe"), SILICON_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_sword"),   SILICON_SWORD);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_axe"),     SILICON_AXE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_shovel"),  SILICON_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bastel_processor_l1"),     BASTEL_PROCESSOR_L1);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "razor_processor_r1"),  RAZOR_PROCESSOR_R1);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "memory_1vb"),  MEMORY_1VB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "memory_4vb"),  MEMORY_4VB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "memory_16vb"),  MEMORY_16VB);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "server_4u"),  SERVER_4U_BLOCKITEM);

		// Overworld Items
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_ore"), SILICON_ORE_BLOCKITEM);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_chunk"),   SILICON_CHUNK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "silicon_crystal"), SILICON_CRYSTAL);

		// Nether Items
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_aura_quartz_ore"), NETHER_AURA_QUARTZ_ORE_BLOCKITEM);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aura_quartz"), AURA_QUARTZ);

		// BLOCKS ------------------------------------------------------------------------------------------
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "silicon_ore"), SILICON_ORE);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "server_4u"),  SERVER_4U);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "nether_aura_quartz_ore"), NETHER_AURA_QUARTZ_ORE);

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
