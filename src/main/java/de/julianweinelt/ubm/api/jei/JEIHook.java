package de.julianweinelt.ubm.api.jei;

import de.julianweinelt.ubm.api.SmithingTableRecipe;
import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.blocks.interactable.smithing.GuiSmithingTable;
import de.julianweinelt.ubm.items.ModItem;
import de.julianweinelt.ubm.items.ModItems;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIHook implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new SmithingTableCategory(helper));
    }

    @Override
    public void register(IModRegistry registry) {

        List<SmithingTableRecipeWrapper> recipes = new ArrayList<>();
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_AXE,ModItems.NETHERITE_AXE)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_SWORD,ModItems.NETHERITE_SWORD)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_SHOVEL,ModItems.NETHERITE_SHOVEL)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_PICKAXE,ModItems.NETHERITE_PICKAXE)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_HOE,ModItems.NETHERITE_HOE)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_HELMET,ModItems.NETHERITE_HELMET)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_CHESTPLATE,ModItems.NETHERITE_CHESTPLATE)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_LEGGINGS,ModItems.NETHERITE_LEGGINGS)));
        recipes.add(new SmithingTableRecipeWrapper(SmithingTableRecipe.ofNetherite(Items.DIAMOND_BOOTS,ModItems.NETHERITE_BOOTS)));

        registry.addRecipes(recipes, SmithingTableCategory.UID);

        registry.addRecipeClickArea(
                GuiSmithingTable.class, 8, 48, 90, 10, SmithingTableCategory.UID
        );
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.SMITHING_TABLE), SmithingTableCategory.UID);
    }
}