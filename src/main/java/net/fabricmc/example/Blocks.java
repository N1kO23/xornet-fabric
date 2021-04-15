package net.fabricmc.example;

import net.fabricmc.example.blocks.Server1U;
import net.fabricmc.example.blocks.Server2U;
import net.fabricmc.example.blocks.Server4U;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;

public class Blocks {

    // Servers
    public static final Block SERVER_4U = new Server4U(FabricBlockSettings.of(Material.METAL).strength(3.0f));
    public static final Block SERVER_2U = new Server2U(FabricBlockSettings.of(Material.METAL).strength(3.0f));
    public static final Block SERVER_1U = new Server1U(FabricBlockSettings.of(Material.METAL).strength(3.0f));

    // Ores
    public static final Block SILICON_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block NETHER_AURA_QUARTZ_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));

}
