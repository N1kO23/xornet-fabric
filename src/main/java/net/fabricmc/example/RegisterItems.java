package net.fabricmc.example;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
    // Registers the custom items into registry
    public static void register() {

        // Aura Quartz
		Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "nether_aura_quartz_ore"), Items.NETHER_AURA_QUARTZ_ORE_BLOCKITEM);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_quartz"),            Items.AURA_QUARTZ);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_quartz_helmet"),     Items.AURA_QUARTZ_HELMET);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_quartz_chestplate"), Items.AURA_QUARTZ_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_quartz_leggings"),   Items.AURA_QUARTZ_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_quartz_boots"),      Items.AURA_QUARTZ_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_sword"),             Items.AURA_SWORD);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_pickaxe"),           Items.AURA_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_shovel"),            Items.AURA_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "aura_axe"),               Items.AURA_AXE);

        // Pumpkin 
		Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "powered_pumpkin_helmet"), Items.POWERED_PUMPKIN_HELMET);

        // Silicon
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "silicon_chunk"),          Items.SILICON_CHUNK);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "silicon_crystal"),        Items.SILICON_CRYSTAL);
		Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "silicon_ore"),            Items.SILICON_ORE_BLOCKITEM);

        // Processors
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "bastel_processor_l1"),    Items.BASTEL_PROCESSOR_L1);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "razor_processor_r1"),     Items.RAZOR_PROCESSOR_R1);

        // Memory
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "memory_1vb"),             Items.MEMORY_1VB);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "memory_4vb"),             Items.MEMORY_4VB);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "memory_16vb"),            Items.MEMORY_16VB);

        // Servers
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "server_4u"),              Items.SERVER_4U_BLOCKITEM);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "server_2u"),              Items.SERVER_2U_BLOCKITEM);
        Registry.register(Registry.ITEM, new Identifier(Xornet.MOD_ID, "server_1u"),              Items.SERVER_1U_BLOCKITEM);

    }
}