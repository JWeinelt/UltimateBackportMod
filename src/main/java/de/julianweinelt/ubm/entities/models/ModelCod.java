package de.julianweinelt.ubm.entities.models;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelCod extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;
	private final ModelRenderer tailfin;
	private final ModelRenderer waist;

	public ModelCod() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -1.0F, -4.0F, 1.0F, 2, 4, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 20, -6, 0.0F, -5.0F, 0.0F, 0, 1, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 22, -1, 0.0F, 0.0F, 3.0F, 0, 1, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -2.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -0.9992F, -2.0008F, -3.0F, 2, 3, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 11, 0, -1.0F, -2.0F, -2.0F, 2, 4, 3, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.0F, -1.0F, 0.0F);
		body.addChild(leftFin);
		setRotationAngle(leftFin, 0.0F, 0.0F, 0.6109F);
		leftFin.cubeList.add(new ModelBox(leftFin, 24, 4, 0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(-1.0F, -1.0F, 0.0F);
		body.addChild(rightFin);
		setRotationAngle(rightFin, 0.0F, 0.0F, -0.6109F);
		rightFin.cubeList.add(new ModelBox(rightFin, 24, 1, -2.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F, false));

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.0F, 0.0F, 8.0F);
		body.addChild(tailfin);
		tailfin.cubeList.add(new ModelBox(tailfin, 20, 1, 0.0F, -4.0F, 0.0F, 0, 4, 6, 0.0F, false));

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(waist);
		
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
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

		this.tailfin.rotateAngleY = MathHelper.cos(ageInTicks * speed) * degree * 1.5F;

		this.leftFin.rotateAngleZ = 0.6109F + MathHelper.cos(ageInTicks * speed * 2F) * 0.1F;
		this.rightFin.rotateAngleZ = -0.6109F - MathHelper.cos(ageInTicks * speed * 2F) * 0.1F;
	}
}