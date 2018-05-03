package com.hitchh1k3rsguide.ld27.panels;


import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.GameSettings;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.SpriteBox;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.TextRenderer;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;

public class TerminalPanel implements IPanel
{

	private SpriteBox modal;
	private SpriteFromSpriteSheet background;
	private int messageIndex;

	public TerminalPanel(int id)
	{
		messageIndex = id;
		modal = new SpriteBox(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, new Color(0., 0., 0., 0.5));
		modal.setOpacity(true);
		background = new SpriteFromSpriteSheet(SpriteSheet.termbg, "background", 0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
	}

	@Override
	public void onAttach()
	{
		((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
		if(messageIndex == 9)
		{
			SoundPlayer.playMusic("mdsitw");
			PanelManager.getInstance().removePanel(GamePanel.class);
		}
	}

	@Override
	public void onDetach()
	{
		if(messageIndex == 0)
			PanelManager.getInstance().addPanel(new GamePanel(), Enums.displayLayers.GAME);
	}

	@Override
	public boolean onKey(int key, boolean isDown)
	{
		if(isDown && messageIndex != 9)
		{
			PanelManager.getInstance().removePanel(TerminalPanel.class);
		}
		return true;
	}

	@Override
	public boolean onMouseMove(int x, int y)
	{
		return true;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown)
	{
		if(isDown && messageIndex != 9)
		{
			PanelManager.getInstance().removePanel(TerminalPanel.class);
		}
		return true;
	}

	@Override
	public void update(double time)
	{
		
	}

	@Override
	public void draw()
	{
		if(messageIndex != 9)
		{
			modal.draw();
			background.draw();
		}
		TextRenderer.renderText(GameSettings.GAME_LOGS[messageIndex], (messageIndex == 1) ? Color.RED : ((messageIndex == 9) ? Color.WHITE : Color.GREEN), 22, 38, 276);
	}

}
