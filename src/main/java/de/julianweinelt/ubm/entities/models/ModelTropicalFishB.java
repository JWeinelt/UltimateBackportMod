package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTropicalFishB extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer tailfin;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;

	public ModelTropicalFishB() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(-0.5F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 20, -0.5F, -6.0F, -0.0008F, 2, 6, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 20, 21, 0.5F, 0.0F, -0.0008F, 0, 5, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 20, 10, 0.5F, -11.0F, -0.0008F, 0, 5, 6, 0.0F, false));

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.5F, 0.0F, 6.0F);
		body.addChild(tailfin);
		tailfin.cubeList.add(new ModelBox(tailfin, 21, 16, 0.0F, -6.0008F, 0.0F, 0, 6, 5, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.0F, 0.0F, 1.0F);
		body.addChild(leftFin);
		setRotationAngle(leftFin, 0.0F, -0.6109F, 0.0F);
		leftFin.cubeList.add(new ModelBox(leftFin, 2, 12, 1.5567F, -2.0F, 1.3515F, 2, 2, 0, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(0.0F, 0.0F, 1.0F);
		body.addChild(rightFin);
		setRotationAngle(rightFin, 0.0F, 0.6109F, 0.0F);
		rightFin.cubeList.add(new ModelBox(rightFin, 2, 16, -3.5567F, -2.0F, 1.3515F, 2, 2, 0, 0.0F, false));
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