package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;


public class ModelCamel extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer saddle;
	private final ModelRenderer tail;
	private final ModelRenderer tail_r1;
	private final ModelRenderer head;
	private final ModelRenderer bridle;
	private final ModelRenderer left_ear;
	private final ModelRenderer right_ear;
	private final ModelRenderer reins;
	private final ModelRenderer hump;
	private final ModelRenderer right_front_leg;
	private final ModelRenderer left_front_leg;
	private final ModelRenderer left_hind_leg;
	private final ModelRenderer right_hind_leg;

	public ModelCamel() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.5F, -20.0F, 9.5F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 25, -8.0F, -12.0F, -23.5F, 15, 12, 27, 0.0F, false));

		saddle = new ModelRenderer(this);
		saddle.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(saddle);
		saddle.cubeList.add(new ModelBox(saddle, 74, 64, -5.0F, -17.0F, -15.5F, 9, 5, 11, 0.1F, false));
		saddle.cubeList.add(new ModelBox(saddle, 92, 114, -4.0F, -20.0F, -15.5F, 7, 3, 11, 0.1F, false));
		saddle.cubeList.add(new ModelBox(saddle, 0, 89, -8.0F, -12.0F, -23.5F, 15, 12, 27, 0.1F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-0.5F, -9.0F, 3.5F);
		body.addChild(tail);
		

		tail_r1 = new ModelRenderer(this);
		tail_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail.addChild(tail_r1);
		setRotationAngle(tail_r1, 0.0F, 3.1416F, 0.0F);
		tail_r1.cubeList.add(new ModelBox(tail_r1, 122, 0, -1.5F, 0.0F, 0.0F, 3, 14, 0, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -5.0F, -19.5F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 60, 24, -4.0F, -5.0F, -15.0F, 7, 8, 19, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 21, 0, -4.0F, -19.0F, -15.0F, 7, 14, 7, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 50, 0, -3.0F, -19.0F, -21.0F, 5, 5, 6, 0.0F, false));

		bridle = new ModelRenderer(this);
		bridle.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(bridle);
		bridle.cubeList.add(new ModelBox(bridle, 60, 87, -4.0F, -5.0F, -15.0F, 7, 8, 19, 0.1F, false));
		bridle.cubeList.add(new ModelBox(bridle, 21, 64, -4.0F, -19.0F, -15.0F, 7, 14, 7, 0.1F, false));
		bridle.cubeList.add(new ModelBox(bridle, 50, 64, -3.0F, -19.0F, -21.1F, 5, 5, 6, 0.1F, false));
		bridle.cubeList.add(new ModelBox(bridle, 74, 70, 2.0F, -17.0F, -18.0F, 1, 2, 2, 0.0F, false));
		bridle.cubeList.add(new ModelBox(bridle, 74, 70, -4.0F, -17.0F, -18.0F, 1, 2, 2, 0.0F, true));

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(2.5F, -18.0F, -9.5F);
		head.addChild(left_ear);
		left_ear.cubeList.add(new ModelBox(left_ear, 45, 0, 0.0F, -0.5F, -1.0F, 3, 1, 2, 0.0F, false));

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-3.5F, -18.0F, -9.5F);
		head.addChild(right_ear);
		right_ear.cubeList.add(new ModelBox(right_ear, 67, 0, -3.0F, -0.5F, -1.0F, 3, 1, 2, 0.0F, false));

		reins = new ModelRenderer(this);
		reins.setRotationPoint(3.2F, -16.0F, -17.0F);
		head.addChild(reins);
		reins.cubeList.add(new ModelBox(reins, 98, 42, 0.0F, 0.0F, 0.0F, 0, 7, 15, 0.0F, false));
		reins.cubeList.add(new ModelBox(reins, 84, 57, -7.4F, 0.0F, 15.0F, 7, 7, 0, 0.0F, false));
		reins.cubeList.add(new ModelBox(reins, 98, 42, -7.4F, 0.0F, 0.0F, 0, 7, 15, 0.0F, false));

		hump = new ModelRenderer(this);
		hump.setRotationPoint(0.0F, -12.0F, -9.5F);
		body.addChild(hump);
		hump.cubeList.add(new ModelBox(hump, 74, 0, -5.0F, -5.0F, -6.0F, 9, 5, 11, 0.0F, false));

		right_front_leg = new ModelRenderer(this);
		right_front_leg.setRotationPoint(-4.9F, -23.0F, -10.5F);
		root.addChild(right_front_leg);
		right_front_leg.cubeList.add(new ModelBox(right_front_leg, 0, 26, -2.5F, 2.0F, -2.5F, 5, 21, 5, 0.0F, false));

		left_front_leg = new ModelRenderer(this);
		left_front_leg.setRotationPoint(4.9F, -23.0F, -10.5F);
		root.addChild(left_front_leg);
		left_front_leg.cubeList.add(new ModelBox(left_front_leg, 0, 0, -2.5F, 2.0F, -2.5F, 5, 21, 5, 0.0F, false));

		left_hind_leg = new ModelRenderer(this);
		left_hind_leg.setRotationPoint(4.9F, -23.0F, 9.5F);
		root.addChild(left_hind_leg);
		left_hind_leg.cubeList.add(new ModelBox(left_hind_leg, 58, 16, -2.5F, 2.0F, -2.5F, 5, 21, 5, 0.0F, false));

		right_hind_leg = new ModelRenderer(this);
		right_hind_leg.setRotationPoint(-4.9F, -23.0F, 9.5F);
		root.addChild(right_hind_leg);
		right_hind_leg.cubeList.add(new ModelBox(right_hind_leg, 94, 16, -2.5F, 2.0F, -2.5F, 5, 21, 5, 0.0F, false));
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