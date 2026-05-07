package de.julianweinelt.ubm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import de.julianweinelt.ubm.api.jei.SmithingTableRecipeWrapper;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static de.julianweinelt.ubm.UBM.getLogger;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SmithingTableRecipeManager {
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(ItemStack.class, new ItemStackTypeAdapter()).create();

    private final File configFolder;
    private final File recipesFolder;

    private List<SmithingTableRecipe> recipes = new ArrayList<>();

    private static SmithingTableRecipeManager instance;
    public static SmithingTableRecipeManager instance() {
        return instance;
    }
    private SmithingTableRecipeManager(File configFolder) {
        this.configFolder = new File(configFolder, "ubm");
        this.recipesFolder = new File(this.configFolder, "recipes");
        instance = this;
    }

    public static void init(File configFolder) {
        new SmithingTableRecipeManager(configFolder);
    }

    public void loadRecipes() {
        if (recipesFolder.mkdirs()) getLogger().debug("Created recipes folder");

        this.recipes.clear();

        File[] recipes = recipesFolder.listFiles();
        if (recipes == null) return;
        for (File recipe : recipes) {
            if (recipe.getName().endsWith(".json") && !recipe.isDirectory()) {
                loadRecipe(recipe);
            }
        }
        getLogger().info("Loaded {} recipes", recipes.length);
    }

    public void loadRecipe(File recipe) {
        try (BufferedReader br = new BufferedReader(new FileReader(recipe))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) sb.append(line);
            JsonElement e = new JsonParser().parse(sb.toString());
            if (e.isJsonArray()) {
                Type type = new TypeToken<List<SmithingTableRecipe>>() {}.getType();
                List<SmithingTableRecipe> list = GSON.fromJson(e, type);
                recipes.addAll(list);
            } else {
                recipes.add(GSON.fromJson(sb.toString(), SmithingTableRecipe.class));
            }
        } catch (Exception e) {
            getLogger().error("Error while loading recipe {}", recipe.getName(), e);
        }
        performRecipeCheck();
    }

    private void performRecipeCheck() {
        getLogger().info("Performing recipe validation check...");
        for (SmithingTableRecipe r : recipes) {
            if (r.anythingNull() || r.anythingEmpty()) {
                getLogger().warn("Recipe {} is invalid", r.getTemplate().getDisplayName());
                getLogger().warn("Piece: {}, Template: {}, Material: {}, Result: {}", r.getPiece(), r.getTemplate(), r.getMaterial(), r.getResult());
            }
        }
    }
    
    public void saveDefaults() {
        saveRecipes(createDefaults(), "netherite_smithing");
    }

    public boolean checkDefaultsCreated() {
        File f = new File(recipesFolder, "netherite_smithing.json");
        if (!f.exists() && recipes.isEmpty()) {
            recipes.addAll(createDefaults());
            return false;
        }
        return true;
    }

    private List<SmithingTableRecipe> createDefaults() {
        List<SmithingTableRecipe> list = new ArrayList<>();

        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_AXE, ModItems.NETHERITE_AXE));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_SWORD,ModItems.NETHERITE_SWORD));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_SHOVEL,ModItems.NETHERITE_SHOVEL));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_PICKAXE,ModItems.NETHERITE_PICKAXE));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_HOE,ModItems.NETHERITE_HOE));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_HELMET,ModItems.NETHERITE_HELMET));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_CHESTPLATE,ModItems.NETHERITE_CHESTPLATE));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_LEGGINGS,ModItems.NETHERITE_LEGGINGS));
        list.add(SmithingTableRecipe.ofNetherite(Items.DIAMOND_BOOTS,ModItems.NETHERITE_BOOTS));
        return list;
    }

    /**
     * Saves a List of recipe objects to a file.
     * @param recipes A {@link List} of {@link SmithingTableRecipe} objects.
     * @param fileName The name of the file to save the recipes to.
     * @apiNote If the file already exists, it will not be overwritten. Use {@link #saveRecipe(SmithingTableRecipe, String, boolean)} for overriding.
     */
    public void saveRecipes(List<SmithingTableRecipe> recipes, String fileName) {
        saveRecipes(recipes, fileName, false);
    }

    public void saveRecipes(List<SmithingTableRecipe> recipes, String fileName, boolean override) {
        if (recipesFolder.mkdirs()) getLogger().debug("Created recipes folder");
        File saveFile = new File(recipesFolder, fileName + ".json");
        if (saveFile.exists() && !override) return;
        try (FileWriter w = new FileWriter(saveFile)) {
            w.write(GSON.toJson(recipes));
        } catch (IOException e) {
            getLogger().error("Error while saving recipe {}", fileName, e);
        }
    }
    public void saveRecipe(SmithingTableRecipe recipe, String fileName) {
        saveRecipe(recipe, fileName, false);
    }
    public void saveRecipe(SmithingTableRecipe recipe, String fileName, boolean override) {
        if (recipesFolder.mkdirs()) getLogger().debug("Created recipes folder");
        File saveFile = new File(recipesFolder, fileName + ".json");
        if (saveFile.exists() && !override) return;
        try (FileWriter w = new FileWriter(saveFile)) {
            w.write(GSON.toJson(recipe));
        } catch (IOException e) {
            getLogger().error("Error while saving recipe {}", fileName, e);
        }
    }

    public List<SmithingTableRecipe> getRecipes() {
        return recipes;
    }
}
