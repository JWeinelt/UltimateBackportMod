package de.julianweinelt.ubm.entities.models;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelSalmon extends ModelBase {
	private final ModelRenderer body_front;
	private final ModelRenderer body_back;
	private final ModelRenderer dorsal_back;
	private final ModelRenderer tailfin;
	private final ModelRenderer dorsal_front;
	private final ModelRenderer head;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;

	public ModelSalmon() {
		textureWidth = 32;
		textureHeight = 32;

		body_front = new ModelRenderer(this);
		body_front.setRotationPoint(0.0F, 24.0F, -4.0F);
		body_front.cubeList.add(new ModelBox(body_front, 0, 0, -1.5F, -8.5F, 0.0F, 3, 5, 8, 0.0F, false));

		body_back = new ModelRenderer(this);
		body_back.setRotationPoint(0.0F, 0.0F, 8.0F);
		body_front.addChild(body_back);
		body_back.cubeList.add(new ModelBox(body_back, 0, 13, -1.5F, -8.5F, 0.0F, 3, 5, 8, 0.0F, false));

		dorsal_back = new ModelRenderer(this);
		dorsal_back.setRotationPoint(0.0F, -5.0F, 0.0F);
		body_back.addChild(dorsal_back);
		dorsal_back.cubeList.add(new ModelBox(dorsal_back, 2, 3, 0.0F, -5.5F, 0.0F, 0, 2, 3, 0.0F, false));

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.0F, 0.0F, 8.0F);
		body_back.addChild(tailfin);
		tailfin.cubeList.add(new ModelBox(tailfin, 20, 10, 0.0F, -8.5F, 0.0F, 0, 5, 6, 0.0F, false));

		dorsal_front = new ModelRenderer(this);
		dorsal_front.setRotationPoint(0.0F, -5.0F, 6.0F);
		body_front.addChild(dorsal_front);
		dorsal_front.cubeList.add(new ModelBox(dorsal_front, 4, 2, 0.0F, -5.5F, 0.0F, 0, 2, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 0.0F);
		body_front.addChild(head);
		head.cubeList.add(new ModelBox(head, 22, 0, -1.0F, -5.5F, -3.0F, 2, 4, 3, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.5F, -1.0F, 0.0F);
		body_front.addChild(leftFin);
		setRotationAngle(leftFin, 0.0F, 0.0F, 0.6109F);
		leftFin.cubeList.add(new ModelBox(leftFin, 2, 0, -2.0075F, -2.867F, 0.0F, 2, 0, 2, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(-1.5F, -1.0F, 0.0F);
		body_front.addChild(rightFin);
		setRotationAngle(rightFin, 0.0F, 0.0F, -0.6109F);
		rightFin.cubeList.add(new ModelBox(rightFin, -2, 0, 0.0074F, -2.867F, 0.0F, 2, 0, 2, 0.0F, false));
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body_front.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
								  float netHeadYaw, float headPitch, float scaleFactor, @Nonnull Entity entityIn) {

		float speed = 0.6F;
		float degree = 0.6F;

		this.body_back.rotateAngleY = MathHelper.cos(ageInTicks * speed) * degree * 0.5F;

		this.tailfin.rotateAngleY = MathHelper.cos(ageInTicks * speed) * degree * 1.5F;

		this.leftFin.rotateAngleZ = 0.6109F + MathHelper.cos(ageInTicks * speed * 2F) * 0.1F;
		this.rightFin.rotateAngleZ = -0.6109F - MathHelper.cos(ageInTicks * speed * 2F) * 0.1F;
	}
}