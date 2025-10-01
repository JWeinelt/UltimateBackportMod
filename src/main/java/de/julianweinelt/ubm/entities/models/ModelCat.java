package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelCat extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer belly;
	private final ModelRenderer head;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer backLegL;
	private final ModelRenderer backLegR;
	private final ModelRenderer frontLegL;
	private final ModelRenderer frontLegR;

	public ModelCat() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 1.0F);
		

		belly = new ModelRenderer(this);
		belly.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(belly);
		setRotationAngle(belly, 1.5708F, 0.0F, 0.0F);
		belly.cubeList.add(new ModelBox(belly, 20, 0, -2.0F, -8.0F, -3.0F, 4, 16, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -2.0F, -10.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -2.5F, -2.0F, -3.0F, 5, 4, 5, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 24, -1.5F, -0.0156F, -4.0F, 3, 2, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 10, -2.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 6, 10, 1.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F, false));

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, -2.0F, 7.0F);
		body.addChild(tail1);
		setRotationAngle(tail1, 0.7854F, 0.0F, 0.0F);
		tail1.cubeList.add(new ModelBox(tail1, 0, 15, -0.5F, 0.0F, 0.0F, 1, 8, 1, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 8.0F, 0.0F);
		tail1.addChild(tail2);
		setRotationAngle(tail2, 0.7854F, 0.0F, 0.0F);
		tail2.cubeList.add(new ModelBox(tail2, 4, 15, -0.5F, 0.0F, 0.0F, 1, 8, 1, 0.0F, false));

		backLegL = new ModelRenderer(this);
		backLegL.setRotationPoint(1.1F, 1.0F, 6.0F);
		body.addChild(backLegL);
		backLegL.cubeList.add(new ModelBox(backLegL, 8, 13, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		backLegR = new ModelRenderer(this);
		backLegR.setRotationPoint(-1.1F, 1.0F, 6.0F);
		body.addChild(backLegR);
		backLegR.cubeList.add(new ModelBox(backLegR, 8, 13, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		frontLegL = new ModelRenderer(this);
		frontLegL.setRotationPoint(1.2F, -3.0F, -5.0F);
		body.addChild(frontLegL);
		frontLegL.cubeList.add(new ModelBox(frontLegL, 40, 0, -1.0F, -0.2F, -1.0F, 2, 10, 2, 0.0F, false));

		frontLegR = new ModelRenderer(this);
		frontLegR.setRotationPoint(-1.2F, -3.0F, -5.0F);
		body.addChild(frontLegR);
		frontLegR.cubeList.add(new ModelBox(frontLegR, 40, 0, -1.0F, -0.2F, -1.0F, 2, 10, 2, 0.0F, false));
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