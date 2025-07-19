package de.julianweinelt.ubm.entities.models;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBee extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer stinger;
	private final ModelRenderer rightwing_bone;
	private final ModelRenderer leftwing_bone;
	private final ModelRenderer leg_front;
	private final ModelRenderer leg_mid;
	private final ModelRenderer leg_back;

	public ModelBee() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, 19.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -3.5F, -4.0F, -5.0F, 7, 7, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 2, 3, -2.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 2, 0, 1.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F, false));

		stinger = new ModelRenderer(this);
		stinger.setRotationPoint(0.0F, -1.0F, 1.0F);
		body.addChild(stinger);
		stinger.cubeList.add(new ModelBox(stinger, 26, 7, 0.0F, 0.0F, 4.0F, 0, 1, 2, 0.0F, false));

		rightwing_bone = new ModelRenderer(this);
		rightwing_bone.setRotationPoint(-1.5F, -4.0F, -3.0F);
		body.addChild(rightwing_bone);
		setRotationAngle(rightwing_bone, 0.2618F, -0.2618F, 0.0F);
		rightwing_bone.cubeList.add(new ModelBox(rightwing_bone, 0, 18, -9.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F, false));

		leftwing_bone = new ModelRenderer(this);
		leftwing_bone.setRotationPoint(1.5F, -4.0F, -3.0F);
		body.addChild(leftwing_bone);
		setRotationAngle(leftwing_bone, 0.2618F, 0.2618F, 0.0F);
		leftwing_bone.cubeList.add(new ModelBox(leftwing_bone, 9, 24, 0.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F, false));

		leg_front = new ModelRenderer(this);
		leg_front.setRotationPoint(1.5F, 3.0F, -2.0F);
		body.addChild(leg_front);
		leg_front.cubeList.add(new ModelBox(leg_front, 26, 1, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, false));

		leg_mid = new ModelRenderer(this);
		leg_mid.setRotationPoint(1.5F, 3.0F, 0.0F);
		body.addChild(leg_mid);
		leg_mid.cubeList.add(new ModelBox(leg_mid, 26, 3, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, false));

		leg_back = new ModelRenderer(this);
		leg_back.setRotationPoint(1.5F, 3.0F, 2.0F);
		body.addChild(leg_back);
		leg_back.cubeList.add(new ModelBox(leg_back, 26, 5, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
								  float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		float flutterSpeed = 3.0F;
		float flutterDegree = 0.6F;

		this.leftwing_bone.rotateAngleZ =
				MathHelper.cos(ageInTicks * flutterSpeed) * flutterDegree;

		this.rightwing_bone.rotateAngleZ =
				-MathHelper.cos(ageInTicks * flutterSpeed) * flutterDegree;


	}
}