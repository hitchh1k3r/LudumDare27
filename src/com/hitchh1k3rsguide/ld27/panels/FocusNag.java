package com.hitchh1k3rsguide.ld27.panels;

import org.lwjgl.opengl.Display;

import com.hitchh1k3rsguide.ld27.GameSettings;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.SpriteBox;
import com.hitchh1k3rsguide.ld27.graphics.TextRenderer;
import com.hitchh1k3rsguide.ld27.utilities.Timer;

public class FocusNag implements IPanel
{

	private SpriteBox bg;
	private boolean isShowing;
	private double delay;
	private double timer;
	private boolean inOut;
	private double oldScale;
	private Color textColor;

	public FocusNag()
	{
		bg = new SpriteBox(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, new Color(0., 0., 0., 0.5));
		bg.setOpacity(true);
		isShowing = false;
		textColor = Color.YELLOW;
		delay = 0.25;
		timer = 0;
		inOut = false;
		oldScale = -1;
	}

	@Override
	public boolean onKey(int key, boolean isDown)
	{
		return false;
	}

	@Override
	public boolean onMouseMove(int x, int y)
	{
		if(isShowing)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown)
	{
		if(isShowing)
		{
			return true;
		}
		return false;
	}

	@Override
	public void update(double time)
	{
		time = Timer.getFrameTime();
		if(isShowing == Display.isActive())
		{
			delay = 0.25;
			isShowing = !isShowing;
//			if(oldScale >= 0)
//			{
//				PanelManager.getInstance().setTimeScale(oldScale);
//				oldScale = -1;
//			}
		}
		if(isShowing && delay > 0)
		{
			delay -= time;
//			oldScale = PanelManager.getInstance().getTimeScale();
//			if(delay <= 0)
//				PanelManager.getInstance().setTimeScale(0);
		}
		if(isShowing)
		{
			timer += time;
			if(timer > 0.125)
			{
				timer = 0;
				inOut = !inOut;
				if(inOut)
				{
					textColor = Color.RED;
				}
				else
				{
					textColor = Color.YELLOW;
				}
			}
		}
	}

	@Override
	public void draw()
	{
		if(isShowing)
		{
			if(delay <= 0)
			{
				bg.draw();
				TextRenderer.renderText("click here!", textColor, 116, 116, 0);
			}
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
