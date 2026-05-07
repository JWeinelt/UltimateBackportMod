package de.julianweinelt.ubm.api.jei;

import de.julianweinelt.ubm.api.SmithingTableRecipe;
import de.julianweinelt.ubm.api.SmithingTableRecipeManager;
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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static de.julianweinelt.ubm.UBM.getLogger;

@JEIPlugin
public class JEIHook implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new SmithingTableCategory(helper));
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {

        List<SmithingTableRecipeWrapper> recipes = new ArrayList<>();
        for (SmithingTableRecipe r : SmithingTableRecipeManager.instance().getRecipes()) {
            recipes.add(new SmithingTableRecipeWrapper(r));
        }
        getLogger().info("Registered {} JEI recipes", recipes.size());

        registry.addRecipes(recipes, SmithingTableCategory.UID);

        registry.addRecipeClickArea(
                GuiSmithingTable.class, 8, 48, 90, 10, SmithingTableCategory.UID
        );
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.SMITHING_TABLE), SmithingTableCategory.UID);
    }
}