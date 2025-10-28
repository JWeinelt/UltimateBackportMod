package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelRavager extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer mouth;
	private final ModelRenderer horns;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	public ModelRavager() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 55, -7.0F, -7.0F, -4.0F, 14, 16, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 91, -6.0F, 9.0F, -4.0F, 12, 13, 18, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 4.0F, -20.0F);
		neck.cubeList.add(new ModelBox(neck, 68, 73, -5.0F, -11.0F, 10.0F, 10, 10, 18, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -8.0F, 10.0F);
		neck.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -8.0F, -6.0F, -14.0F, 16, 20, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, -2.0F, 8.0F, -18.0F, 4, 8, 4, 0.0F, false));

		mouth = new ModelRenderer(this);
		mouth.setRotationPoint(0.0F, 13.0F, 0.0F);
		head.addChild(mouth);
		mouth.cubeList.add(new ModelBox(mouth, 0, 36, -8.0F, -1.0F, -14.0F, 16, 3, 16, 0.0F, false));

		horns = new ModelRenderer(this);
		horns.setRotationPoint(-5.0F, 1.0F, -9.0F);
		head.addChild(horns);
		setRotationAngle(horns, 1.0472F, 0.0F, 0.0F);
		horns.cubeList.add(new ModelBox(horns, 74, 55, -5.0F, -14.0F, -1.0F, 2, 14, 4, 0.0F, false));
		horns.cubeList.add(new ModelBox(horns, 74, 55, 13.0F, -14.0F, -1.0F, 2, 14, 4, 0.0F, false));

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(-12.0F, -6.0F, 22.0F);
		leg0.cubeList.add(new ModelBox(leg0, 96, 0, 0.0F, -7.0F, -5.0F, 8, 37, 8, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(4.0F, -6.0F, 22.0F);
		leg1.cubeList.add(new ModelBox(leg1, 96, 0, 0.0F, -7.0F, -5.0F, 8, 37, 8, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-4.0F, -2.0F, -4.0F);
		leg2.cubeList.add(new ModelBox(leg2, 64, 0, -8.0F, -11.0F, -4.0F, 8, 37, 8, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-4.0F, -2.0F, -4.0F);
		leg3.cubeList.add(new ModelBox(leg3, 64, 0, 8.0F, -11.0F, -4.0F, 8, 37, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		neck.render(f5);
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