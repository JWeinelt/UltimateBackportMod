package de.julianweinelt.ubm.api;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmithingTableRecipe {
    private final ItemStack piece;
    private final ItemStack template;
    private final ItemStack material;
    private final ItemStack result;

    public SmithingTableRecipe(ItemStack piece, ItemStack template, ItemStack material, ItemStack result) {
        this.piece = piece;
        this.template = template;
        this.material = material;
        this.result = result;
    }

    public ItemStack getPiece() {
        return piece;
    }

    public ItemStack getTemplate() {
        return template;
    }

    public ItemStack getResult() {
        return result;
    }

    public ItemStack getMaterial() {
        return material;
    }

    public static SmithingTableRecipe of(Item piece, Item template, Item material, Item result) {
        return new SmithingTableRecipe(new ItemStack(piece), new ItemStack(template), new ItemStack(material), new ItemStack(result));
    }
    public static SmithingTableRecipe ofNetherite(Item input, Item netherite) {
        return new SmithingTableRecipe(new ItemStack(input), new ItemStack(ModItems.TRIM_NETHERITE_UPGRADE), new ItemStack(ModItems.NETHERITE_INGOT), new ItemStack(netherite));
    }

    public boolean anythingEmpty() {
        return piece.isEmpty() || template.isEmpty() || material.isEmpty() || result.isEmpty();
    }
    public boolean anythingNull() {
        return piece == null || template == null || material == null || result == null;
    }
}
