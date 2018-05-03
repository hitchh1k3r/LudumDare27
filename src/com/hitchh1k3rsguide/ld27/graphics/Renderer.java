package com.hitchh1k3rsguide.ld27.graphics;

import org.lwjgl.opengl.GL11;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class Renderer
{

	private static RenderOptions lastRender = new RenderOptions();

	public static class RenderOptions
	{
		public Color color = new Color(1., 1., 1.);
		public Vec2DPoint pos = new Vec2DPoint(0, 0);
		public Vec2DPoint size = new Vec2DPoint(0, 0);
		public Vec2DPoint origin = new Vec2DPoint(0, 0);
		public Vec2DPoint scale = new Vec2DPoint(1, 1);
		public Vec2DPoint uv1 = new Vec2DPoint(0, 1);
		public Vec2DPoint uv2 = new Vec2DPoint(1, 1);
		public Vec2DPoint uv3 = new Vec2DPoint(1, 0);
		public Vec2DPoint uv4 = new Vec2DPoint(0, 0);
		public double rotation = 0;
		public Enums.alphaType alphaType = Enums.alphaType.NONE;
	};

	public static void prepRender(RenderOptions options)
	{
		GL11.glPushMatrix();
		if(!options.color.equals(lastRender.color))
		{
			options.color.applyColorGL();
		}
		if(!options.origin.equals(lastRender.origin))
		{
			GL11.glRotated(-lastRender.rotation, 0, 0, 1);
			Vec2DPoint offset = new Vec2DPoint(options.origin);
			GL11.glTranslated(offset.getDX(), offset.getDY(), 0);
			offset.subtractVector(lastRender.origin);
			GL11.glRotated(lastRender.rotation, 0, 0, 1);
		}
		if(options.rotation != lastRender.rotation)
		{
			GL11.glRotated(options.rotation-lastRender.rotation, 0, 0, 1);
		}
		if(options.alphaType != lastRender.alphaType)
		{
			if(lastRender.alphaType == Enums.alphaType.ALPHA)
			{
				GL11.glDisable(GL11.GL_ALPHA_TEST);
			}
			if(lastRender.alphaType == Enums.alphaType.BLEND)
			{
				GL11.glDisable(GL11.GL_BLEND);
			}
			if(options.alphaType == Enums.alphaType.ALPHA)
			{
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			}
			if(options.alphaType == Enums.alphaType.BLEND)
			{
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
		}
		lastRender = options;
		lastRender.color = new Color(options.color);
		GL11.glPopMatrix();
	}

	public  static void draw(RenderOptions options)
	{
		prepRender(options);
		GL11.glBegin(GL11.GL_QUADS);
		{
			double x = options.pos.getDX()*options.scale.getDX();
			double y = options.pos.getDY()*options.scale.getDY();
			double width = options.size.getDX()*options.scale.getDX();
			double height = options.size.getDY()*options.scale.getDY();
			GL11.glTexCoord2d(options.uv1.getDX(), options.uv1.getDY());
			GL11.glVertex2d(x, y+height);
			GL11.glTexCoord2d(options.uv2.getDX(), options.uv2.getDY());
			GL11.glVertex2d(x+width, y+height);
			GL11.glTexCoord2d(options.uv3.getDX(), options.uv3.getDY());
			GL11.glVertex2d(x+width, y);
			GL11.glTexCoord2d(options.uv4.getDX(), options.uv4.getDY());
			GL11.glVertex2d(x, y);
		}
		GL11.glEnd();
	}

}
