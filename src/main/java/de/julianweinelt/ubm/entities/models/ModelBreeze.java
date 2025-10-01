package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelBreeze extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer rods;
	private final ModelRenderer rods_r1;
	private final ModelRenderer rods_r2;
	private final ModelRenderer rods_r3;
	private final ModelRenderer head;
	private final ModelRenderer eyes;

	public ModelBreeze() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		rods = new ModelRenderer(this);
		rods.setRotationPoint(0.0F, -16.0F, 0.0F);
		body.addChild(rods);
		

		rods_r1 = new ModelRenderer(this);
		rods_r1.setRotationPoint(2.5981F, -3.0F, 1.5F);
		rods.addChild(rods_r1);
		setRotationAngle(rods_r1, -2.7489F, -1.0472F, 3.1416F);
		rods_r1.cubeList.add(new ModelBox(rods_r1, 0, 17, -1.0F, 0.0F, -3.0F, 2, 8, 2, 0.0F, false));

		rods_r2 = new ModelRenderer(this);
		rods_r2.setRotationPoint(-2.5981F, -3.0F, 1.5F);
		rods.addChild(rods_r2);
		setRotationAngle(rods_r2, -2.7489F, 1.0472F, 3.1416F);
		rods_r2.cubeList.add(new ModelBox(rods_r2, 0, 17, -1.0F, 0.0F, -3.0F, 2, 8, 2, 0.0F, false));

		rods_r3 = new ModelRenderer(this);
		rods_r3.setRotationPoint(0.0F, -3.0F, -3.0F);
		rods.addChild(rods_r3);
		setRotationAngle(rods_r3, 0.3927F, 0.0F, 0.0F);
		rods_r3.cubeList.add(new ModelBox(rods_r3, 0, 17, -1.0F, 0.0F, -3.0F, 2, 8, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -20.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(eyes);
		eyes.cubeList.add(new ModelBox(eyes, 4, 24, -5.0F, -5.0F, -4.2F, 10, 3, 4, 0.0F, false));
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
}