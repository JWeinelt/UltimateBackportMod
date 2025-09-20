package de.julianweinelt.ubm.misc.client;

import de.julianweinelt.ubm.trims.TrimColorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.TRSRTransformation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class InventoryTrimRenderer {

    public static void renderTrim(ItemStack stack, float x, float y, float scale) {
        if(stack.isEmpty() || !stack.hasTagCompound()) return;

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) return;
        if(!nbt.hasKey("trim") || !nbt.hasKey("trimMaterial")) return;

        String trim = nbt.getString("trim");
        String material = nbt.getString("trimMaterial");

        String type = "";
        if(stack.getItem() instanceof ItemArmor) {
            ItemArmor.ArmorMaterial mat = ((ItemArmor) stack.getItem()).getArmorMaterial();
            switch(((ItemArmor) stack.getItem()).armorType) {
                case HEAD: type = "helmet"; break;
                case CHEST: type = "chestplate"; break;
                case LEGS: type = "leggings"; break;
                case FEET: type = "boots"; break;
            }
        }

        ResourceLocation texture = new ResourceLocation("ubm", "textures/trims/items" + type + "_trim.png");

        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, 100);
        GlStateManager.scale(scale, scale, scale);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        renderQuad();

        GlStateManager.popMatrix();
    }

    public static void renderQuad() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        buffer.pos(0, 0 + 16, 0).tex(0, 1).endVertex();
        buffer.pos(0 + 16, 0 + 16, 0).tex(1, 1).endVertex();
        buffer.pos(0 + 16, 0, 0).tex(1, 0).endVertex();
        buffer.pos(0, 0, 0).tex(0, 0).endVertex();

        tessellator.draw();
    }

    public static List<BakedQuad> getTrimQuads(ResourceLocation texture) {
        List<BakedQuad> quads = new ArrayList<>();

        ResourceLocation tex = TrimColorHelper.createColoredTrim(texture,
                TrimColorHelper.extractPalette("textures/equipment/trims/color_palettes/gold.png")); //TODO: Make dynamic

        Minecraft mc = Minecraft.getMinecraft();
        TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite(texture.toString());
        if(sprite == null) return quads;

        FaceBakery bakery = new FaceBakery();

        BlockPartFace face = new BlockPartFace(null, 0, "", new BlockFaceUV(new float[]{0, 0, 16, 16}, 0));

        Vector3f from = new Vector3f(0, 0, 0);
        Vector3f to = new Vector3f(16, 16, 0);

        BlockPartRotation rotation = new BlockPartRotation(new Vector3f(0, 0, 0), EnumFacing.Axis.X, 0, false);

        BakedQuad quad = bakery.makeBakedQuad(
                from,
                to,
                face,
                sprite,
                EnumFacing.NORTH,
                TRSRTransformation.identity(),
                rotation,
                false,
                true
        );

        quads.add(quad);
        return quads;
    }
}
