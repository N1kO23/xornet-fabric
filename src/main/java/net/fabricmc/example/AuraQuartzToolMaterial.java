package net.fabricmc.example;

import net.fabricmc.example.Xornet;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AuraQuartzToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 500;
    }
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }
    public float getAttackDamage() {
        return 3.0F;
    }
    public int getMiningLevel() {
        return 2;
    }
    public int getEnchantability() {
        return 15;
    }
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.AURA_QUARTZ);
    }

    public static final AuraQuartzToolMaterial INSTANCE = new AuraQuartzToolMaterial();
}