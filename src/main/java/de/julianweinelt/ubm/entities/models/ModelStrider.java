package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStrider extends ModelBase {
	private final ModelRenderer Body;
	private final ModelRenderer right_bristles_1;
	private final ModelRenderer left_bristles_1;
	private final ModelRenderer right_bristles_2;
	private final ModelRenderer left_bristles_2;
	private final ModelRenderer right_bristles_3;
	private final ModelRenderer left_bristles_3;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public ModelStrider() {
		textureWidth = 64;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 7.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -8.0F, -14.0F, -8.0F, 16, 14, 16, 0.0F, false));

		right_bristles_1 = new ModelRenderer(this);
		right_bristles_1.setRotationPoint(-8.0F, -13.0F, 0.0F);
		Body.addChild(right_bristles_1);
		setRotationAngle(right_bristles_1, 0.0F, 0.0F, -1.0472F);
		right_bristles_1.cubeList.add(new ModelBox(right_bristles_1, 4, 33, -12.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, true));

		left_bristles_1 = new ModelRenderer(this);
		left_bristles_1.setRotationPoint(8.0F, -13.0F, 0.0F);
		Body.addChild(left_bristles_1);
		setRotationAngle(left_bristles_1, 0.0F, 0.0F, 1.0472F);
		left_bristles_1.cubeList.add(new ModelBox(left_bristles_1, 4, 33, 0.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, false));

		right_bristles_2 = new ModelRenderer(this);
		right_bristles_2.setRotationPoint(-8.0F, -9.0F, 0.0F);
		Body.addChild(right_bristles_2);
		setRotationAngle(right_bristles_2, 0.0F, 0.0F, -1.0472F);
		right_bristles_2.cubeList.add(new ModelBox(right_bristles_2, 4, 49, -12.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, true));

		left_bristles_2 = new ModelRenderer(this);
		left_bristles_2.setRotationPoint(8.0F, -9.0F, 0.0F);
		Body.addChild(left_bristles_2);
		setRotationAngle(left_bristles_2, 0.0F, 0.0F, 1.0472F);
		left_bristles_2.cubeList.add(new ModelBox(left_bristles_2, 4, 49, 0.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, false));

		right_bristles_3 = new ModelRenderer(this);
		right_bristles_3.setRotationPoint(-8.0F, -4.0F, 0.0F);
		Body.addChild(right_bristles_3);
		setRotationAngle(right_bristles_3, 0.0F, 0.0F, -1.0472F);
		right_bristles_3.cubeList.add(new ModelBox(right_bristles_3, 4, 65, -12.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, true));

		left_bristles_3 = new ModelRenderer(this);
		left_bristles_3.setRotationPoint(8.0F, -4.0F, 0.0F);
		Body.addChild(left_bristles_3);
		setRotationAngle(left_bristles_3, 0.0F, 0.0F, 1.0472F);
		left_bristles_3.cubeList.add(new ModelBox(left_bristles_3, 4, 65, 0.0F, 0.0F, -8.0F, 12, 0, 16, 0.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-4.0F, 7.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(4.0F, 7.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 17, 4, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}