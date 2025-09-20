package de.julianweinelt.ubm.blocks.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderTileEntityCampfire extends TileEntitySpecialRenderer<TileEntityCampfire> {

    @Override
    public void render(@Nonnull TileEntityCampfire te, double x, double y, double z, float partialTicks,
                       int destroyStage, float alpha) {

        GlStateManager.pushMatrix();
        GlStateManager.translate(x+0.5, y+0.5, z+0.5);

        for (int i = 0; i < TileEntityCampfire.SLOTS; i++) {
            ItemStack stack = te.getItem(i);
            if (stack != null) {
                GlStateManager.pushMatrix();
                float[] slotRotations = new float[] { 90f, 180f, 0f, -90f };
                float offsetX = (i % 2 == 0 ? -0.2F : 0.2F);
                float offsetZ = (i < 2 ? -0.2F : 0.2F);
                if (i == 0) {
                    offsetZ -= 0.15F;
                } else if (i == 1) {
                    offsetX += 0.1F;
                    offsetZ -= 0.07F;
                } else if (i == 2) {
                    offsetX -= 0.1F;
                    offsetZ += 0.05F;
                } else if (i == 3) {
                    offsetZ += 0.1F;
                }
                GlStateManager.translate(offsetX, -0.05F, offsetZ);
                GlStateManager.rotate(90, 1, 0, 0);
                GlStateManager.scale(0.8F, 0.8F, 0.8F);
                GlStateManager.rotate(slotRotations[i], 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
                GlStateManager.popMatrix();
            } else {
                Minecraft.getMinecraft().getRenderItem().renderItem(ItemStack.EMPTY, ItemCameraTransforms.TransformType.GROUND);
            }
        }

        GlStateManager.popMatrix();
    }
}
