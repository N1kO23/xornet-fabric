package net.fabricmc.example;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class Items {
    public static final ArmorMaterial AURA_QUARTZ_ARMOR_MATERIAL = new CustomArmorMaterial();

    // Aura Quartz
    public static final BlockItem NETHER_AURA_QUARTZ_ORE_BLOCKITEM = new BlockItem(Blocks.NETHER_AURA_QUARTZ_ORE, new FabricItemSettings().group(Xornet.XORNET_GROUP));
    public static final Item AURA_QUARTZ = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(64));
    public static final Item AURA_QUARTZ_HELMET =     new ArmorItem(AURA_QUARTZ_ARMOR_MATERIAL, EquipmentSlot.HEAD,  new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final Item AURA_QUARTZ_CHESTPLATE = new ArmorItem(AURA_QUARTZ_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final Item AURA_QUARTZ_LEGGINGS =   new ArmorItem(AURA_QUARTZ_ARMOR_MATERIAL, EquipmentSlot.LEGS,  new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final Item AURA_QUARTZ_BOOTS =      new ArmorItem(AURA_QUARTZ_ARMOR_MATERIAL, EquipmentSlot.FEET,  new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final ToolItem AURA_PICKAXE = 	  new CustomPickaxeItem(AuraQuartzToolMaterial.INSTANCE, 4, -3.0F, new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final ToolItem AURA_SWORD =  		  new CustomSwordItem(AuraQuartzToolMaterial.INSTANCE, 	 8, -3.0F, new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final ToolItem AURA_AXE =  	      new CustomAxeItem(AuraQuartzToolMaterial.INSTANCE, 	 9, -3.0F, new Item.Settings().group(Xornet.XORNET_GROUP));
    public static final ToolItem AURA_SHOVEL = 		  new CustomShovelItem(AuraQuartzToolMaterial.INSTANCE,  3, -3.0F, new Item.Settings().group(Xornet.XORNET_GROUP));

    // Pumpkin
    public static final ArmorMaterial PUMPKIN_ARMOR_MATERIAL = new CustomArmorMaterial();
    static final Item POWERED_PUMPKIN_HELMET = new ArmorItem(Items.PUMPKIN_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));

    // Silicon
    public static final Item SILICON_CHUNK = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(64));
    public static final Item SILICON_CRYSTAL = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(64));
    public static final BlockItem SILICON_ORE_BLOCKITEM = new BlockItem(Blocks.SILICON_ORE, new FabricItemSettings().group(Xornet.XORNET_GROUP));
    
    // Processors
    public static final Item BASTEL_PROCESSOR_L1 = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(1));
    public static final Item RAZOR_PROCESSOR_R1 = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(1));

    // Memory
    public static final Item MEMORY_1VB = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(16));
    public static final Item MEMORY_4VB = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(16));
    public static final Item MEMORY_16VB = new Item(new FabricItemSettings().group(Xornet.XORNET_GROUP).maxCount(16));

    // Servers
    public static final BlockItem SERVER_4U_BLOCKITEM = new BlockItem(Blocks.SERVER_4U, new FabricItemSettings().group(Xornet.XORNET_GROUP));
    public static final BlockItem SERVER_2U_BLOCKITEM = new BlockItem(Blocks.SERVER_2U, new FabricItemSettings().group(Xornet.XORNET_GROUP));
    public static final BlockItem SERVER_1U_BLOCKITEM = new BlockItem(Blocks.SERVER_1U, new FabricItemSettings().group(Xornet.XORNET_GROUP));

}
