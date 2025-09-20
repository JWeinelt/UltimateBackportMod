package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;


public class ModelHoglin extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer right_ear;
	private final ModelRenderer left_ear;
	private final ModelRenderer leg_back_right;
	private final ModelRenderer leg_back_left;
	private final ModelRenderer leg_front_right;
	private final ModelRenderer leg_front_left;

	public ModelHoglin() {
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, -3.0F);
		body.cubeList.add(new ModelBox(body, 1, 1, -8.0F, -6.0F, -4.0F, 16, 14, 26, 0.02F, false));
		body.cubeList.add(new ModelBox(body, 90, 33, 0.0F, -13.0F, -7.0F, 0, 10, 19, 0.02F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, -2.0F);
		body.addChild(head);
		setRotationAngle(head, 0.8727F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 61, 1, -7.0F, -5.0F, -19.0F, 14, 6, 19, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 13, -8.0F, -11.0F, -14.0F, 2, 11, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 13, 6.0F, -11.0F, -14.0F, 2, 11, 2, 0.0F, false));

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-7.0F, -5.0F, -2.0F);
		head.addChild(right_ear);
		setRotationAngle(right_ear, 0.0F, 0.0F, -0.8727F);
		right_ear.cubeList.add(new ModelBox(right_ear, 1, 1, -6.0F, 0.0F, -3.0F, 6, 1, 4, 0.0F, false));

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(7.0F, -5.0F, -2.0F);
		head.addChild(left_ear);
		setRotationAngle(left_ear, 0.0F, 0.0F, 0.8727F);
		left_ear.cubeList.add(new ModelBox(left_ear, 1, 6, 0.0F, 0.0F, -3.0F, 6, 1, 4, 0.0F, false));

		leg_back_right = new ModelRenderer(this);
		leg_back_right.setRotationPoint(6.0F, 16.0F, 17.0F);
		leg_back_right.cubeList.add(new ModelBox(leg_back_right, 21, 45, -14.0F, -3.0F, -4.0F, 5, 11, 5, 0.0F, false));

		leg_back_left = new ModelRenderer(this);
		leg_back_left.setRotationPoint(-6.0F, 16.0F, 17.0F);
		leg_back_left.cubeList.add(new ModelBox(leg_back_left, 0, 45, 9.0F, -3.0F, -4.0F, 5, 11, 5, 0.0F, false));

		leg_front_right = new ModelRenderer(this);
		leg_front_right.setRotationPoint(-6.0F, 12.0F, -3.0F);
		leg_front_right.cubeList.add(new ModelBox(leg_front_right, 66, 42, -2.0F, -2.0F, -3.0F, 6, 14, 6, 0.0F, false));

		leg_front_left = new ModelRenderer(this);
		leg_front_left.setRotationPoint(6.0F, 12.0F, -3.0F);
		leg_front_left.cubeList.add(new ModelBox(leg_front_left, 41, 42, -4.0F, -2.0F, -3.0F, 6, 14, 6, 0.0F, false));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		leg_back_right.render(f5);
		leg_back_left.render(f5);
		leg_front_right.render(f5);
		leg_front_left.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}