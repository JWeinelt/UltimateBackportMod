package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public class ModelBell extends ModelBase {
	private final ModelRenderer bone;

	public ModelBell() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 15.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, 0.0F, -3.0F, 6, 7, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 13, -4.0F, 7.0F, -4.0F, 8, 2, 8, 0.0F, false));
	}

	@Override
	public void render(@Nullable Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}