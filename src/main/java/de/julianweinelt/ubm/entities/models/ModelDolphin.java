package de.julianweinelt.ubm.entities.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDolphin extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer tail;
	private final ModelRenderer tail_fin;
	private final ModelRenderer back_fin;
	private final ModelRenderer left_fin;
	private final ModelRenderer right_fin;

	public ModelDolphin() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, -3.0F);
		body.cubeList.add(new ModelBox(body, 22, 0, -4.0F, -7.0F, 0.0F, 8, 7, 13, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -7.0F, -6.0F, 8, 7, 6, 0.0F, false));

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, 0.0F, -10.0F);
		head.addChild(nose);
		nose.cubeList.add(new ModelBox(nose, 0, 13, -1.0F, -2.0F, 0.0F, 2, 2, 4, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -2.5F, 14.0F);
		body.addChild(tail);
		setRotationAngle(tail, -0.0873F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 0, 19, -2.0F, -2.5F, -1.0F, 4, 5, 11, 0.0F, false));

		tail_fin = new ModelRenderer(this);
		tail_fin.setRotationPoint(0.0F, 0.0F, 9.0F);
		tail.addChild(tail_fin);
		setRotationAngle(tail_fin, -0.1396F, 0.0F, 0.0F);
		tail_fin.cubeList.add(new ModelBox(tail_fin, 19, 20, -5.0F, -0.5F, -1.0F, 10, 1, 6, 0.0F, false));

		back_fin = new ModelRenderer(this);
		back_fin.setRotationPoint(0.0F, -7.0F, 5.0F);
		body.addChild(back_fin);
		setRotationAngle(back_fin, 1.0472F, 0.0F, 0.0F);
		back_fin.cubeList.add(new ModelBox(back_fin, 51, 0, -0.5F, -0.75F, -0.5F, 1, 4, 5, 0.0F, false));

		left_fin = new ModelRenderer(this);
		left_fin.setRotationPoint(3.0F, -2.0F, 5.0F);
		body.addChild(left_fin);
		setRotationAngle(left_fin, 0.9599F, 0.0F, 1.8675F);
		left_fin.cubeList.add(new ModelBox(left_fin, 48, 20, 0.0F, -4.0F, -1.5F, 1, 4, 7, 0.0F, false));

		right_fin = new ModelRenderer(this);
		right_fin.setRotationPoint(-3.0F, -2.0F, 5.0F);
		body.addChild(right_fin);
		setRotationAngle(right_fin, 0.9599F, 0.0F, -1.8675F);
		right_fin.cubeList.add(new ModelBox(right_fin, 48, 20, -1.0F, -4.0F, -1.5F, 1, 4, 7, 0.0F, true));
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