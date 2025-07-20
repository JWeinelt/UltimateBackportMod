package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFox extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer tail;

	public ModelFox() {
		textureWidth = 48;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 24, 15, -3.0F, -3.0F, -3.0F, 6, 11, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, -3.0F);
		head.cubeList.add(new ModelBox(head, 1, 5, -4.0F, -2.0F, -6.0F, 8, 6, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 1, -4.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 15, 1, 2.0F, -4.0F, -5.0F, 2, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 6, 18, -2.0F, 2.0F, -9.0F, 4, 2, 3, 0.0F, false));

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(-3.0F, 18.0F, 6.0F);
		leg0.cubeList.add(new ModelBox(leg0, 13, 24, -0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(1.0F, 18.0F, 6.0F);
		leg1.cubeList.add(new ModelBox(leg1, 4, 24, 0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-3.0F, 18.0F, -1.0F);
		leg2.cubeList.add(new ModelBox(leg2, 13, 24, -0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(1.0F, 18.0F, -1.0F);
		leg3.cubeList.add(new ModelBox(leg3, 4, 24, 0.005F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 16.0F, 7.0F);
		setRotationAngle(tail, 1.5708F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 30, 0, -2.0F, 1.0F, -2.25F, 4, 9, 5, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		head.render(f5);
		leg0.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		tail.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}