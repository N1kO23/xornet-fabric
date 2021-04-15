package net.fabricmc.example;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterBlocks {
    // Registers the custom items into registry
    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(Xornet.MOD_ID, "silicon_ore"), Blocks.SILICON_ORE);
        Registry.register(Registry.BLOCK, new Identifier(Xornet.MOD_ID, "server_4u"),  Blocks.SERVER_4U);
        Registry.register(Registry.BLOCK, new Identifier(Xornet.MOD_ID, "server_2u"),  Blocks.SERVER_2U);
        Registry.register(Registry.BLOCK, new Identifier(Xornet.MOD_ID, "server_1u"),  Blocks.SERVER_1U);
        Registry.register(Registry.BLOCK, new Identifier(Xornet.MOD_ID, "nether_aura_quartz_ore"), Blocks.NETHER_AURA_QUARTZ_ORE);
    }
}