package com.hitchh1k3rsguide.ld27.panels;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;

public class TitlePanel implements IPanel
{

	private SpriteFromSpriteSheet button;
	private Color fadeUp;
	private double tweener;
	private boolean hovering;

	public TitlePanel()
	{
		button = new SpriteFromSpriteSheet(SpriteSheet.spalshScreen, "play", 144, 104, 32, 32);
		fadeUp = new Color(1., 1., 1., 0.);
		button.setColor(fadeUp);
		button.setOpacity(true);
		tweener = 0;
		hovering = false;
	}

	@Override
	public void onAttach()
	{
		SoundPlayer.playMusic("title");
	}

	@Override
	public void onDetach()
	{
		
	}

	@Override
	public boolean onKey(int key, boolean isDown)
	{
		return false;
	}

	@Override
	public boolean onMouseMove(int x, int y)
	{
		if(!hovering && x > 114 && x < 144+32 && y > 104 && y < 104+32)
		{
			hovering = true;
			fadeUp.setAlpha(1);
			tweener = 1;
		}
		else if(hovering && !(x > 114 && x < 144+32 && y > 104 && y < 104+32))
		{
			hovering = false;
			fadeUp.setAlpha(0.5);
		}
		return false;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown)
	{
		if(button == 1)
		{
			SoundPlayer.playSound("explode");
		}
		if(hovering && isDown && button == 0)
		{
			((StarBackground)PanelManager.getInstance().getPanel(StarBackground.class)).setMoving(true);
			PanelManager.getInstance().removePanel(TitlePanel.class);
		}
		return false;
	}

	@Override
	public void update(double time)
	{
		if(tweener < 1)
		{
			tweener += time*5;
			if(tweener > 1)
				tweener = 1;
			fadeUp.setAlpha(tweener/2);
		}
	}

	@Override
	public void draw()
	{
		button.draw();
	}

}
