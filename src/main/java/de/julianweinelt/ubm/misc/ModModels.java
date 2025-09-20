package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.misc.client.BakedTrimArmorModel;
import de.julianweinelt.ubm.misc.client.TrimItemOverride;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModModels {

    public static void registerAllArmorModels() {
        registerArmorModel(Items.DIAMOND_HELMET);
        registerArmorModel(Items.DIAMOND_CHESTPLATE);
        registerArmorModel(Items.DIAMOND_LEGGINGS);
        registerArmorModel(Items.DIAMOND_BOOTS);

    }

    public static void registerArmorModel(Item item) {
        ModelResourceLocation location = new ModelResourceLocation("minecraft:diamond_chestplate", "inventory");
        IBakedModel baseModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(item));

        IBakedModel bakedModelWithOverride = new BakedTrimArmorModel(baseModel, null);
        bakedModelWithOverride = new BakedTrimArmorModel(baseModel, null) {
            @Override
            public ItemOverrideList getOverrides() {
                return new TrimItemOverride(baseModel);
            }
        };

        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }
}
