package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelArmadillo extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer right_ear;
	private final ModelRenderer right_ear_r1;
	private final ModelRenderer left_ear;
	private final ModelRenderer left_ear_r1;
	private final ModelRenderer right_hind_leg;
	private final ModelRenderer left_hind_leg;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer left_front_leg;

	public ModelArmadillo() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 21.0F, 4.0F);
		body.cubeList.add(new ModelBox(body, 0, 20, -4.0F, -7.0F, -10.0F, 8, 8, 12, 0.3F, false));
		body.cubeList.add(new ModelBox(body, 0, 40, -4.0F, -7.0F, -10.0F, 8, 8, 12, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -3.0F, 1.0F);
		body.addChild(tail);
		setRotationAngle(tail, 0.5061F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 44, 53, -0.5F, -0.0865F, 0.0933F, 1, 6, 1, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -2.0F, -11.0F);
		body.addChild(head);
		

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.3927F, 0.0F, 0.0F);
		head_r1.cubeList.add(new ModelBox(head_r1, 43, 15, -1.5F, -1.0F, -1.0F, 3, 5, 2, 0.0F, false));

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-1.0F, -1.0F, 0.0F);
		head.addChild(right_ear);
		

		right_ear_r1 = new ModelRenderer(this);
		right_ear_r1.setRotationPoint(-0.5F, 0.0F, -0.6F);
		right_ear.addChild(right_ear_r1);
		setRotationAngle(right_ear_r1, 0.1886F, -0.3864F, -0.0718F);
		right_ear_r1.cubeList.add(new ModelBox(right_ear_r1, 43, 10, -2.0F, -3.0F, 0.0F, 2, 5, 0, 0.0F, false));

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(1.0F, -2.0F, 0.0F);
		head.addChild(left_ear);
		

		left_ear_r1 = new ModelRenderer(this);
		left_ear_r1.setRotationPoint(0.5F, 1.0F, -0.6F);
		left_ear.addChild(left_ear_r1);
		setRotationAngle(left_ear_r1, 0.1886F, 0.3864F, 0.0718F);
		left_ear_r1.cubeList.add(new ModelBox(left_ear_r1, 47, 10, 0.0F, -3.0F, 0.0F, 2, 5, 0, 0.0F, false));

		right_hind_leg = new ModelRenderer(this);
		right_hind_leg.setRotationPoint(-2.0F, 21.0F, 4.0F);
		right_hind_leg.cubeList.add(new ModelBox(right_hind_leg, 51, 31, -1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F, false));

		left_hind_leg = new ModelRenderer(this);
		left_hind_leg.setRotationPoint(2.0F, 21.0F, 4.0F);
		left_hind_leg.cubeList.add(new ModelBox(left_hind_leg, 42, 31, -1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F, false));

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-2.0F, 21.0F, -4.0F);
		right_front_leg.cubeList.add(new ModelBox(right_front_leg, 51, 43, -1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F, false));

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(2.0F, 21.0F, -4.0F);
		left_front_leg.cubeList.add(new ModelBox(left_front_leg, 42, 43, -1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F, false));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		right_hind_leg.render(f5);
		left_hind_leg.render(f5);
		right_front_leg.render(f5);
		left_front_leg.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}