package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTropicalFishA extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer tailfin;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;

	public ModelTropicalFishA() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(-0.5F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -0.5F, -3.0F, -3.0F, 2, 3, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, -6, 0.5F, -7.0F, -2.9992F, 0, 4, 6, 0.0F, false));

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.5F, 0.0F, 3.0F);
		body.addChild(tailfin);
		tailfin.cubeList.add(new ModelBox(tailfin, 24, -4, 0.0F, -3.0F, 0.0F, 0, 3, 4, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.0F, 0.0F, 1.0F);
		body.addChild(leftFin);
		setRotationAngle(leftFin, 0.0F, -0.6109F, 0.0F);
		leftFin.cubeList.add(new ModelBox(leftFin, 2, 12, -0.164F, -2.0F, -1.1059F, 2, 2, 0, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(0.0F, 0.0F, 1.0F);
		body.addChild(rightFin);
		setRotationAngle(rightFin, 0.0F, 0.6109F, 0.0F);
		rightFin.cubeList.add(new ModelBox(rightFin, 2, 16, -1.836F, -2.0F, -1.1059F, 2, 2, 0, 0.0F, false));
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