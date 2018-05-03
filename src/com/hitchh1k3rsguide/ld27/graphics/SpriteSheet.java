	package com.hitchh1k3rsguide.ld27.graphics;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SpriteSheet
{

	public static class Block
	{
		public final String name;
		public final int x, y, width, height;
		public Block(String name, int x, int y, int width, int height)
		{
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}

	public static SpriteSheet spalshScreen = new SpriteSheet("assets/Graphics/SplashScreen.png", 128, 128,
			new Block("background", 0, 0, 128, 80),
			new Block("pause", 0, 80, 32, 32),
			new Block("play", 32, 80, 32, 32)
		);
	public static SpriteSheet font = new SpriteSheet("assets/Graphics/font.png", 128, 128,
			new Block("char.a", 0, 0, 8, 8),
			new Block("char.b", 8, 0, 8, 8),
			new Block("char.c", 16, 0, 8, 8),
			new Block("char.d", 24, 0, 8, 8),
			new Block("char.e", 32, 0, 8, 8),
			new Block("char.f", 40, 0, 8, 8),
			new Block("char.g", 48, 0, 8, 8),
			new Block("char.h", 56, 0, 8, 8),
			new Block("char.i", 64, 0, 8, 8),
			new Block("char.j", 72, 0, 8, 8),
			new Block("char.k", 80, 0, 8, 8),
			new Block("char.l", 88, 0, 8, 8),
			new Block("char.m", 96, 0, 8, 8),
			new Block("char.n", 104, 0, 8, 8),
			new Block("char.o", 112, 0, 8, 8),
			new Block("char.p", 120, 0, 8, 8),
			new Block("char.q", 0, 8, 8, 8),
			new Block("char.r", 8, 8, 8, 8),
			new Block("char.s", 16, 8, 8, 8),
			new Block("char.t", 24, 8, 8, 8),
			new Block("char.u", 32, 8, 8, 8),
			new Block("char.v", 40, 8, 8, 8),
			new Block("char.w", 48, 8, 8, 8),
			new Block("char.x", 56, 8, 8, 8),
			new Block("char.y", 64, 8, 8, 8),
			new Block("char.z", 72, 8, 8, 8),
			new Block("char..", 80, 8, 8, 8),
			new Block("char.!", 88, 8, 8, 8),
			new Block("char.?", 96, 8, 8, 8),
			new Block("char.,", 104, 8, 8, 8),
			new Block("char.:", 112, 8, 8, 8),
			new Block("char.;", 120, 8, 8, 8),
			new Block("char.1", 0, 16, 8, 8),
			new Block("char.2", 8, 16, 8, 8),
			new Block("char.3", 16, 16, 8, 8),
			new Block("char.4", 24, 16, 8, 8),
			new Block("char.5", 32, 16, 8, 8),
			new Block("char.6", 40, 16, 8, 8),
			new Block("char.7", 48, 16, 8, 8),
			new Block("char.8", 56, 16, 8, 8),
			new Block("char.9", 64, 16, 8, 8),
			new Block("char.0", 72, 16, 8, 8),
			new Block("char.+", 80, 16, 8, 8),
			new Block("char.-", 88, 16, 8, 8),
			new Block("char.*", 96, 16, 8, 8),
			new Block("char./", 104, 16, 8, 8),
			new Block("char.(", 112, 16, 8, 8),
			new Block("char.)", 120, 16, 8, 8),
			new Block("char.'", 0, 24, 8, 8),
			new Block("char.#", 8, 24, 8, 8)
		);
	public static SpriteSheet roomParts = new SpriteSheet("assets/Graphics/Rooms.png", 2048, 128,
			new Block("roomOverlay", 0, 0, 160, 120),
			new Block("warpCore", 160, 0, 160, 120),
			new Block("engineering", 320, 0, 160, 120),
			new Block("kitchen", 480, 0, 160, 120),
			new Block("messHall", 640, 0, 160, 120),
			new Block("bioLab", 800, 0, 160, 120),
			new Block("security", 960, 0, 160, 120),
			new Block("chronoLab", 1120, 0, 160, 120),
			new Block("escapePod", 1280, 0, 160, 120)
		);
	public static SpriteSheet termbg = new SpriteSheet("assets/Graphics/TerminalBG.png", 256, 128,
			new Block("background", 0, 0, 160, 120)
		);
	public static SpriteSheet powerPuzzle = new SpriteSheet("assets/Graphics/Pipes.png", 64, 16,
			new Block("wireNone", 56, 0, 8, 8),
			new Block("wireUD", 0, 0, 8, 8),
			new Block("wireLR", 8, 0, 8, 8),
			new Block("wireRD", 16, 0, 8, 8),
			new Block("wireLD", 24, 0, 8, 8),
			new Block("wireUL", 32, 0, 8, 8),
			new Block("wireUR", 40, 0, 8, 8),
			new Block("wireItem", 48, 0, 8, 8),
			new Block("BwireUD", 0, 8, 8, 8),
			new Block("BwireLR", 8, 8, 8, 8),
			new Block("BwireRD", 16, 8, 8, 8),
			new Block("BwireLD", 24, 8, 8, 8),
			new Block("BwireUL", 32, 8, 8, 8),
			new Block("BwireUR", 40, 8, 8, 8),
			new Block("selector", 48, 8, 8, 8)
		);
	public static SpriteSheet sprites = new SpriteSheet("assets/Graphics/Sprite.png", 256, 256,
			new Block("jaunStand.1", 0, 16, 16, 16),
			new Block("jaunStand.2", 16, 16, 16, 16),
			new Block("jaunFreak.1", 32, 16, 16, 16),
			new Block("jaunFreak.2", 48, 16, 16, 16),
			new Block("jaunRun.1", 0, 0, 16, 16),
			new Block("jaunRun.2", 16, 0, 16, 16),
			new Block("jaunRun.3", 32, 0, 16, 16),
			new Block("jaunRun.4", 48, 0, 16, 16),
			new Block("jaunRun.5", 64, 0, 16, 16),
			new Block("jaunRun.6", 80, 0, 16, 16),
			new Block("jaunRun.7", 96, 0, 16, 16),
			new Block("jaunRun.8", 112, 0, 16, 16),
			new Block("jaunRun.9", 128, 0, 16, 16),
			new Block("jaunRun.10", 144, 0, 16, 16),
			new Block("jaunRun.11", 160, 0, 16, 16),
			new Block("jaunRun.12", 176, 0, 16, 16),
			new Block("jaunRun.13", 192, 0, 16, 16),
			new Block("jaunRun.14", 208, 0, 16, 16),
			new Block("jaunRun.15", 224, 0, 16, 16),
			new Block("jaunRun.16", 240, 0, 16, 16),
			new Block("chefCook.1", 0, 48, 16, 16),
			new Block("chefCook.2", 16, 48, 16, 16),
			new Block("chefPanic.1", 32, 48, 16, 16),
			new Block("chefPanic.2", 48, 48, 16, 16),
			new Block("mouse.1", 64, 48, 16, 16),
			new Block("mouse.2", 80, 48, 16, 16),
			new Block("security.1", 96, 48, 16, 16),
			new Block("security.2", 112, 48, 16, 16),
			new Block("testsub.1", 128, 48, 16, 16),
			new Block("testsub.2", 144, 48, 16, 16),
			new Block("scientist.1", 160, 48, 16, 16),
			new Block("scientist.2", 176, 48, 16, 16),
			new Block("cursor.normal", 0, 32, 16, 16),
			new Block("cursor.grab", 16, 32, 16, 16),
			new Block("cursor.look", 32, 32, 16, 16),
			new Block("cursor.mop", 48, 32, 16, 16),
			new Block("cursor.walk", 64, 32, 16, 16),
			new Block("cursor.nograb", 80, 32, 16, 16),
			new Block("captian.1", 0, 64, 16, 16),
			new Block("captian.2", 16, 64, 16, 16),
			new Block("captianpanic.1", 32, 64, 16, 16),
			new Block("captianpanic.2", 48, 64, 16, 16),
			new Block("leut.1", 64, 64, 16, 16),
			new Block("leut.2", 80, 64, 16, 16),
			new Block("rarmsrest.1", 96, 64, 16, 16),
			new Block("robotarms.1", 112, 64, 16, 16),
			new Block("robotarms.2", 128, 64, 16, 16),
			new Block("lamerobotarms.1", 144, 64, 16, 16),
			new Block("rarmspanic.1", 128, 64, 16, 16),
			new Block("rrmspanic.2", 144, 64, 16, 16),
			new Block("explode.1", 0, 80, 16, 16),
			new Block("explode.2", 16, 80, 16, 16),
			new Block("explode.3", 32, 80, 16, 16),
			new Block("explode.4", 48, 80, 16, 16),
			new Block("explode.5", 64, 80, 16, 16),
			new Block("explode.6", 80, 80, 16, 16),
			new Block("explode.7", 96, 80, 16, 16),
			new Block("explode.8", 112, 80, 16, 16),
			new Block("glass.1.1", 128, 80, 16, 16),
			new Block("glass.2.1", 144, 80, 16, 16),
			new Block("glass.3.1", 160, 80, 16, 16),
			new Block("glass.4.1", 176, 80, 16, 16),
			new Block("glass.5.1", 192, 80, 16, 16),
			new Block("glass.6.1", 208, 80, 16, 16),
			new Block("glass.7.1", 224, 80, 16, 16),
			new Block("glass.8.1", 240, 80, 16, 16),
			new Block("emote.no.1", 0, 96, 16, 16),
			new Block("emote.love.1", 16, 96, 16, 16),
			new Block("emote.death.1", 32, 96, 16, 16),
			new Block("emote.panic.1", 48, 96, 16, 16),
			new Block("emote.question.1", 64, 96, 16, 16),
			new Block("emote.hunger.1", 80, 96, 16, 16),
			new Block("emote.sleep.1", 96, 96, 16, 16),
			new Block("emote.happy.1", 112, 96, 16, 16),
			new Block("emote.sad.1", 128, 96, 16, 16),
			new Block("emote.music.1", 144, 96, 16, 16),
			new Block("door.down", 0, 112, 8, 32),
			new Block("trap.1", 0, 112, 8, 32),
			new Block("timelight.1", 8, 112, 32, 32),
			new Block("timelight.2", 40, 112, 32, 32),
			new Block("timelight.3", 72, 112, 32, 32),
			new Block("timelight.4", 104, 112, 32, 32),
			new Block("zap.1", 136, 112, 32, 32),
			new Block("zap.2", 168, 112, 32, 32),
			new Block("zap.3", 200, 112, 32, 32),
			new Block("item.cheese", 0,144, 8, 8),
			new Block("item.chicken", 8, 144, 8, 8),
			new Block("item.burger", 16, 144, 8, 8),
			new Block("item.sushi", 24, 144, 8, 8),
			new Block("item.mop", 32, 144, 8, 8),
			new Block("item.wrench", 40, 144, 8, 8),
			new Block("item.radio", 48, 144, 8, 8),
			new Block("item.eye", 56, 144, 8, 8),
			new Block("item.timecube", 64, 144, 8, 8),
			new Block("item.fuseLR", 0, 152, 8, 8),
			new Block("item.fuseUD", 8, 152, 8, 8),
			new Block("item.fuseULRD", 16, 152, 8, 8),
			new Block("item.fuseUR", 24, 152, 8, 8),
			new Block("item.fuseLU", 32, 152, 8, 8),
			new Block("item.fuseRD", 40, 152, 8, 8),
			new Block("item.fuseLD", 48, 152, 8, 8),
	        new Block("cd.1", 0, 160, 3, 5),
	        new Block("cd.2", 3, 160, 3, 5),
	        new Block("cd.3", 6, 160, 3, 5),
	        new Block("cd.4", 9, 160, 3, 5),
	        new Block("cd.5", 12, 160, 3, 5),
	        new Block("cd.6", 15, 160, 3, 5),
	        new Block("cd.7", 18, 160, 3, 5),
	        new Block("cd.8", 21, 160, 3, 5),
	        new Block("cd.9", 24, 160, 3, 5),
	        new Block("cd.0", 27, 160, 3, 5),
	        new Block("cd..", 30, 160, 3, 5),
	        new Block("terminalScreen", 56, 152, 16, 8)
			
		);
	/*	public static SpriteSheet boxBGSheet = new SpriteSheet("assets/Box BG.png", 16, 16,
	new Block("loop", 0, 0, 16, 16)
);*/



	private HashMap<String, double[]> uvMap = new HashMap<String, double[]>();
	private Texture texture;
	private double width, height;

	public SpriteSheet(String filename, int width, int height, Block... blocks)
	{
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(filename));
			texture.setTextureFilter(GL11.GL_NEAREST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < blocks.length; i++)
		{
			double left = (double)blocks[i].x / (double)width;
			double right = ((double)blocks[i].x + (double)blocks[i].width) / (double)width;
			double top = (double)blocks[i].y / (double)height;
			double bottom = ((double)blocks[i].y + (double)blocks[i].height) / (double)height;
			this.uvMap.put(blocks[i].name, new double[]{left, right, top, bottom});
			this.width = width;
			this.height = height;
		}
	}

	public void bindTexture()
	{
		texture.bind();
	}

	public double getU(String spriteID, int vertexID) {
		if(this.uvMap.containsKey(spriteID))
			return this.uvMap.get(spriteID)[((vertexID == 1 || vertexID == 2) ? 1 : 0)];
		return 0;
	}

	public double getV(String spriteID, int vertexID) {
		if(this.uvMap.containsKey(spriteID))
			return this.uvMap.get(spriteID)[((vertexID == 0 || vertexID == 1) ? 3 : 2)];
		return 0;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

}
