package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurtle extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer eggbelly;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	public ModelTurtle() {
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, -10.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 7, 37, -9.5F, 3.0F, -10.0F, 19, 20, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 31, 1, -5.5F, 3.0F, -13.0F, 11, 18, 3, 0.0F, false));

		eggbelly = new ModelRenderer(this);
		eggbelly.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(eggbelly);
		eggbelly.cubeList.add(new ModelBox(eggbelly, 70, 33, -4.5F, 3.0F, -14.0F, 9, 18, 1, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 19.0F, -10.0F);
		head.cubeList.add(new ModelBox(head, 3, 0, -3.0F, -1.0F, -3.0F, 6, 5, 6, 0.0F, false));

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(-3.5F, 22.0F, 11.0F);
		leg0.cubeList.add(new ModelBox(leg0, 1, 23, -2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(3.5F, 22.0F, 11.0F);
		leg1.cubeList.add(new ModelBox(leg1, 1, 12, -2.0F, 0.0F, 0.0F, 4, 1, 10, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-5.0F, 21.0F, -4.0F);
		setRotationAngle(leg2, 0.0F, 0.1745F, 0.0F);
		leg2.cubeList.add(new ModelBox(leg2, 27, 30, -13.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(5.0F, 21.0F, -4.0F);
		setRotationAngle(leg3, 0.0F, -0.1745F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 27, 24, 0.0F, 0.0F, -2.0F, 13, 1, 5, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		head.render(f5);
		leg0.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}