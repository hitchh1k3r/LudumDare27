package com.hitchh1k3rsguide.ld27;

import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.SoundStore;

import com.hitchh1k3rsguide.ld27.panels.CursorPanel;
import com.hitchh1k3rsguide.ld27.panels.PanelManager;
import com.hitchh1k3rsguide.ld27.panels.SplashScreen;
import com.hitchh1k3rsguide.ld27.panels.TitlePanel;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;
import com.hitchh1k3rsguide.ld27.utilities.Timer;

public class Main
{

	public static boolean running = false;
	
	private static PanelManager panelManager = PanelManager.getInstance();
	public static Random rand = new Random();

	protected static void initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslated(0.0125, 0.0125, 0.0);
	}

	public static void main(String[] args)
	{
		running = true;
		try
		{
			Display.setDisplayMode(new DisplayMode(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
			Display.setTitle(GameSettings.GAME_TITLE);
			Display.create();
			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
		} catch (LWJGLException e1)
		{
			e1.printStackTrace();
		}
		initGL();
		SoundPlayer.init();
		panelManager.addPanel(new SplashScreen(), Enums.displayLayers.HUD);
		panelManager.addPanel(new CursorPanel(), Enums.displayLayers.SYSTEM);
		while(running && !Display.isCloseRequested())
		{
			Timer.startFrame();
			Display.update();
			panelManager.handleInputEvents();
			panelManager.update(Timer.getFrameTime());
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			panelManager.draw();
			Display.sync(60);
			SoundPlayer.update();
		}
		AL.destroy();
		Display.destroy();
	}

}