package com.hitchh1k3rsguide.ld27.panels;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;

public class SplashScreen implements IPanel
{

	private SpriteFromSpriteSheet background;
	private Color backgroundColor;
	private double tweener;
	private double delay;

	public SplashScreen()
	{
		super();
		tweener = 0;
		backgroundColor = new Color(Color.WHITE);
		background = new SpriteFromSpriteSheet(SpriteSheet.spalshScreen, "background", 0, 0, 320, 240);
		background.setOpacity(true);
		background.setColor(backgroundColor);
	}

	@Override
	public void onAttach()
	{
		PanelManager.getInstance().addPanel(new StarBackground(false), Enums.displayLayers.BACKGROUND);
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
		if(delay < 2)
		{
			delay += time;
			if(delay >= 2)
			{
				PanelManager.getInstance().addPanel(new TerminalPanel(0), Enums.displayLayers.GAME);
				SoundPlayer.playMusic("theme");
			}
		}
		else
		{
			if(tweener == 0)
				tweener = 0.0001;
			else
				tweener += time;
			if(tweener < 1)
			{
				backgroundColor.setAlpha(1.-tweener);
			}
			else
			{
				PanelManager.getInstance().removePanel(SplashScreen.class);
				((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
			}
		}
	}

	@Override
	public void draw()
	{
		background.draw();
	}

}
