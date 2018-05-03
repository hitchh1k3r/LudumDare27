package com.hitchh1k3rsguide.ld27.graphics;

import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;
import com.hitchh1k3rsguide.ld27.Enums;

public class AnimatedSprite implements ISprite
{

	protected Vec2DPoint pos;
	protected Vec2DPoint size;
	protected Vec2DPoint origin;
	protected String indexBase;
	protected SpriteSheet sheet;
	protected int frame;
	protected int lastFrame;
	protected double delay;
	protected double currentFrameTime;
	protected Color color;
	protected boolean hasOpactiy;
	protected boolean isFlipped;
	protected double rotation;

	public AnimatedSprite(SpriteSheet sheet, String indexBase, int x, int y, int w, int h, int length, double delay)
	{
		this.sheet = sheet;
		this.indexBase = indexBase;
		this.lastFrame = length;
		this.delay = delay;
		this.currentFrameTime = 0;
		this.frame = 0;
		this.pos = new Vec2DPoint(x, y);
		this.size = new Vec2DPoint(w, h);
		origin = new Vec2DPoint(0, 0);
		this.color = Color.WHITE;
		hasOpactiy = false;
		isFlipped = false;
		rotation = 0;
	}

	public void setAnimation(String indexBase, int length, double delay)
	{
		this.indexBase = indexBase;
		this.lastFrame = length;
		this.delay = delay;
		frame = 0;
	}

	public void update(double frameTime)
	{
		boolean negativeTime = false;
		if(frameTime < 0)
		{
			negativeTime = true;
			frameTime = -frameTime;
		}
		currentFrameTime += frameTime;
		while(currentFrameTime > delay && delay > 0)
		{
			currentFrameTime -= delay;
			
			if(negativeTime)
			{
				--frame;
				if(frame < 0)
					frame = lastFrame-1;
			}
			else
			{
				++frame;
				if(frame >= lastFrame)
					frame = 0;
			}
		}
	}

	@Override
	public void draw()
	{
		sheet.bindTexture();
		Renderer.RenderOptions options = new Renderer.RenderOptions();
		if(hasOpactiy)
			options.alphaType = Enums.alphaType.BLEND;
		else
			options.alphaType = Enums.alphaType.ALPHA;
		options.pos = pos;
		options.origin = origin;
		options.color = color;
		options.size = size;
		options.uv1 = new Vec2DPoint(sheet.getU(indexBase+"."+(frame+1), (isFlipped?1:0)), sheet.getV(indexBase+"."+(frame+1), 0));
		options.uv2 = new Vec2DPoint(sheet.getU(indexBase+"."+(frame+1), (isFlipped?0:1)), sheet.getV(indexBase+"."+(frame+1), 1));
		options.uv3 = new Vec2DPoint(sheet.getU(indexBase+"."+(frame+1), (isFlipped?3:2)), sheet.getV(indexBase+"."+(frame+1), 2));
		options.uv4 = new Vec2DPoint(sheet.getU(indexBase+"."+(frame+1), (isFlipped?2:3)), sheet.getV(indexBase+"."+(frame+1), 3));
		options.rotation = rotation;
		Renderer.draw(options);
	}

	public void setFlip(boolean set)
	{
		isFlipped = set;
	}

	public void setSpriteBase(String indexBase)
	{
		this.indexBase = indexBase;
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

	public void setSize(int w, int h)
	{
		size.setPoint(w, h);
	}

	public void setPos(int x, int y)
	{
		pos.setPoint(x, y);
	}

	public void setOrigin(Vec2DPoint pos2)
	{
		origin = pos2;
	}

	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}

}
