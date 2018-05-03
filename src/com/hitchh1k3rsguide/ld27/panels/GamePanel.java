package com.hitchh1k3rsguide.ld27.panels;

import java.util.ArrayList;

import org.newdawn.slick.openal.SoundStore;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.Enums.itemTypes;
import com.hitchh1k3rsguide.ld27.Enums.pT;
import com.hitchh1k3rsguide.ld27.GameSettings;
import com.hitchh1k3rsguide.ld27.gameplay.Item;
import com.hitchh1k3rsguide.ld27.gameplay.ZoneData;
import com.hitchh1k3rsguide.ld27.graphics.AnimatedSprite;
import com.hitchh1k3rsguide.ld27.graphics.Color;
import com.hitchh1k3rsguide.ld27.graphics.ParticleSystem;
import com.hitchh1k3rsguide.ld27.graphics.SpriteBox;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.sounds.SoundPlayer;
import com.hitchh1k3rsguide.ld27.utilities.Timer;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class GamePanel implements IPanel
{
	private SpriteFromSpriteSheet map;
	private SpriteFromSpriteSheet pipes;
	private SpriteFromSpriteSheet door;
	private SpriteFromSpriteSheet timerPrint;
	private SpriteFromSpriteSheet timerBack;
	private SpriteBox blueShift;
	private SpriteBox flashLight;
	private pT[] pipeData;
	private AnimatedSprite Jaun;
	private double jaunX, jaunY;
	private boolean isJaunWalk, jaunRunAni;
	private double jaunVelX;
	private static final int HORZ_VEL = 75;
	private static final int JAUN_START = 120;
	private static final double GAME_TIME_FACTOR = 0.335;
	private static final double BLUE_RATE = 0.033;
	private int tileSelected;
	private boolean grabbingIcon;
	private boolean walkingIcon;
	private boolean lookIcon;
	private int currentZone;
	private int termX, termY;
	private double runTimer;
	private boolean runMode;
	private boolean timeFlipped;
	private double flash;
	private Color flashColor;
	private Color timeShiftColor;
	private double deathCooldown;
	private boolean inRoom;
	private Item itemInHand;
	private double blueCooldown;
	private int blueIndex;
	private int powerRoomDistance;
	private ArrayList<ArrayList<Integer>> bluePaths;
	private boolean jaming;
	private boolean gameEnding;

	public GamePanel()
	{
		currentZone = 0;
		runMode = false;
		pipeData = ZoneData.getPipes(currentZone);
		Vec2DPoint term = ZoneData.getTermLocation(currentZone);
		termX = (int) term.getDX();
		termY = (int) term.getDY();
		door = new SpriteFromSpriteSheet(SpriteSheet.sprites, "door.down", 0, 0, 16, 54);
		map = new SpriteFromSpriteSheet(SpriteSheet.roomParts, ZoneData.getBackground(currentZone), 0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
		pipes = new SpriteFromSpriteSheet(SpriteSheet.powerPuzzle, "", 0, 0, 16, 16);
		Jaun = new AnimatedSprite(SpriteSheet.sprites, "jaunStand", 152, 152, 32, 32, 2, 1.5);
		jaunX = JAUN_START;
		jaunY = 178;
		isJaunWalk = false;
		jaunRunAni = false;
		tileSelected = -1;
		grabbingIcon = false;
		walkingIcon = false;
		timeShiftColor = new Color(0., 0., 1., 0.125);
		blueShift = new SpriteBox(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, timeShiftColor);
		blueShift.setOpacity(true);
		flashColor = new Color(1., 1., 1., 0.);
		flashLight = new SpriteBox(0, 0, GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT, flashColor);
		flashLight.setOpacity(true);
		flash = 0;
		deathCooldown = 0;
		timerPrint = new SpriteFromSpriteSheet(SpriteSheet.sprites, "", 0, 0, 6, 10);
		timerBack = new SpriteFromSpriteSheet(SpriteSheet.sprites, "terminalScreen", 0, 0, 32, 16);
		runTimer = 0;
		timeFlipped = false;
		inRoom = false;
		itemInHand = null;
		blueCooldown = BLUE_RATE;
		blueIndex = 0;
		bluePaths = new ArrayList<ArrayList<Integer>>(10);
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		bluePaths.add(new ArrayList<Integer>());
		powerRoomDistance = 0;
		jaming = false;
		gameEnding = false;
	}

	@Override
	public void onAttach()
	{
		switchMode(true);
		SoundPlayer.playMusic("tension");
		PanelManager.getInstance().setTimeScale(0.25);
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
		if(isJaunWalk || runMode)
			return false;
		grabbingIcon = false;
		lookIcon = false;
		walkingIcon = false;
		if(x > 16 && x < 304 && y > 32 && y < 160)
		{
			tileSelected = (x-16)/16;
			tileSelected += 18*((y-32)/16);
		}
		else
			tileSelected = -1;
		if(itemInHand == null)
		{
			if(tileSelected != -1 && pipeData[tileSelected] != pT.NO)
				if(pipeData[tileSelected] == pT.IT || pipeData[tileSelected] == pT.FLR || pipeData[tileSelected] == pT.FUD || pipeData[tileSelected] == pT.FUL || pipeData[tileSelected] == pT.FUR || pipeData[tileSelected] == pT.FLD || pipeData[tileSelected] == pT.FRD)
				{
					((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.GRAB);
					grabbingIcon = true;
				}
				else
					((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.MOP);
			else
			{
				((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
				for(int i = 0; i < ZoneData.numSlots(currentZone); ++i)
				{
					Vec2DPoint point = ZoneData.getSlotVec(currentZone, i);
					if(x >= point.getDX() && x <= point.getDX() + 16 && y >= point.getDY() && y <= point.getDY() + 16)
					{
						((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.GRAB);
						grabbingIcon = true;
					}
				}
				if(currentZone == 0 && x >= 128 && x <= 192 && y >= 64 && y <= 128)
				{
					((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.GRAB);
					grabbingIcon = true;
				}
			}
			if(grabbingIcon == false)
			{
				if(x >= termX && x <= termX + 36 && y >= termY && y <= termY + 30)
				{
					((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.LOOK);
					lookIcon = true;
				}
			}			
		}
		else
		{
			((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.ITEM);
		}
		if(lookIcon == false)
		{
			if( (x >= 0 && x <= 16 && y >= 154 && y <= 208 && ZoneData.getCanGo(runMode?1:0, currentZone, false))
			 || (x >= 304 && x <= 320 && y >= 154 && y <= 208 && ZoneData.getCanGo(runMode?1:0, currentZone, true)))
			{
				((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.WALK);
				walkingIcon = true;
			}
		}
		return false;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown)
	{
		if(!runMode && !isJaunWalk && isDown && button == 0)
		{
			if(itemInHand != null)
			{
				if(tileSelected != -1 && pipeData[tileSelected] == pT.IT && itemInHand.getType() == Enums.itemTypes.CIRCUIT)
				{
					if(itemInHand == Item.fuseLR)
						pipeData[tileSelected] = pT.FLR;
					else if(itemInHand == Item.fuseUD)
						pipeData[tileSelected] = pT.FUD;
					else if(itemInHand == Item.fuseLU)
						pipeData[tileSelected] = pT.FUL;
					else if(itemInHand == Item.fuseUR)
						pipeData[tileSelected] = pT.FUR;
					else if(itemInHand == Item.fuseLD)
						pipeData[tileSelected] = pT.FLD;
					else if(itemInHand == Item.fuseRD)
						pipeData[tileSelected] = pT.FRD;
					itemInHand = null;
				}
				else
				{
					for(int i = 0; i < ZoneData.numSlots(currentZone); ++i)
					{
						if(itemInHand != null)
						{
							Vec2DPoint point = ZoneData.getSlotVec(currentZone, i);
							if(x >= point.getDX() && x <= point.getDX() + 16 && y >= point.getDY() && y <= point.getDY() + 16)
							{
								if(ZoneData.getItemInSlot(currentZone, i) == null)
								{
									if(ZoneData.getSlotType(currentZone, i) == itemInHand.getType() || ZoneData.getSlotType(currentZone, i) == itemTypes.GENERIC)
									{
										ZoneData.setItemInSlot(currentZone, i, itemInHand);
										itemInHand = null;
									}
								}
							}
						}
					}
				}
			}
			else
			{
				if(currentZone == 0 && x >= 128 && x <= 192 && y >= 64 && y <= 128)
				{
					switchMode(true);
					return true;
				}
				if(lookIcon)
				{
					PanelManager.getInstance().addPanel(new TerminalPanel(currentZone+1), Enums.displayLayers.GAME);
				}
				if(grabbingIcon)
				{
					if(tileSelected != -1 && (pipeData[tileSelected] == pT.FLR || pipeData[tileSelected] == pT.FUD|| pipeData[tileSelected] == pT.FUL || pipeData[tileSelected] == pT.FUR || pipeData[tileSelected] == pT.FLD || pipeData[tileSelected] == pT.FRD))
					{
						if(pipeData[tileSelected] == pT.FLR)
							itemInHand = Item.fuseLR;
						else if(pipeData[tileSelected] == pT.FUD)
							itemInHand = Item.fuseUD;
						else if(pipeData[tileSelected] == pT.FUL)
							itemInHand = Item.fuseLU;
						else if(pipeData[tileSelected] == pT.FUR)
							itemInHand = Item.fuseUR;
						else if(pipeData[tileSelected] == pT.FLD)
							itemInHand = Item.fuseLD;
						else if(pipeData[tileSelected] == pT.FRD)
							itemInHand = Item.fuseRD;
						pipeData[tileSelected] = pT.IT;
						((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.GRAB);
						grabbingIcon = true;
					}
					else
					{
						if(itemInHand == null)
						{
							for(int i = 0; i < ZoneData.numSlots(currentZone); ++i)
							{
								Vec2DPoint point = ZoneData.getSlotVec(currentZone, i);
								if(x >= point.getDX() && x <= point.getDX() + 16 && y >= point.getDY() && y <= point.getDY() + 16)
								{
									itemInHand = ZoneData.getItemInSlot(currentZone, i);
									ZoneData.setItemInSlot(currentZone, i, null);
								}
							}
						}
					}
					if(itemInHand != null)
					{
						((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.ITEM);
						grabbingIcon = false;
					}
				}
				if(tileSelected >= 0)
				{
					if(pipeData[tileSelected] == pT.UD)
						pipeData[tileSelected] = pT.LR;
					else if(pipeData[tileSelected] == pT.LR)
						pipeData[tileSelected] = pT.UD;
					else if(pipeData[tileSelected] == pT.UR)
						pipeData[tileSelected] = pT.RD;
					else if(pipeData[tileSelected] == pT.RD)
						pipeData[tileSelected] = pT.LD;
					else if(pipeData[tileSelected] == pT.LD)
						pipeData[tileSelected] = pT.UL;
					else if(pipeData[tileSelected] == pT.UL)
						pipeData[tileSelected] = pT.UR;
					return true;
				}
			}
			if(walkingIcon)
			{
				if(ZoneData.getCanGo(runMode?1:0, currentZone, (x > 100)))
				{
					jaunVelX = (x < 100 ? -1 : 1)*HORZ_VEL;
					isJaunWalk = true;
					inRoom = false;
				}
			}
		}
		((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setItem(itemInHand);
		return false;
	}

	private void switchMode(boolean isRunning)
	{
		if(isRunning)
		{
			runMode = true;
			PanelManager.getInstance().setTimeScale(1);
			SoundPlayer.playMusic("run");
			runTimer = 10;
			flash = 1;
			((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
			jaunVelX = HORZ_VEL;
			isJaunWalk = true;
			deathCooldown = 0;
			timeFlipped = false;
			buildBluePipe();
			powerRoomDistance = 0;
			jaming = false;
		}
		else
		{
			runMode = false;
			PanelManager.getInstance().setTimeScale(0);
			SoundPlayer.playMusic("game");
			flash = 1;
			((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
			jaunVelX = 0;
			isJaunWalk = false;
			jaunX = JAUN_START;
			setZone(0);
			deathCooldown = 0;
			runTimer = 0;
			timeFlipped = false;
			bluePaths = new ArrayList<ArrayList<Integer>>(10);
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
			bluePaths.add(new ArrayList<Integer>());
		}
	}

	public void jaunRun(boolean state)
	{
		if(state && !jaunRunAni)
			Jaun.setAnimation("jaunRun", 16, 0.05);
		if(!state && jaunRunAni)
			Jaun.setAnimation("jaunStand", 2, 1.5);
		jaunRunAni = state;
	}

	@Override
	public void update(double time)
	{
		if(gameEnding && currentZone == 0 && jaunX <= JAUN_START)
		{
			PanelManager.getInstance().addPanel(new TerminalPanel(9), Enums.displayLayers.GAME);
		}
		if(timeFlipped && currentZone == 0 && jaunX <= JAUN_START)
		{
			switchMode(false);
		}
		ParticleSystem.updateParticles(currentZone, time);
		if(runMode && !timeFlipped && powerRoomDistance >= currentZone)
		{
			blueCooldown -= time;
			while(bluePaths.get(currentZone).size() > 0 && blueCooldown < 0)
			{
				blueCooldown += BLUE_RATE;
				if(blueIndex < bluePaths.get(currentZone).size())
					++blueIndex;
				if(blueIndex == 0)
					blueIndex = 1;
				if(currentZone == 6 && bluePaths.get(currentZone).get(blueIndex-1) == 119)
				{
					int index = bluePaths.get(currentZone).get(blueIndex-1);
					if(pipeData[index] == pT.FLR || pipeData[index] == pT.FULRD || pipeData[index] == pT.FUR || pipeData[index] == pT.FRD ||
					   pipeData[index] == pT.LR || pipeData[index] == pT.UR || pipeData[index] == pT.RD)
					{
						if(ZoneData.getItemInSlot(currentZone, 8) != null)
						{
							gameEnding = true;
							deathCooldown = 2;
							flash = 1;
						}
					}
				}
				if(bluePaths.get(currentZone).get(blueIndex-1) == 17) // TOP POWER
				{
					int index = bluePaths.get(currentZone).get(blueIndex-1);
					if(pipeData[index] == pT.FLR || pipeData[index] == pT.FULRD || pipeData[index] == pT.FUR || pipeData[index] == pT.FRD ||
					   pipeData[index] == pT.LR || pipeData[index] == pT.UR || pipeData[index] == pT.RD)
					{
						powerRoomDistance = currentZone + 1;
					}
				}
				if(bluePaths.get(currentZone).get(blueIndex-1) == 125) // DOOR POWER
				{
					int index = bluePaths.get(currentZone).get(blueIndex-1);
					if(pipeData[index] == pT.FLR || pipeData[index] == pT.FULRD || pipeData[index] == pT.FUR || pipeData[index] == pT.FRD ||
					   pipeData[index] == pT.LR || pipeData[index] == pT.UR || pipeData[index] == pT.RD)
					{
						ZoneData.openDoor(currentZone);
					}
				}
			}
		}
		if(deathCooldown > 0) // TODO
		{//soundPitch
			deathCooldown -= Timer.getFrameTime();
			double scale = (deathCooldown-1);
			PanelManager.getInstance().setTimeScale((scale*1.5)-0.5);
			if(deathCooldown < 1 && !timeFlipped)
			{
				timeFlipped = true;
				SoundPlayer.reverseSong(false);
			}
			if(timeFlipped)
				SoundStore.get().setMusicPitch((float) (1-scale));
			else
				SoundStore.get().setMusicPitch((float) (scale-1));
			if(deathCooldown < 0)
			{
				deathCooldown = 0;
				jaunRun(false);
				jaunRun(true);
				jaunVelX = HORZ_VEL;
			}
		}
		if(deathCooldown <= 0 && runTimer > 0)
		{
			runTimer -= time*GAME_TIME_FACTOR;
			if(runTimer < 0.5)
			{
				deathCooldown = 2;
			}
		}
		if(flash > 0)
		{
			flash -= Timer.getFrameTime()*0.25;
			if(flash > 0.75)
				flash -= Timer.getFrameTime()*0.5;
			if(flash > 0.5)
				flash -= Timer.getFrameTime();
			if(flash > 0.25)
				flash -= Timer.getFrameTime()*2;
			if(flash < 0)
				flash = 0;
			flashColor.setAlpha(flash);
		}
		double oX = jaunX;
		if(runMode)
		{
			Jaun.update(time);
			jaunX += time*jaunVelX;
		}
		else
		{
			Jaun.update(Timer.getFrameTime());
			jaunX += Timer.getFrameTime()*jaunVelX;
		}
		if(!runMode && inRoom)
		{
			if((jaunX < JAUN_START && jaunVelX < 0) || (jaunX > JAUN_START && jaunVelX > 0))
			{
				jaunX = JAUN_START;
				jaunVelX = 0;
				Jaun.setFlip(false);
				walkingIcon = false;
				((CursorPanel)PanelManager.getInstance().getPanel(CursorPanel.class)).setCursor(Enums.cursors.NORMAL);
			}
		}
		if(jaunX < (ZoneData.getCanGo(runMode?1:0, currentZone, false)?0:8) && (timeFlipped || jaunVelX < 0))
		{
			if(ZoneData.getCanGo(runMode?1:0, currentZone, false))
			{
				jaunX = GameSettings.GAME_WIDTH+jaunX;
				ParticleSystem.clearParticles(currentZone);
				setZone(currentZone-1);
			}
			else
			{
				jaunX = 8;
				jaunVelX = 0;
			}
		}
		if(jaunX > ZoneData.getMaxWidth(runMode?1:0, currentZone)-(ZoneData.getCanGo(runMode?1:0, currentZone, true)?0:32) && (timeFlipped || jaunVelX > 0))
		{
			if(ZoneData.getCanGo(runMode?1:0, currentZone, true))
			{
				jaunX = -32+(jaunX-GameSettings.GAME_WIDTH);
				setZone(currentZone+1);
			}
			else
			{
				if(runMode)
				{
					Jaun.setAnimation("jaunFreak", 2, 0.25);
					Jaun.setFlip(false);
					deathCooldown = 3;
					jaunVelX = 0;
					jaunX = ZoneData.getMaxWidth(runMode?1:0, currentZone)-33;
				}
				else
				{
					jaunX = ZoneData.getMaxWidth(runMode?1:0, currentZone)-32;
					jaunVelX = 0;
				}
			}
		}
		if(deathCooldown == 0)
		{
			if(oX != jaunX)
				jaunRun(true);
			else
				jaunRun(false);
		}
		if(!isJaunWalk && jaunVelX != 0)
		{
			isJaunWalk = true;
		}
		if(isJaunWalk && jaunVelX == 0)
		{
			isJaunWalk = false;
		}
		if(jaunVelX < 0)
			Jaun.setFlip(true);
		if(jaunVelX > 0)
			Jaun.setFlip(false);
		Jaun.setPos((int)jaunX, (int)jaunY);
		ZoneData.update(currentZone, time);
	}

	private void setZone(int i)
	{
		if(i == 4 && runMode)
		{
			if(timeFlipped)
				SoundPlayer.playMusic("Rmdsitw");
			else
				SoundPlayer.playMusic("mdsitw");
			jaming = true;
		}
		else if(jaming)
		{
			if(timeFlipped)
				SoundPlayer.playMusic("Rrun");
			else
				SoundPlayer.playMusic("run");
			jaming = false;
		}
		if(!timeFlipped && runMode)
		{
			ParticleSystem.addParticle(i, "fire", (int)(Math.random()*(GameSettings.GAME_WIDTH/3))+25, (int)(Math.random()*GameSettings.GAME_HEIGHT/2)+25, 20, Math.random()*5);
			ParticleSystem.addParticle(i, "fire", (int)(Math.random()*(GameSettings.GAME_WIDTH/3))+25, (int)(Math.random()*GameSettings.GAME_HEIGHT/2)+25, 20, Math.random()*5);
			ParticleSystem.addParticle(i, "fire", (int)(Math.random()*(GameSettings.GAME_WIDTH/3))+25, (int)(Math.random()*GameSettings.GAME_HEIGHT/2)+25, 20, Math.random()*5);
			ParticleSystem.addParticle(i, "fire", (int)(Math.random()*(GameSettings.GAME_WIDTH/3))+25, (int)(Math.random()*GameSettings.GAME_HEIGHT/2)+25, 20, Math.random()*5);
			ParticleSystem.addParticle(i, "fire", (int)(Math.random()*(GameSettings.GAME_WIDTH/3))+25, (int)(Math.random()*GameSettings.GAME_HEIGHT/2)+25, 20, Math.random()*5);
		}
			//TODO
		inRoom = !runMode;
		currentZone = i;
		pipeData = ZoneData.getPipes(currentZone);
		Vec2DPoint term = ZoneData.getTermLocation(currentZone);
		termX = (int) term.getDX();
		termY = (int) term.getDY();
		map.setSprite(ZoneData.getBackground(currentZone));
		tileSelected = -1;
		blueCooldown = BLUE_RATE;
		blueIndex = 0;
	}

	private String pipeLookup(pT id)
	{
		if(id == pT.UD)
			return "wireUD";
		else if(id == pT.LR)
			return "wireLR";
		else if(id == pT.RD)
			return "wireRD";
		else if(id == pT.LD)
			return "wireLD";
		else if(id == pT.UL)
			return "wireUL";
		else if(id == pT.UR)
			return "wireUR";
		else if(id == pT.IT || id == pT.FUD || id == pT.FLR)
			return "wireItem";
		return "wireNone";
	}

	@Override
	public void draw()
	{
		map.draw();
		int i = -1;
		for(int y = 0; y < 8; ++y)
		{
			for(int x = 0; x < 18; ++x)
			{
				pipes.setSprite(pipeLookup(pipeData[++i]));
				pipes.drawAt(16+(16*x), 32+(16*y));
				if(pipeData[i] == pT.FLR)
					Item.fuseLR.drawAt(16+(16*x), 32+(16*y));
				else if(pipeData[i] == pT.FUD)
					Item.fuseUD.drawAt(16+(16*x), 32+(16*y));
				else if(pipeData[i] == pT.FUL)
					Item.fuseLU.drawAt(16+(16*x), 32+(16*y));
				else if(pipeData[i] == pT.FUR)
					Item.fuseUR.drawAt(16+(16*x), 32+(16*y));
				else if(pipeData[i] == pT.FRD)
					Item.fuseRD.drawAt(16+(16*x), 32+(16*y));
				else if(pipeData[i] == pT.FLD)
					Item.fuseLD.drawAt(16+(16*x), 32+(16*y));
				if(i == tileSelected && pipeData[i] != pT.NO && itemInHand == null)
				{
					pipes.setSprite("selector");
					pipes.drawAt(16+(16*x), 32+(16*y));
				}
			}
		}
		for(int p = 0; p < blueIndex; ++p)
		{
			int index = bluePaths.get(currentZone).get(p);
			pipes.setSprite("B"+pipeLookup(pipeData[index]));
			int y = (int) Math.floor(Math.abs(index/18));
			int x = index-(18*y);
			pipes.drawAt(16+(16*x), 32+(16*y));
		}
		ZoneData.draw(runMode?1:0, currentZone);
		if(!ZoneData.getDoor(currentZone))
			door.drawAt(304, 154);
		if(!ZoneData.getCanGo(runMode?1:0, currentZone, false))
			door.drawAt(0, 154);
		if(runMode)
		{
			timerBack.drawAt(termX+2, termY+2);
			int dig1 = (int)Math.floor(runTimer);
			int dig2 = (int)Math.floor((runTimer-dig1)*10);
			int dig3 = (int)Math.floor((((runTimer-dig1)*10)-dig2)*10);
			timerPrint.setSprite("cd."+dig1);
			timerPrint.drawAt(termX+5, termY+6);
			timerPrint.setSprite("cd..");
			timerPrint.drawAt(termX+11, termY+6);
			timerPrint.setSprite("cd."+dig2);
			timerPrint.drawAt(termX+17, termY+6);
			timerPrint.setSprite("cd."+dig3);
			timerPrint.drawAt(termX+25, termY+6);
		}
		Jaun.draw();
		if(deathCooldown > 0)
			ZoneData.drawPanic(currentZone);
		ParticleSystem.drawParticles(currentZone);
		if(!runMode || timeFlipped)
			blueShift.draw();
		if(flash > 0)
			flashLight.draw();
	}

	private void buildBluePipe()
	{
		for(int i = 0; i <= 6; ++i)
		{
			pT[] tempPipes = ZoneData.getPipes(i);
			int zombieMeds = 0;
			int index = (i == 0 ? 11 : -1);
			int dir = 1;
			boolean good = true;
			while(good)
			{
				++zombieMeds;
				if(zombieMeds > 150)
					good = false;
				if(index >= 0 && tempPipes[index] != pT.FLD && tempPipes[index] != pT.FLR && tempPipes[index] != pT.FRD && tempPipes[index] != pT.FUD && tempPipes[index] != pT.FUL && tempPipes[index] != pT.FULRD && tempPipes[index] != pT.FUR)
					bluePaths.get(i).add(index);
				int y = (int) Math.floor(Math.abs(index/18));
				int x = index-(18*y);
				if(dir == 0)
					--y;
				else if(dir == 1)
					++x;
				else if(dir == 2)
					++y;
				else if(dir == 3)
					--x;
				if(x < 0 || x > 18 || y < 0 || y > 8)
					good = false;
				else
				{
					index = (y*18)+x;
					pT type = tempPipes[index];
					if(dir == 0)
					{
						if(type == pT.FLD || type == pT.LD)
							dir = 3;
						else if(type == pT.FRD || type == pT.RD)
							dir = 1;
						else if(type == pT.FUD || type == pT.UD)
							dir = 0;
						else
							good = false;
					}
					else if(dir == 1)
					{
						if(type == pT.FUL || type == pT.UL)
							dir = 0;
						else if(type == pT.FLR || type == pT.LR)
							dir = 1;
						else if(type == pT.FLD || type == pT.LD)
							dir = 2;
						else
							good = false;
					}
					else if(dir == 2)
					{
						if(type == pT.FUL || type == pT.UL)
							dir = 3;
						else if(type == pT.FUR || type == pT.UR)
							dir = 1;
						else if(type == pT.FUD || type == pT.UD)
							dir = 2;
						else
							good = false;
					}
					else if(dir == 3)
					{
						if(type == pT.FUR || type == pT.UR)
							dir = 0;
						else if(type == pT.FLR || type == pT.LR)
							dir = 3;
						else if(type == pT.FRD || type == pT.RD)
							dir = 2;
						else
							good = false;
					}
				}
			}
		}
	}

}
