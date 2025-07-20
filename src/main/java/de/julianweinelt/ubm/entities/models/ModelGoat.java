package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGoat extends ModelBase {
	private final ModelRenderer left_back_leg;
	private final ModelRenderer right_back_leg;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer left_front_leg;
	private final ModelRenderer body;
	private final ModelRenderer Head;
	private final ModelRenderer Head_r1;
	private final ModelRenderer HeadMain;

	public ModelGoat() {
		textureWidth = 64;
		textureHeight = 64;

		left_back_leg = new ModelRenderer(this);
		left_back_leg.setRotationPoint(1.0F, 14.0F, 4.0F);
		left_back_leg.cubeList.add(new ModelBox(left_back_leg, 36, 29, 0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F, false));

		right_back_leg = new ModelRenderer(this);
		right_back_leg.setRotationPoint(-3.0F, 14.0F, 4.0F);
		right_back_leg.cubeList.add(new ModelBox(right_back_leg, 49, 29, 0.0F, 4.0F, 0.0F, 3, 6, 3, 0.0F, false));

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-3.0F, 14.0F, -6.0F);
		right_front_leg.cubeList.add(new ModelBox(right_front_leg, 49, 2, 0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F, false));

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(1.0F, 14.0F, -6.0F);
		left_front_leg.cubeList.add(new ModelBox(left_front_leg, 35, 2, 0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 1, 1, -4.0F, -17.0F, -7.0F, 9, 11, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 28, -5.0F, -18.0F, -8.0F, 11, 14, 11, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(1.0F, 14.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 12, 55, -2.99F, -16.0F, -10.0F, 2, 7, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 12, 55, -0.01F, -16.0F, -10.0F, 2, 7, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 2, 61, 2.0F, -11.0F, -10.0F, 3, 2, 1, 0.0F, true));
		Head.cubeList.add(new ModelBox(Head, 2, 61, -6.0F, -11.0F, -10.0F, 3, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 23, 52, -0.5F, -3.0F, -14.0F, 0, 7, 5, 0.0F, false));

		Head_r1 = new ModelRenderer(this);
		Head_r1.setRotationPoint(0.0F, -8.0F, -8.0F);
		Head.addChild(Head_r1);
		setRotationAngle(Head_r1, 0.9599F, 0.0F, 0.0F);
		Head_r1.cubeList.add(new ModelBox(Head_r1, 34, 46, -3.0F, -4.0F, -8.0F, 5, 7, 10, 0.0F, false));

		HeadMain = new ModelRenderer(this);
		HeadMain.setRotationPoint(0.0F, -8.0F, -8.0F);
		Head.addChild(HeadMain);
		setRotationAngle(HeadMain, 0.9599F, 0.0F, 0.0F);
		HeadMain.cubeList.add(new ModelBox(HeadMain, 34, 46, -3.0F, -4.0F, -8.0F, 5, 7, 10, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left_back_leg.render(f5);
		right_back_leg.render(f5);
		right_front_leg.render(f5);
		left_front_leg.render(f5);
		body.render(f5);
		Head.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
								  float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		this.left_front_leg.rotateAngleX =
				MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.right_back_leg.rotateAngleX =
				MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.right_front_leg.rotateAngleX =
				MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.left_back_leg.rotateAngleX =
				MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

}