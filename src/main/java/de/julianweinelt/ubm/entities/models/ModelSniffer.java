package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelSniffer extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer left_ear;
	private final ModelRenderer right_ear;
	private final ModelRenderer nose;
	private final ModelRenderer lower_beak;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer right_mid_leg;
	private final ModelRenderer right_hind_leg;
	private final ModelRenderer left_front_leg;
	private final ModelRenderer left_mid_leg;
	private final ModelRenderer left_hind_leg;

	public ModelSniffer() {
		textureWidth = 192;
		textureHeight = 192;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 5.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 19.0F, 0.0F);
		bone.addChild(body);
		body.cubeList.add(new ModelBox(body, 62, 0, -12.5F, -33.0F, -20.0F, 25, 24, 40, 0.5F, false));
		body.cubeList.add(new ModelBox(body, 62, 68, -12.5F, -33.0F, -20.0F, 25, 29, 40, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 87, 68, -12.5F, -8.0F, -20.0F, 25, 0, 40, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -13.5F, -19.4F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 8, 15, -6.5F, -7.5F, -11.5F, 13, 18, 11, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 4, -6.5F, 7.5F, -11.5F, 13, 0, 11, 0.0F, false));

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(6.4F, -7.5F, -4.5F);
		head.addChild(left_ear);
		left_ear.cubeList.add(new ModelBox(left_ear, 2, 0, 0.0F, 0.0F, -3.0F, 1, 19, 7, 0.0F, false));

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-6.4F, -7.5F, -4.5F);
		head.addChild(right_ear);
		right_ear.cubeList.add(new ModelBox(right_ear, 48, 0, -1.0F, 0.0F, -3.0F, 1, 19, 7, 0.0F, false));

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -4.5F, -11.5F);
		head.addChild(nose);
		nose.cubeList.add(new ModelBox(nose, 10, 45, -6.5F, -2.0F, -9.0F, 13, 2, 9, 0.0F, false));

		lower_beak = new ModelRenderer(this);
		lower_beak.setRotationPoint(0.0F, 2.5F, -12.5F);
		head.addChild(lower_beak);
		lower_beak.cubeList.add(new ModelBox(lower_beak, 10, 57, -6.5F, -7.0F, -8.0F, 13, 12, 9, 0.0F, false));

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-7.5F, 10.0F, -15.0F);
		bone.addChild(right_front_leg);
		right_front_leg.cubeList.add(new ModelBox(right_front_leg, 32, 87, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));

		right_mid_leg = new ModelRenderer(this);
		right_mid_leg.setRotationPoint(-7.5F, 10.0F, 0.0F);
		bone.addChild(right_mid_leg);
		right_mid_leg.cubeList.add(new ModelBox(right_mid_leg, 32, 105, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));

		right_hind_leg = new ModelRenderer(this);
		right_hind_leg.setRotationPoint(-7.5F, 10.0F, 15.0F);
		bone.addChild(right_hind_leg);
		right_hind_leg.cubeList.add(new ModelBox(right_hind_leg, 32, 123, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(7.5F, 10.0F, -15.0F);
		bone.addChild(left_front_leg);
		left_front_leg.cubeList.add(new ModelBox(left_front_leg, 0, 87, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));

		left_mid_leg = new ModelRenderer(this);
		left_mid_leg.setRotationPoint(7.5F, 10.0F, 0.0F);
		bone.addChild(left_mid_leg);
		left_mid_leg.cubeList.add(new ModelBox(left_mid_leg, 0, 105, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));

		left_hind_leg = new ModelRenderer(this);
		left_hind_leg.setRotationPoint(7.5F, 10.0F, 15.0F);
		bone.addChild(left_hind_leg);
		left_hind_leg.cubeList.add(new ModelBox(left_hind_leg, 0, 123, -3.5F, -1.0F, -4.0F, 7, 10, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}