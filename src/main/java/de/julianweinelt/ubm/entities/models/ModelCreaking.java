package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelCreaking extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer upperBody;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public ModelCreaking() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		upperBody = new ModelRenderer(this);
		upperBody.setRotationPoint(-1.0F, -19.0F, 0.0F);
		root.addChild(upperBody);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(-3.0F, -11.0F, 0.0F);
		upperBody.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -10.0F, -3.0F, 6, 10, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 28, 31, -3.0F, -13.0F, -3.0F, 6, 3, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 12, 40, 3.0F, -13.0F, 0.0F, 9, 14, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 34, 12, -12.0F, -14.0F, 0.0F, 9, 14, 0, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 1.0F);
		upperBody.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 16, 0.0F, -3.0F, -3.0F, 6, 13, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 24, 0, -6.0F, -4.0F, -3.0F, 6, 7, 5, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-7.0F, -9.5F, 1.5F);
		upperBody.addChild(rightArm);
		rightArm.cubeList.add(new ModelBox(rightArm, 22, 13, -2.0F, -1.5F, -1.5F, 3, 21, 3, 0.0F, false));
		rightArm.cubeList.add(new ModelBox(rightArm, 46, 0, -2.0F, 19.5F, -1.5F, 3, 4, 3, 0.0F, false));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(6.0F, -9.0F, 0.5F);
		upperBody.addChild(leftArm);
		leftArm.cubeList.add(new ModelBox(leftArm, 30, 40, 0.0F, -1.0F, -1.5F, 3, 16, 3, 0.0F, false));
		leftArm.cubeList.add(new ModelBox(leftArm, 52, 12, 0.0F, -5.0F, -1.5F, 3, 4, 3, 0.0F, false));
		leftArm.cubeList.add(new ModelBox(leftArm, 52, 19, 0.0F, 15.0F, -1.5F, 3, 4, 3, 0.0F, false));

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.5F, -16.0F, 0.5F);
		root.addChild(leftLeg);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 42, 40, -1.5F, 0.0F, -1.5F, 3, 16, 3, 0.0F, false));
		leftLeg.cubeList.add(new ModelBox(leftLeg, 45, 55, -1.5F, 15.7F, -4.5F, 5, 0, 9, 0.0F, false));

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-1.0F, -17.5F, 0.5F);
		root.addChild(rightLeg);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 0, 34, -3.0F, -1.5F, -1.5F, 3, 19, 3, 0.0F, false));
		rightLeg.cubeList.add(new ModelBox(rightLeg, 45, 46, -5.0F, 17.2F, -4.5F, 5, 0, 9, 0.0F, false));
		rightLeg.cubeList.add(new ModelBox(rightLeg, 12, 34, -3.0F, -4.5F, -1.5F, 3, 3, 3, 0.0F, false));
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