package com.hitchh1k3rsguide.ld27.gameplay;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.Enums.itemTypes;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;
import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class Item
{

	public static final Item cheese = new Item("item.cheese", Enums.itemTypes.FOOD);
	public static final Item burger = new Item("item.burger", Enums.itemTypes.FOOD);
	public static final Item chicken = new Item("item.chicken", Enums.itemTypes.FOOD);
	public static final Item sushi = new Item("item.sushi", Enums.itemTypes.FOOD);
	public static final Item fuseLR = new Item("item.fuseLR", Enums.itemTypes.CIRCUIT);
	public static final Item fuseUD = new Item("item.fuseUD", Enums.itemTypes.CIRCUIT);
	public static final Item fuseULRD = new Item("item.fuseULRD", Enums.itemTypes.CIRCUIT);
	public static final Item fuseUR = new Item("item.fuseUR", Enums.itemTypes.CIRCUIT);
	public static final Item fuseLU = new Item("item.fuseLU", Enums.itemTypes.CIRCUIT);
	public static final Item fuseRD = new Item("item.fuseRD", Enums.itemTypes.CIRCUIT);
	public static final Item fuseLD = new Item("item.fuseLD", Enums.itemTypes.CIRCUIT);
	public static final Item radio = new Item("item.radio", Enums.itemTypes.OTHER);
	public static final Item wrench = new Item("item.wrench", Enums.itemTypes.PERSONAL);
	public static final Item mop = new Item("item.mop", Enums.itemTypes.OTHER);
	public static final Item eye = new Item("item.eye", Enums.itemTypes.PERSONAL);
	public static final Item timecube = new Item("item.timecube", Enums.itemTypes.CHRONOSTABALIZER);

	private SpriteFromSpriteSheet sprite;
	private Enums.itemTypes type;

	public Item(String sprite, Enums.itemTypes type)
	{
		this.sprite = new SpriteFromSpriteSheet(SpriteSheet.sprites, sprite, 0, 0, 16, 16);
		this.type = type;
	}

	public void drawAtSlot(int zone, int slot)
	{
		Vec2DPoint point = ZoneData.getSlotVec(zone, slot);
		sprite.drawAt((int)point.getDX(), (int)point.getDY());
	}

	public void drawAt(int x, int y)
	{
		sprite.drawAt(x, y);
	}

	public itemTypes getType()
	{
		return type;
	}

}
