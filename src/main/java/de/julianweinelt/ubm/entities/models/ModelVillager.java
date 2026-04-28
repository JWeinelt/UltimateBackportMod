package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVillager extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer helmet;
	private final ModelRenderer brim;
	private final ModelRenderer nose;
	private final ModelRenderer arms;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public ModelVillager() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 20, -4.0F, -24.0F, -3.0F, 8, 12, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 38, -4.0F, -24.0F, -3.0F, 8, 20, 6, 0.5F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false));

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.cubeList.add(new ModelBox(helmet, 32, 0, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F, false));

		brim = new ModelRenderer(this);
		brim.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(brim);
		setRotationAngle(brim, -1.5708F, 0.0F, 0.0F);
		brim.cubeList.add(new ModelBox(brim, 30, 47, -8.0F, -8.0F, -6.0F, 16, 16, 1, 0.1F, false));

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		head.addChild(nose);
		nose.cubeList.add(new ModelBox(nose, 24, 0, -1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F, false));

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, -22.0F, 0.0F);
		body.addChild(arms);
		setRotationAngle(arms, -0.7854F, 0.0F, 0.0F);
		arms.cubeList.add(new ModelBox(arms, 40, 38, -4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F, false));
		arms.cubeList.add(new ModelBox(arms, 44, 22, -8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));
		arms.cubeList.add(new ModelBox(arms, 44, 22, 4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, true));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.0F, -12.0F, 0.0F);
		body.addChild(RightLeg);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.0F, -12.0F, 0.0F);
		body.addChild(LeftLeg);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));
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