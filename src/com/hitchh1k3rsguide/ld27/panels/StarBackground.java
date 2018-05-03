package com.hitchh1k3rsguide.ld27.panels;

import com.hitchh1k3rsguide.ld27.GameSettings;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.SpriteBox;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;


public class StarBackground implements IPanel
{

	private SpriteBox starSprite;
	private double[] paralax;
	private Vec2DPoint[] starPoses;
	private boolean moving;
	public static final int NUM_STARS = 120;

	public StarBackground(boolean shiftingBG)
	{
		this.moving = shiftingBG;
		this.moving = true;
		paralax = new double[NUM_STARS];
		starPoses = new Vec2DPoint[NUM_STARS];
		starSprite = new SpriteBox(0, 0, 1, 1, Color.WHITE);
		for(int i=0; i<NUM_STARS; ++i)
		{
			paralax[i] = ((Math.random()*15)+1);
			starPoses[i] = new Vec2DPoint(GameSettings.GAME_WIDTH*Math.random(), GameSettings.GAME_HEIGHT*Math.random());
		}
	}

	public void setMoving(boolean state)
	{
		moving = state;
	}

	@Override
	public boolean onKey(int key, boolean isDown)
	{
		return false;
	}

	@Override
	public boolean onMouseMove(int x, int y)
	{
		return false;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown)
	{
		return false;
	}

	@Override
	public void update(double time)
	{
		if(moving)
		{
			for(int i=0; i<NUM_STARS; ++i)
			{
				double x = starPoses[i].getDX();
				double y = starPoses[i].getDY();
				x -= paralax[i] * time * 3;
				y -= paralax[i] * time * 0.075;
				if(x < -2)
				{
					x = GameSettings.GAME_WIDTH;
					y = Math.random()*GameSettings.GAME_HEIGHT;
				}
				if(y > GameSettings.GAME_HEIGHT)
				{
					y = -2;
					x = Math.random()*GameSettings.GAME_WIDTH;
				}
				if(x > GameSettings.GAME_WIDTH)
				{
					x = -2;
					y = Math.random()*GameSettings.GAME_HEIGHT;
				}
				if(y < -2)
				{
					y = GameSettings.GAME_HEIGHT;
					x = Math.random()*GameSettings.GAME_WIDTH;
				}
				starPoses[i].setPoint(x, y);
			}
		}
	}

	@Override
	public void draw()
	{
		for(int i=0; i<NUM_STARS; ++i)
		{
			double brightness = (paralax[i]/20)+0.2;
			starSprite.setColor(new Color(brightness, brightness, brightness));
			int size = 1;
			if(paralax[i] > 8)
				size = 2;
			starSprite.setSize(size, size);
			starSprite.drawAt(starPoses[i].getDX(), starPoses[i].getDY());
		}
	}

	@Override
	public void onAttach()
	{
		
	}

	@Override
	public void onDetach()
	{
		
	}

}
