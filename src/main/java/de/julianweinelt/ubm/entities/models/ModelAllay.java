package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelAllay extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightItem;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer left_wing;
	private final ModelRenderer right_wing;

	public ModelAllay() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		root.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -2.5F, -5.01F, -2.5F, 5, 5, 5, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -4.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 10, -1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 16, -1.5F, 0.0F, -1.0F, 3, 5, 2, -0.2F, false));

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(0.0F, 5.0F, -2.0F);
		body.addChild(rightItem);
		setRotationAngle(rightItem, -1.3963F, 0.0F, 0.0F);
		

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-1.75F, 0.5F, 0.0F);
		body.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 23, 0, -0.75F, -0.5F, -1.0F, 1, 4, 2, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(1.75F, 0.5F, 0.0F);
		body.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 23, 6, -0.25F, -0.5F, -1.0F, 1, 4, 2, 0.0F, false));

		left_wing = new ModelRenderer(this);
		left_wing.setRotationPoint(0.5F, 1.0F, 1.0F);
		body.addChild(left_wing);
		left_wing.cubeList.add(new ModelBox(left_wing, 16, 14, 0.0F, 0.0F, 0.0F, 0, 5, 8, 0.0F, true));

		right_wing = new ModelRenderer(this);
		right_wing.setRotationPoint(-0.5F, 1.0F, 1.0F);
		body.addChild(right_wing);
		right_wing.cubeList.add(new ModelBox(right_wing, 16, 14, 0.0F, 0.0F, 0.0F, 0, 5, 8, 0.0F, false));
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