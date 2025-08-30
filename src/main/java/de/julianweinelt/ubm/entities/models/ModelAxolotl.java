package de.julianweinelt.ubm.entities.models;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelAxolotl extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer right_arm;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_arm;
	private final ModelRenderer left_leg;
	private final ModelRenderer tail;
	private final ModelRenderer head;
	private final ModelRenderer left_gills;
	private final ModelRenderer right_gills;
	private final ModelRenderer top_gills;

	public ModelAxolotl() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 28.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 4.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 11, -4.0F, -1.0F, -9.0F, 8, 4, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 2, 17, 0.0F, -2.0F, -9.0F, 0, 5, 9, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, 2.0F, -8.0F);
		body.addChild(right_arm);
		setRotationAngle(right_arm, 0.0F, -1.5708F, 1.5708F);
		right_arm.cubeList.add(new ModelBox(right_arm, 2, 13, -2.0F, 0.0F, 0.0F, 3, 5, 0, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 2.0F, 0.0F);
		body.addChild(right_leg);
		setRotationAngle(right_leg, 0.0F, 1.5708F, 1.5708F);
		right_leg.cubeList.add(new ModelBox(right_leg, 2, 13, -1.0F, 0.0F, 0.0F, 3, 5, 0, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(4.0F, 2.0F, -8.0F);
		body.addChild(left_arm);
		setRotationAngle(left_arm, 0.0F, 1.5708F, -1.5708F);
		left_arm.cubeList.add(new ModelBox(left_arm, 2, 13, -1.0F, 0.0F, 0.0F, 3, 5, 0, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 2.0F, 0.0F);
		body.addChild(left_leg);
		setRotationAngle(left_leg, 0.0F, -1.5708F, -1.5708F);
		left_leg.cubeList.add(new ModelBox(left_leg, 2, 13, -2.0F, 0.0F, 0.0F, 3, 5, 0, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 1.0F, 0.0F);
		body.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 2, 19, 0.0F, -3.0F, 0.0F, 0, 5, 12, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 1.0F, -9.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 1, -4.0F, -3.0F, -5.0F, 8, 5, 5, 0.0F, false));

		left_gills = new ModelRenderer(this);
		left_gills.setRotationPoint(4.0F, 0.0F, -1.0F);
		head.addChild(left_gills);
		left_gills.cubeList.add(new ModelBox(left_gills, 11, 40, 0.0F, -5.0F, 0.0F, 3, 7, 0, 0.0F, false));

		right_gills = new ModelRenderer(this);
		right_gills.setRotationPoint(-4.0F, 0.0F, -1.0F);
		head.addChild(right_gills);
		right_gills.cubeList.add(new ModelBox(right_gills, 0, 40, -3.0F, -5.0F, 0.0F, 3, 7, 0, 0.0F, false));

		top_gills = new ModelRenderer(this);
		top_gills.setRotationPoint(0.0F, -3.0F, -1.0F);
		head.addChild(top_gills);
		top_gills.cubeList.add(new ModelBox(top_gills, 3, 37, -4.0F, -3.0F, 0.0F, 8, 3, 0, 0.0F, false));
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

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
								  float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;

		float swimSpeed = 0.4F;
		float swimDegree = 0.4F;
		this.tail.rotateAngleY = (float) Math.cos(ageInTicks * swimSpeed) * swimDegree;

		this.left_arm.rotateAngleX = (float) Math.cos(ageInTicks * 0.5F) * 0.3F;
		this.right_arm.rotateAngleX = (float) Math.cos(ageInTicks * 0.5F + Math.PI) * 0.3F;
		this.left_leg.rotateAngleX = (float) Math.cos(ageInTicks * 0.5F + Math.PI) * 0.3F;
		this.right_leg.rotateAngleX = (float) Math.cos(ageInTicks * 0.5F) * 0.3F;

		float gillSpeed = 0.15F;
		float gillDegree = 0.1F;
		this.left_gills.rotateAngleZ = (float) Math.cos(ageInTicks * gillSpeed) * gillDegree;
		this.right_gills.rotateAngleZ = -(float) Math.cos(ageInTicks * gillSpeed) * gillDegree;
		this.top_gills.rotateAngleX = (float) Math.sin(ageInTicks * gillSpeed * 0.5F) * gillDegree;
	}

}