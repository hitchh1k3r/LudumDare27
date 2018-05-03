package com.hitchh1k3rsguide.ld27.graphics;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class SpriteFromSpriteSheet implements ISprite
{

	private int x, y;
	private Vec2DPoint size;
	private String index;
	private SpriteSheet sheet;
	Color color;
	boolean hasOpactiy;

	public SpriteFromSpriteSheet(SpriteSheet sheet, String index, int x, int y, int w, int h)
	{
		this.sheet = sheet;
		this.index = index;
		this.x = x;
		this.y = y;
		this.size = new Vec2DPoint(w, h);
		this.color = new Color(1., 1., 1.);
		hasOpactiy = false;
	}

	@Override
	public void draw()
	{
		drawAt(x, y);
	}

	public void setSprite(String index)
	{
		this.index = index;
	}

	public void setSpriteSheet(SpriteSheet sheet)
	{
		this.sheet = sheet;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public void setOpacity(boolean onOff)
	{
		hasOpactiy = onOff;
	}

	public void drawAt(int x, int y)
	{
		sheet.bindTexture();
		Renderer.RenderOptions options = new Renderer.RenderOptions();
		if(hasOpactiy)
			options.alphaType = Enums.alphaType.BLEND;
		else
			options.alphaType = Enums.alphaType.ALPHA;
		options.pos = new Vec2DPoint(x, y);
		options.color = color;
		options.size = size;
		options.uv1 = new Vec2DPoint(sheet.getU(index, 0), sheet.getV(index, 0));
		options.uv2 = new Vec2DPoint(sheet.getU(index, 1), sheet.getV(index, 1));
		options.uv3 = new Vec2DPoint(sheet.getU(index, 2), sheet.getV(index, 2));
		options.uv4 = new Vec2DPoint(sheet.getU(index, 3), sheet.getV(index, 3));
		Renderer.draw(options);
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

}
