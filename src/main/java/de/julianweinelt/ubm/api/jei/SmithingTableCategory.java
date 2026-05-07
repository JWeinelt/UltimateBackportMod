package de.julianweinelt.ubm.api.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class SmithingTableCategory implements IRecipeCategory<SmithingTableRecipeWrapper> {
    public static final String UID = "ubm.smithing_table";

    private final IDrawable background;

    public SmithingTableCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                new ResourceLocation("ubm", "textures/gui/smithing.png"),
                4, 4, 150, 71
        );
    }

    @Override
    @Nonnull
    public String getUid() {
        return UID;
    }

    @Override
    @Nonnull
    public String getTitle() {
        return "Smithing Table";
    }

    @Override
    @Nonnull
    public String getModName() {
        return "ubm";
    }

    @Override
    @Nonnull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout layout,
                          @Nonnull SmithingTableRecipeWrapper wrapper,
                          @Nonnull IIngredients ingredients) {

        IGuiItemStackGroup stacks = layout.getItemStacks();

        stacks.init(0, true, 3, 43);

        stacks.init(1, true, 26 - 5, 43);

        stacks.init(2, true, 44 - 5, 43);

        stacks.init(3, false, 98 - 5, 43);

        stacks.set(ingredients);
    }
}
