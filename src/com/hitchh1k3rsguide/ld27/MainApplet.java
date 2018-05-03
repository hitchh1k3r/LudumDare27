package com.hitchh1k3rsguide.ld27;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.SoundStore;

import com.hitchh1k3rsguide.ld27.panels.CursorPanel;
import com.hitchh1k3rsguide.ld27.panels.FocusNag;
import com.hitchh1k3rsguide.ld27.panels.PanelManager;
import com.hitchh1k3rsguide.ld27.panels.SplashScreen;
import com.hitchh1k3rsguide.ld27.panels.TitlePanel;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;
import com.hitchh1k3rsguide.ld27.utilities.Timer;

public class MainApplet extends Applet {

	private static final long serialVersionUID = 1L;
	volatile Canvas display_parent;
	Thread gameThread;
	public static boolean running = false;
	
	private PanelManager panelManager = PanelManager.getInstance();
	Random rand = new Random();

	public void startLWJGL()
	{
		gameThread = new Thread()
		{
			public void run()
			{
				running = true;
				try
				{
					Display.setParent(display_parent);
					Display.create();
					initGL();
				}
				catch (LWJGLException e)
				{
					Display.destroy();
					e.printStackTrace();
				}
				gameLoop();
			}
		};
		gameThread.start();
	}

	public void stopLWJGL()
	{
		running = false;
		try
		{
			gameThread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void init()
	{
		setLayout(new BorderLayout());
		try
		{
			display_parent = new Canvas()
			{
				private static final long serialVersionUID = 1L;
				public final void addNotify()
				{
					super.addNotify();
					startLWJGL();
				}
				public final void removeNotify()
				{
					stopLWJGL();
					super.removeNotify();
				}
			};
			display_parent.setSize(getWidth(), getHeight());
			add(display_parent);
			display_parent.setFocusable(true);
			display_parent.requestFocus();
			display_parent.setIgnoreRepaint(true);
			setVisible(true);
		}
		catch (Exception e)
		{
			System.err.println(e);
			throw new RuntimeException("Unable to create display");
		}
	}

	public void destroy()
	{
		remove(display_parent);
		super.destroy();
	}

	protected void initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glOrtho(0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslated(0.0125, 0.0125, 0.0);
	}

	public void gameLoop()
	{
		try {
			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		SoundPlayer.init();
		panelManager.addPanel(new SplashScreen(), Enums.displayLayers.HUD);
		panelManager.addPanel(new FocusNag(), Enums.displayLayers.SYSTEM);
		panelManager.addPanel(new CursorPanel(), Enums.displayLayers.SYSTEM);
		while(running)
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