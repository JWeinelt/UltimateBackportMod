package de.julianweinelt.ubm.misc.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BakedTrimArmorModel implements IBakedModel {
    private final IBakedModel baseModel;
    private final ResourceLocation trimTexture;

    public BakedTrimArmorModel(IBakedModel baseModel, ResourceLocation trimTexture) {
        this.baseModel = baseModel;
        this.trimTexture = trimTexture;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        List<BakedQuad> quads = new ArrayList<>(baseModel.getQuads(state, side, rand));

        if(trimTexture != null) {
            quads.addAll(InventoryTrimRenderer.getTrimQuads(trimTexture));
        }

        return quads;
    }

    @Override
    public boolean isAmbientOcclusion() { return baseModel.isAmbientOcclusion(); }
    @Override
    public boolean isGui3d() { return baseModel.isGui3d(); }
    @Override
    public boolean isBuiltInRenderer() { return baseModel.isBuiltInRenderer(); }
    @Override
    public TextureAtlasSprite getParticleTexture() { return baseModel.getParticleTexture(); }
    @Override
    public ItemCameraTransforms getItemCameraTransforms() { return baseModel.getItemCameraTransforms(); }
    @Override
    public ItemOverrideList getOverrides() { return baseModel.getOverrides(); }
}
