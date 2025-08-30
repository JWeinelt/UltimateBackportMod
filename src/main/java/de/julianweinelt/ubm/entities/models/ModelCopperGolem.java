package de.julianweinelt.ubm.entities.models;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelCopperGolem extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer right_arm;
	private final ModelRenderer rightItem;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelCopperGolem() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(1.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, -5.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 15, -4.0F, -6.0F, -3.0F, 8, 6, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -6.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -5.0F, -5.0F, 8, 5, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 56, 0, -1.0F, -2.0F, -6.0F, 2, 3, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 37, 8, -1.0F, -9.0F, -1.0F, 2, 4, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 37, 0, -2.0F, -13.0F, -2.0F, 4, 4, 4, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, -6.0F, 0.0F);
		body.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 36, 16, -3.0F, -1.0F, -2.0F, 3, 10, 4, 0.0F, false));

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(-1.0F, 7.4F, -1.0F);
		right_arm.addChild(rightItem);
		

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(4.0F, -6.0F, 0.0F);
		body.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 50, 16, 0.0F, -1.0F, -2.0F, 3, 10, 4, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-3.0F, -5.0F, 0.0F);
		root.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 27, -1.9F, 0.0F, -1.99F, 4, 5, 4, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(1.0F, -5.0F, 0.0F);
		root.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 16, 27, -2.1F, 0.0F, -2.0F, 4, 5, 4, 0.0F, false));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}