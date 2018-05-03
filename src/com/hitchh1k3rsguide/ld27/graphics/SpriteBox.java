package com.hitchh1k3rsguide.ld27.graphics;

import org.lwjgl.opengl.GL11;

import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;
import com.hitchh1k3rsguide.ld27.Enums;

public class SpriteBox implements ISprite
{

	private int x, y;
	private Vec2DPoint size;
	Color colorTL;
	Color colorTR;
	Color colorBL;
	Color colorBR;
	boolean hasOpactiy;

	public SpriteBox(int x, int y, int w, int h, Color color)
	{
		this.x = x;
		this.y = y;
		this.size = new Vec2DPoint(w, h);
		this.colorTL = color;
		this.colorTR = color;
		this.colorBL = color;
		this.colorBR = color;
		hasOpactiy = false;
	}

	@Override
	public void draw()
	{
		drawAt(x, y);
	}

	public void setColor(Color color)
	{
		this.colorTL = color;
		this.colorTR = color;
		this.colorBL = color;
		this.colorBR = color;
	}

	public void setColor(Color color, int corner)
	{
		if(corner == 0)
			this.colorTL = color;
		else if(corner == 1)
			this.colorTR = color;
		else if(corner == 2)
			this.colorBL = color;
		else if(corner == 3)
			this.colorBR = color;
	}

	public void setOpacity(boolean onOff)
	{
		hasOpactiy = onOff;
	}

	public void drawAt(double x, double y)
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Renderer.RenderOptions options = new Renderer.RenderOptions();
		if(hasOpactiy)
			options.alphaType = Enums.alphaType.BLEND;
		else
			options.alphaType = Enums.alphaType.ALPHA;
		Renderer.prepRender(options);
		GL11.glBegin(GL11.GL_QUADS);
		{
			double width = size.getDX();
			double height = size.getDY();
			colorBL.applyColorGL();
			GL11.glVertex2d(x, y+height);
			colorBR.applyColorGL();
			GL11.glVertex2d(x+width, y+height);
			colorTR.applyColorGL();
			GL11.glVertex2d(x+width, y);
			colorTL.applyColorGL();
			GL11.glVertex2d(x, y);
		}
		GL11.glEnd();
		new Color(1., 1., 1.).applyColorGL();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void setSize(int w, int h)
	{
		size.setPoint(w, h);
	}

	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2DPoint getPos()
	{
		return new Vec2DPoint(x, y);
	}

}
