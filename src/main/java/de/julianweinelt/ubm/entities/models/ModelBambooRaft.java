package de.julianweinelt.ubm.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.Entity;

public class ModelBambooRaft extends ModelBase {
	private final ModelRenderer raft;
	private final ModelRenderer paddle_left;
	private final ModelRenderer paddle_right;

	public ModelBambooRaft() {
		textureWidth = 128;
		textureHeight = 64;

		raft = new ModelRenderer(this);
        raft.setRotationPoint(0.0F, 6.0F, -2.0F);
		setRotationAngle(raft, 1.5708F, -1.5708F, 0.0F);
		raft.cubeList.add(new ModelBox(raft, 0, 0, -14.0F, -8.0F, 3.0F, 28, 20, 4, 0.0F, false));
		raft.cubeList.add(new ModelBox(raft, 0, 0, -14.0F, -6.0F, -1.0F, 28, 16, 4, 0.0F, false));

		paddle_left = new ModelRenderer(this);
        paddle_left.setRotationPoint(-11.5F, 16.0F, 1.0F);
		setRotationAngle(paddle_left, -0.8727F, -1.309F, 0.0F);
		paddle_left.cubeList.add(new ModelBox(paddle_left, 0, 24, -1.0F, -1.0F, -5.5F, 2, 2, 18, 0.0F, false));
		paddle_left.cubeList.add(new ModelBox(paddle_left, 0, 24, -1.01F, -4.0F, 7.5F, 1, 6, 7, 0.0F, false));

		paddle_right = new ModelRenderer(this);
        paddle_right.setRotationPoint(7.5F, 16.0F, 0.0F);
		setRotationAngle(paddle_right, -0.8727F, 1.309F, 0.0F);
		paddle_right.cubeList.add(new ModelBox(paddle_right, 40, 24, -2.0F, -1.0F, -5.5F, 2, 2, 18, 0.0F, false));
		paddle_right.cubeList.add(new ModelBox(paddle_right, 40, 24, -0.99F, -4.0F, 7.5F, 1, 6, 7, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		raft.render(f5);
		paddle_left.render(f5);
		paddle_right.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}