package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFrog extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer eyes;
	private final ModelRenderer right_eye;
	private final ModelRenderer left_eye;
	private final ModelRenderer croaking_body;
	private final ModelRenderer tongue;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public ModelFrog() {
		textureWidth = 48;
		textureHeight = 48;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -2.0F, 4.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 3, 1, -3.5F, -2.0F, -8.0F, 7, 3, 9, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 23, 22, -3.5F, -1.0F, -8.0F, 7, 0, 9, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -2.0F, -1.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 23, 13, -3.5F, -1.0F, -7.0F, 7, 0, 9, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 13, -3.5F, -2.0F, -7.0F, 7, 3, 9, 0.0F, false));

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(-0.5F, 0.0F, 2.0F);
		head.addChild(eyes);
		

		right_eye = new ModelRenderer(this);
		right_eye.setRotationPoint(-1.5F, -3.0F, -6.5F);
		eyes.addChild(right_eye);
		right_eye.cubeList.add(new ModelBox(right_eye, 0, 0, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		left_eye = new ModelRenderer(this);
		left_eye.setRotationPoint(2.5F, -3.0F, -6.5F);
		eyes.addChild(left_eye);
		left_eye.cubeList.add(new ModelBox(left_eye, 0, 5, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F, false));

		croaking_body = new ModelRenderer(this);
		croaking_body.setRotationPoint(0.0F, -1.0F, -5.0F);
		body.addChild(croaking_body);
		croaking_body.cubeList.add(new ModelBox(croaking_body, 26, 5, -3.5F, -0.1F, -2.9F, 7, 2, 3, -0.1F, false));

		tongue = new ModelRenderer(this);
		tongue.setRotationPoint(0.0F, -1.1F, 1.0F);
		body.addChild(tongue);
		tongue.cubeList.add(new ModelBox(tongue, 17, 13, -2.0F, 0.0F, -7.1F, 4, 0, 7, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(4.0F, -1.0F, -6.5F);
		body.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 0, 32, -1.0F, 0.0F, -1.0F, 2, 3, 3, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 18, 40, -4.0F, 3.01F, -5.0F, 8, 0, 8, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, -1.0F, -6.5F);
		body.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 0, 38, -1.0F, 0.0F, -1.0F, 2, 3, 3, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 2, 40, -4.0F, 3.01F, -5.0F, 8, 0, 8, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(3.5F, -3.0F, 4.0F);
		root.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 14, 25, -1.0F, 0.0F, -2.0F, 3, 3, 4, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 2, 32, -2.0F, 3.01F, -4.0F, 8, 0, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-3.5F, -3.0F, 4.0F);
		root.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 25, -2.0F, 0.0F, -2.0F, 3, 3, 4, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 18, 32, -6.0F, 3.01F, -4.0F, 8, 0, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}