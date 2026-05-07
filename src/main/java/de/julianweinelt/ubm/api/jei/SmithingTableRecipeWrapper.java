package de.julianweinelt.ubm.api.jei;

import de.julianweinelt.ubm.api.SmithingTableRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class SmithingTableRecipeWrapper implements IRecipeWrapper {
    private SmithingTableRecipe recipe;

    public SmithingTableRecipeWrapper(SmithingTableRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, Arrays.asList(
                recipe.getPiece(),
                recipe.getTemplate(),
                recipe.getMaterial()
        ));

        ingredients.setOutput(ItemStack.class, recipe.getResult());
    }
}