package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWarden extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer right_ribcage;
	private final ModelRenderer left_ribcage;
	private final ModelRenderer head;
	private final ModelRenderer right_tendril;
	private final ModelRenderer left_tendril;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelWarden() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -21.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -9.0F, -13.0F, -4.0F, 18, 21, 11, 0.0F, false));

		right_ribcage = new ModelRenderer(this);
		right_ribcage.setRotationPoint(-7.0F, -2.0F, -4.0F);
		body.addChild(right_ribcage);
		right_ribcage.cubeList.add(new ModelBox(right_ribcage, 90, 11, -2.0F, -11.0F, -0.1F, 9, 21, 0, 0.0F, false));

		left_ribcage = new ModelRenderer(this);
		left_ribcage.setRotationPoint(7.0F, -2.0F, -4.0F);
		body.addChild(left_ribcage);
		left_ribcage.cubeList.add(new ModelBox(left_ribcage, 90, 11, -7.0F, -11.0F, -0.1F, 9, 21, 0, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -13.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 32, -8.0F, -16.0F, -5.0F, 16, 16, 10, 0.0F, false));

		right_tendril = new ModelRenderer(this);
		right_tendril.setRotationPoint(-8.0F, -12.0F, 0.0F);
		head.addChild(right_tendril);
		right_tendril.cubeList.add(new ModelBox(right_tendril, 52, 32, -16.0F, -13.0F, 0.0F, 16, 16, 0, 0.0F, false));

		left_tendril = new ModelRenderer(this);
		left_tendril.setRotationPoint(8.0F, -12.0F, 0.0F);
		head.addChild(left_tendril);
		left_tendril.cubeList.add(new ModelBox(left_tendril, 58, 0, 0.0F, -13.0F, 0.0F, 16, 16, 0, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-13.0F, -13.0F, 1.0F);
		body.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 44, 50, -4.0F, 0.0F, -4.0F, 8, 28, 8, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(13.0F, -13.0F, 1.0F);
		body.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 0, 58, -4.0F, 0.0F, -4.0F, 8, 28, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-5.9F, -13.0F, 0.0F);
		root.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 76, 48, -3.1F, 0.0F, -3.0F, 6, 13, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(5.9F, -13.0F, 0.0F);
		root.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 76, 76, -2.9F, 0.0F, -3.0F, 6, 13, 6, 0.0F, false));
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