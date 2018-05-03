package com.hitchh1k3rsguide.ld27.gameplay;

import java.util.ArrayList;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.Enums.itemTypes;
import com.hitchh1k3rsguide.ld27.Enums.pT;
import com.hitchh1k3rsguide.ld27.GameSettings;
import com.hitchh1k3rsguide.ld27.graphics.AnimatedSprite;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class ZoneData
{

	private static final int DOOR_LIMIT = GameSettings.GAME_WIDTH-8;

	public static class ItemSlot
	{
		private int x, y;
		private Item item;
		private Enums.itemTypes type;
		public ItemSlot(int x, int y, Enums.itemTypes type, Item item)
		{
			this.x = x;
			this.y = y;
			this.type = type;
			this.item = item;
		}
		public Item getItem()
		{
			return item;
		}
		public void setItem(Item item)
		{
			this.item = item;
		}
		public itemTypes getType()
		{
			return type;
		}
	}

	public static class Sprite
	{
		private AnimatedSprite graphic;
		public Sprite(String spriteName, int x, int y, int w, int h, int frames, double delay)
		{
			graphic = new AnimatedSprite(SpriteSheet.sprites, spriteName, x, y, w, h, frames, delay);
		}
		public void update(double frameTime)
		{
			graphic.update(frameTime);
		}
		public void draw()
		{
			graphic.draw();
		}
	}

	private static final ZoneData[] zones = new ZoneData[]{new ZoneData(0), new ZoneData(1), new ZoneData(2), new ZoneData(3), new ZoneData(4), new ZoneData(5), new ZoneData(6), new ZoneData(7)};

	private pT[] pipeData;
	private ArrayList<ItemSlot> items;
	private ArrayList<Sprite> sprites;
	private ArrayList<Sprite> panicSprites;
	private String backgroundName;
	private Vec2DPoint termLocation;
	private boolean isDoorOpen;

	private ZoneData(int id)
	{
		isDoorOpen = false;
		if(id == 1)
		{
			pipeData = new pT[]{
					 pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.RD, pT.UD, pT.UD, pT.LR, pT.UD, pT.LR, pT.LD, pT.LR, pT.LR, pT.UD, pT.LR, pT.UD, 
					 pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.LD, pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.UR, 
					 pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.IT, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD,
					 pT.UR, pT.LD, pT.NO, pT.RD, pT.UD, pT.UL, pT.UD, pT.LD, pT.NO, pT.NO, pT.UL, pT.LR, pT.UR, pT.RD, pT.LR, pT.LR, pT.UD, pT.UL, 
					 pT.NO, pT.UR, pT.LR, pT.LD, pT.NO, pT.NO, pT.UD, pT.RD, pT.LR, pT.LD, pT.UD, pT.NO, pT.RD, pT.UD, pT.LR, pT.LD, pT.NO, pT.NO, 
					 pT.NO, pT.NO, pT.NO, pT.UL, pT.LD, pT.RD, pT.LR, pT.UR, pT.RD, pT.UR, pT.UD, pT.RD, pT.UR, pT.UD, pT.NO, pT.LR, pT.NO, pT.NO, 
					 pT.NO, pT.NO, pT.NO, pT.NO, pT.UL, pT.UR, pT.UR, pT.LD, pT.UR, pT.UD, pT.IT, pT.LD, pT.NO, pT.UD, pT.LR, pT.UR, pT.LR, pT.LR, 
					 pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UL, pT.UD, pT.UR, pT.LR, pT.LR, pT.UR, pT.UR, pT.LR, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(144, 168, Enums.itemTypes.GENERIC, Item.fuseLR));
			items.add(new ItemSlot(184, 184, Enums.itemTypes.GENERIC, null));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("robotarms", 88*2, 88*2, 32, 32, 2, 1.));
			sprites.add(new Sprite("emote.love", 88*2, 64*2, 32, 32, 1, 1));
			panicSprites = new ArrayList<Sprite>();
			panicSprites.add(new Sprite("emote.love", 88*2, 64*2, 32, 32, 1, 1));
			panicSprites.add(new Sprite("robotarms", 88*2, 88*2, 32, 32, 2, 1.));
			termLocation = new Vec2DPoint(30, 174);
			backgroundName = "engineering";
		}
		else if(id == 2)
		{
			pipeData = new pT[]{
					pT.RD, pT.LD, pT.UD, pT.UL, pT.UR, pT.LR, pT.UD, pT.UR, pT.UL, pT.UD, pT.UD, pT.UD, pT.UL, pT.UD, pT.UD, pT.RD, pT.UD, pT.LR,
					pT.UR, pT.RD, pT.LR, pT.RD, pT.UR, pT.NO, pT.NO, pT.UL, pT.UR, pT.LD, pT.UR, pT.NO, pT.LR, pT.NO, pT.LD, pT.UL, pT.LR, pT.NO,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UL, pT.RD, pT.RD, pT.UD, pT.UL, pT.LD, pT.UR, pT.LR, pT.RD, pT.UL,
					pT.UL, pT.UR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.RD, pT.UD, pT.LR, pT.UR, pT.LD, pT.UR, pT.UD, pT.LD,
					pT.UR, pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.RD, pT.UR, pT.NO, pT.NO, pT.UL, pT.UD, pT.RD, pT.LR, pT.NO, pT.NO,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.UR, pT.UD, pT.LR, pT.LR, pT.UD, pT.LR, pT.RD, pT.UR, pT.NO,
					pT.UL, pT.RD, pT.LR, pT.RD, pT.LR, pT.RD, pT.LR, pT.UD, pT.UR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.LD, pT.UD, pT.LR,
					pT.UR, pT.UD, pT.UD, pT.RD, pT.LR, pT.LD, pT.UD, pT.UR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(96, 194, Enums.itemTypes.GENERIC, Item.fuseUD));
			items.add(new ItemSlot(112, 194, Enums.itemTypes.GENERIC, Item.cheese));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("mouse", 80, 176, 32, 32, 2, 0.25));
			sprites.add(new Sprite("chefCook", 208, 160, 32, 32, 2, 1));
			sprites.add(new Sprite("chefPanic", 100*2, 80*2, 32, 32, 2, 0.125));
			sprites.add(new Sprite("emote.panic", 100*2, 64*2, 32, 32, 1, 1.));
			sprites.add(new Sprite("emote.music", 100*2, 64*2, 32, 32, 1, 1.));
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(30, 174);
			backgroundName = "kitchen";
		}
		else if(id == 3)
		{
			pipeData = new pT[]{
					pT.LD, pT.UR, pT.UD, pT.RD, pT.UR, pT.UD, pT.LR, pT.UD, pT.LR, pT.UD, pT.UD, pT.LR, pT.LD, pT.RD, pT.UR, pT.LD, pT.UD, pT.UD,
					pT.UL, pT.RD, pT.NO, pT.UL, pT.UR, pT.LR, pT.UL, pT.UD, pT.UD, pT.LR, pT.LR, pT.UR, pT.LD, pT.LR, pT.UD, pT.UD, pT.NO, pT.NO,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.LD, pT.RD, pT.UR, pT.UR, pT.UD, pT.RD,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.UL, pT.IT, pT.UR, pT.NO, pT.UD, pT.NO, pT.LR,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.UD, pT.LR, pT.NO, pT.UL, pT.UR, pT.LR,
					pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.UD, pT.UD, pT.RD, pT.UD, pT.LD, pT.UD,
					pT.RD, pT.UL, pT.LR, pT.LR, pT.UD, pT.LR, pT.LD, pT.LR, pT.UR, pT.LD, pT.LR, pT.UR, pT.LR, pT.RD, pT.UL, pT.LD, pT.UL, pT.UR,
					pT.RD, pT.UD, pT.UD, pT.LR, pT.UD, pT.LR, pT.LR, pT.UD, pT.UR, pT.UL, pT.UD, pT.UD, pT.UR, pT.RD, pT.LR, pT.UR, pT.LR, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(64, 176, Enums.itemTypes.FOOD, Item.burger));
			items.add(new ItemSlot(208, 186, Enums.itemTypes.GENERIC, Item.chicken));
			items.add(new ItemSlot(224, 186, Enums.itemTypes.GENERIC, null));
			items.add(new ItemSlot(240, 186, Enums.itemTypes.GENERIC, Item.sushi));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("captian", 32*2, 88*2, 32, 32, 2, 0.5));
			sprites.add(new Sprite("captianpanic", 40*2, 88*2, 32, 32, 2, 0.5));
			sprites.add(new Sprite("emote.death", 36*2, 72*2, 32, 32, 1, 1));
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(262, 174);
			backgroundName = "messHall";
		}
		else if(id == 4)
		{
            pipeData = new pT[]{
            		pT.LR, pT.UD, pT.UD, pT.LD, pT.LD, pT.LR, pT.LR, pT.RD, pT.NO, pT.LD, pT.LR, pT.LR, pT.UD, pT.UD, pT.LD, pT.LD, pT.LR, pT.UD,
            		pT.NO, pT.NO, pT.RD, pT.UL, pT.UD, pT.RD, pT.LD, pT.UD, pT.NO, pT.UR, pT.LR, pT.LR, pT.LD, pT.NO, pT.UR, pT.UL, pT.UD, pT.NO,
            		pT.NO, pT.LD, pT.UR, pT.UD, pT.LR, pT.UR, pT.UD, pT.LR, pT.RD, pT.UD, pT.LD, pT.NO, pT.UL, pT.LD, pT.NO, pT.NO, pT.UR, pT.LD,
            		pT.NO, pT.UL, pT.UD, pT.RD, pT.LR, pT.RD, pT.UD, pT.UD, pT.UD, pT.NO, pT.UD, pT.NO, pT.NO, pT.UD, pT.RD, pT.LD, pT.NO, pT.UD,
            		pT.NO, pT.LR, pT.RD, pT.UL, pT.UL, pT.LR, pT.UL, pT.LR, pT.UD, pT.LR, pT.UD, pT.LR, pT.UD, pT.LR, pT.UR, pT.UD, pT.LD, pT.LD,
            		pT.NO, pT.UD, pT.UR, pT.LR, pT.LR, pT.UL, pT.NO, pT.UR, pT.UL, pT.NO, pT.UR, pT.LD, pT.NO, pT.UD, pT.NO, pT.LR, pT.LR, pT.UD,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.RD, pT.UD, pT.UR, pT.NO, pT.UR, pT.UR, pT.UR,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(84*2, 88*2, Enums.itemTypes.GENERIC, Item.eye));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("testsub", 12*2, 88*2, 32, 32, 2, 0.5));
			sprites.add(new Sprite("scientist", 28*2, 88*2, 32, 32, 2, 0.5));
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(46*2, 87*2);
			backgroundName = "bioLab";
		}
		else if(id == 5)
		{
            pipeData = new pT[]{
            		pT.UD, pT.LR, pT.LD, pT.NO, pT.RD, pT.LR, pT.LR, pT.LR, pT.LD, pT.NO, pT.RD, pT.UD, pT.LD, pT.NO, pT.UD, pT.LD, pT.LD, pT.RD,
            		pT.RD, pT.LD, pT.LR, pT.NO, pT.LR, pT.NO, pT.RD, pT.LD, pT.LR, pT.UD, pT.UL, pT.NO, pT.UR, pT.LR, pT.UL, pT.LR, pT.UD, pT.UD,
            		pT.UD, pT.RD, pT.UL, pT.RD, pT.UR, pT.UD, pT.UL, pT.LR, pT.UD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.LR,
            		pT.UR, pT.LR, pT.UD, pT.UD, pT.LR, pT.UD, pT.LR, pT.UR, pT.RD, pT.LR, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.LR,
            		pT.NO, pT.NO, pT.LD, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.LD, pT.UR,
            		pT.NO, pT.NO, pT.LR, pT.NO, pT.RD, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.UD,
            		pT.NO, pT.RD, pT.UR, pT.LR, pT.UR, pT.UD, pT.NO, pT.NO, pT.NO, pT.LD, pT.UR, pT.UD, pT.LR, pT.LR, pT.LR, pT.LR, pT.UL, pT.RD,
            		pT.NO, pT.UL, pT.LR, pT.LR, pT.UL, pT.UL, pT.NO, pT.NO, pT.NO, pT.RD, pT.UL, pT.LD, pT.LR, pT.UD, pT.LR, pT.RD, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(64*2, 88*2, Enums.itemTypes.PERSONAL, null));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("testsub", 60*2, 88*2, 32, 32, 2, 0.25));
			sprites.add(new Sprite("trap", 56*2, 72*2, 32, 32, 1, 1.));
			sprites.add(new Sprite("trap", 72*2, 72*2, 32, 32, 1, 1.));
			sprites.add(new Sprite("security", 88*2, 88*2, 32, 32, 2, 0.25));
			panicSprites = new ArrayList<Sprite>();
			panicSprites.add(new Sprite("trap", 56*2, 72*2, 32, 32, 1, 1.));
			panicSprites.add(new Sprite("trap", 72*2, 72*2, 32, 32, 1, 1.));
			termLocation = new Vec2DPoint(23*2, 87*2);
			backgroundName = "security";
		}
		else if(id == 6)
		{
            pipeData = new pT[]{
            		pT.LD, pT.LD, pT.LR, pT.RD, pT.NO, pT.NO, pT.NO, pT.NO, pT.UL, pT.LD, pT.UL, pT.LR, pT.IT, pT.UD, pT.LR, pT.RD, pT.RD, pT.UL,
            		pT.IT, pT.UL, pT.RD, pT.IT, pT.LR, pT.IT, pT.LR, pT.IT, pT.UD, pT.IT, pT.UL, pT.UD, pT.UR, pT.LD, pT.NO, pT.UD, pT.UD, pT.UD,
            		pT.UR, pT.LR, pT.UR, pT.NO, pT.LD, pT.UL, pT.IT, pT.UD, pT.UD, pT.LR, pT.IT, pT.LR, pT.UD, pT.UD, pT.LD, pT.LR, pT.UR, pT.LR,
            		pT.UR, pT.LD, pT.LR, pT.RD, pT.LR, pT.LR, pT.UL, pT.UR, pT.UR, pT.NO, pT.IT, pT.UL, pT.UD, pT.UR, pT.UL, pT.NO, pT.NO, pT.IT,
            		pT.NO, pT.LR, pT.NO, pT.LR, pT.UD, pT.NO, pT.NO, pT.UR, pT.LR, pT.UD, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR,
            		pT.RD, pT.IT, pT.LR, pT.IT, pT.UD, pT.NO, pT.NO, pT.UD, pT.LD, pT.IT, pT.RD, pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD,
            		pT.LD, pT.UD, pT.LR, pT.LR, pT.UR, pT.NO, pT.NO, pT.UR, pT.UR, pT.UR, pT.UL, pT.UR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UL,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(32*2, 88*2, Enums.itemTypes.GENERIC, Item.fuseLU));
			items.add(new ItemSlot(40*2, 88*2, Enums.itemTypes.GENERIC, Item.fuseLU));
			items.add(new ItemSlot(32*2, 96*2, Enums.itemTypes.GENERIC, Item.fuseRD));
			items.add(new ItemSlot(40*2, 96*2, Enums.itemTypes.GENERIC, Item.fuseRD));
			items.add(new ItemSlot(72*2, 88*2, Enums.itemTypes.GENERIC, Item.fuseLD));
			items.add(new ItemSlot(80*2, 88*2, Enums.itemTypes.GENERIC, Item.fuseLD));
			items.add(new ItemSlot(72*2, 96*2, Enums.itemTypes.GENERIC, Item.fuseUR));
			items.add(new ItemSlot(80*2, 96*2, Enums.itemTypes.GENERIC, Item.fuseUR));
			items.add(new ItemSlot(116*2, 56*2, Enums.itemTypes.CHRONOSTABALIZER, null));
			sprites = new ArrayList<Sprite>();
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(111*2, 87*2);
			backgroundName = "chronoLab";
		}
		else if(id == 7)
		{
            pipeData = new pT[]{
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO,
            		pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(88*2, 88*2, Enums.itemTypes.GENERIC, Item.timecube));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("leut", 124*2, 88*2, 32, 32, 2, 1.));
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(103*2, 87*2);
			backgroundName = "escapePod";
		}
		else
		{
			pipeData = new pT[]{
					pT.LD, pT.NO, pT.NO, pT.NO, pT.RD, pT.LR, pT.LR, pT.NO, pT.NO, pT.NO, pT.NO, pT.LR, pT.LD, pT.NO, pT.NO, pT.RD, pT.LR, pT.LR,
					pT.UR, pT.LD, pT.NO, pT.NO, pT.UR, pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.RD, pT.LR, pT.UL, pT.NO, pT.NO,
					pT.NO, pT.UD, pT.NO, pT.RD, pT.LR, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.UD, pT.RD, pT.LR, pT.LD, pT.NO,
					pT.RD, pT.UL, pT.RD, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UD, pT.UD, pT.UR, pT.LD, pT.UD, pT.NO,
					pT.UR, pT.LR, pT.UL, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UR, pT.LD, pT.NO, pT.UD, pT.IT, pT.NO,
					pT.NO, pT.RD, pT.LD, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UR, pT.LR, pT.UL, pT.UD, pT.NO,
					pT.LR, pT.UL, pT.UR, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.UR, pT.LR,
					pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO, pT.NO};
			items = new ArrayList<ItemSlot>();
			items.add(new ItemSlot(240, 176, Enums.itemTypes.GENERIC, Item.fuseUD));
			items.add(new ItemSlot(56*2,96*2, Enums.itemTypes.GENERIC, Item.mop));
			sprites = new ArrayList<Sprite>();
			sprites.add(new Sprite("timelight", 128, 64, 64, 64, 4, 0.07));
			sprites.add(new Sprite("zap", 100, 45, 64, 64, 4, 0.03));
			sprites.add(new Sprite("zap", 145, 64, 64, 64, 4, 0.02));
			sprites.add(new Sprite("zap", 132, 80, 64, 64, 4, 0.05));
			panicSprites = new ArrayList<Sprite>();
			termLocation = new Vec2DPoint(142, 174);
			backgroundName = "warpCore";
		}
	}

	public static boolean getDoor(int zone)
	{
		if(zone == 7)
			return false;
		return zones[zone].isDoorOpen;
	}

	public static int getMaxWidth(int mode, int zone)
	{
		if(zone == 1 && mode == 1)
		{
			boolean hasItem = (zones[zone].items.get(1).getItem() != null);
			if(!hasItem)
				return 208;
		}
		if(zone == 2 && mode == 1) // kitchen
		{
			boolean hasCheese = (zones[zone].items.get(0).getItem() == Item.cheese) || (zones[zone].items.get(1).getItem() == Item.cheese);
			if(hasCheese)
				return 80;
		}
		if(zone == 3 && mode == 1)
		{
			if(zones[zone].items.get(0).getItem() != Item.cheese)
				return 48;
		}
		if(zone == 5 && mode == 1)
		{
			if(zones[zone].items.get(0).getItem() == null || zones[zone].items.get(0).getItem() != Item.eye)
				return 76*2;
		}
		if(zones[zone].isDoorOpen)
			return GameSettings.GAME_WIDTH+32;
		return DOOR_LIMIT;
	}

	public static boolean getCanGo(int mode, int zone, boolean isRight)
	{
		if(zone != 0 && !isRight)
			return true;
		if(isRight && getMaxWidth(mode, zone) > GameSettings.GAME_WIDTH)
			return true;
		return false;
	}

	public static boolean doesSpriteShow(int mode, int zone, int spriteID)
	{
		if(zone == 1)
		{
			boolean hasHugs = (zones[zone].items.get(1).getItem() != null);
			if(spriteID == 1) // mouse
			{
				return (mode == 1 && hasHugs);
			}
		}
		if(zone == 2)
		{
			boolean hasCheese = (zones[zone].items.get(0).getItem() == Item.cheese) || (zones[zone].items.get(1).getItem() == Item.cheese);
			if(spriteID == 0) // mouse
			{
				return (mode == 1 && hasCheese);
			}
			if(spriteID == 1) // cooking
			{
				return !(mode == 1 && hasCheese);
			}
			if(spriteID == 2) // panic
			{
				return (mode == 1 && hasCheese);
			}
			if(spriteID == 3) // emote panic
			{
				return (mode == 1 && hasCheese);
			}
			if(spriteID == 4) // emote music
			{
				return !(mode == 1 && hasCheese);
			}
		}
		if(zone == 3)
		{
			boolean hasCheese = (zones[zone].items.get(0).getItem() == Item.cheese);
			if(spriteID == 0) // eat
			{
				return (mode == 0 || hasCheese);
			}
			if(spriteID == 1) // dead
			{
				return (mode == 1 && !hasCheese);
			}
			if(spriteID == 2) // emote death
			{
				return (mode == 1 && !hasCheese);
			}
		}
		if(zone == 4)
		{
			if(spriteID == 0 && mode == 1 && (zones[5].items.get(0).getItem() != null && zones[5].items.get(0).getItem() == Item.eye))
				return false;
		}
		if(zone == 5)
		{
			if((spriteID == 0 || spriteID == 1 || spriteID == 2) && mode == 0)
				return false;
			if((spriteID == 0 || spriteID == 1 || spriteID == 2) && mode == 1 && (zones[zone].items.get(0).getItem() == null || zones[zone].items.get(0).getItem() != Item.eye))
				return false;
		}
		return true;
	}

	private int getItemX(int slot)
	{
		return items.get(slot).x;
	}

	private int getItemY(int slot)
	{
		return items.get(slot).y;
	}

	public static Vec2DPoint getSlotVec(int zone, int slot)
	{
		return new Vec2DPoint(zones[zone].getItemX(slot), zones[zone].getItemY(slot));
	}

	public static pT[] getPipes(int zone)
	{
		return zones[zone].pipeData;
	}

	public static void setPipe(int zone, int index, pT state)
	{
		zones[zone].pipeData[index] = state;
	}

	public static int numSlots(int zone)
	{
		return zones[zone].items.size();
	}

	public static void update(int zone, double frameTime)
	{
		for(int i = 0; i < zones[zone].sprites.size(); ++i)
		{
			zones[zone].sprites.get(i).update(frameTime);
		}
		for(int i = 0; i < zones[zone].panicSprites.size(); ++i)
		{
			zones[zone].panicSprites.get(i).update(frameTime);
		}
	}

	public static void draw(int mode, int zone)
	{
		for(int i = 0; i < zones[zone].items.size(); ++i)
		{
			Item item = zones[zone].items.get(i).getItem();
			if(item != null)
				item.drawAtSlot(zone, i);
		}		
		for(int i = 0; i < zones[zone].sprites.size(); ++i)
		{
			if(doesSpriteShow(mode, zone, i))
				zones[zone].sprites.get(i).draw();
		}
	}
	public static void drawPanic(int zone)
	{
		for(int i = 0; i < zones[zone].panicSprites.size(); ++i)
		{
			zones[zone].panicSprites.get(i).draw();
		}
	}

	public static String getBackground(int zone)
	{
		return zones[zone].backgroundName;
	}

	public static Vec2DPoint getTermLocation(int zone)
	{
		return zones[zone].termLocation;
	}

	public static Item getItemInSlot(int zone, int slot)
	{
		return zones[zone].items.get(slot).getItem();
	}

	public static void setItemInSlot(int zone, int slot, Item item)
	{
		zones[zone].items.get(slot).setItem(item);
	}

	public static void openDoor(int zone)
	{
		zones[zone].isDoorOpen = true;
	}

	public static itemTypes getSlotType(int zone, int i)
	{
		return zones[zone].items.get(i).getType();
	}

}
