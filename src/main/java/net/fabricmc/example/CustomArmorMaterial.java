package net.fabricmc.example;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CustomArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {10, 14, 14, 11};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 100;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.AURA_QUARTZ);
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public float getToughness() {
        return 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 1.0F;
    }

    public static final CustomArmorMaterial INSTANCE = new CustomArmorMaterial();
}