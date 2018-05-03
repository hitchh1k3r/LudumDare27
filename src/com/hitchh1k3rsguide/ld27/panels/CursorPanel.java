package com.hitchh1k3rsguide.ld27.panels;

import org.lwjgl.input.Mouse;

import com.hitchh1k3rsguide.ld27.Enums;
import com.hitchh1k3rsguide.ld27.gameplay.Item;
import com.hitchh1k3rsguide.ld27.graphics.SpriteFromSpriteSheet;
import com.hitchh1k3rsguide.ld27.graphics.SpriteSheet;

public class CursorPanel implements IPanel
{

	SpriteFromSpriteSheet cursor;
	int mX, mY;
	boolean hidden = true;
	Enums.cursors type;
	private Item itemInHand;

	public CursorPanel()
	{
		cursor = new SpriteFromSpriteSheet(SpriteSheet.sprites, "cursor.normal", 0, 0, 32, 32);
		type = Enums.cursors.NONE;
		itemInHand = null;
	}

	@Override
	public boolean onKey(int key, boolean isDown) {return false;}

	@Override
	public boolean onMouseMove(int x, int y)
	{
		mX = x;
		mY = y;
		hidden = false;
		return false;
	}

	@Override
	public boolean onMouseClick(int x, int y, int button, boolean isDown) {return false;}

	@Override
	public void update(double time)
	{
	}

	@Override
	public void draw()
	{
		if(!hidden && Mouse.isInsideWindow() && type != Enums.cursors.NONE)
		{
			cursor.drawAt(mX, mY);
			if(itemInHand != null)
			{
				itemInHand.drawAt(mX, mY);
			}
		}
	}

	public void hideCursor()
	{
		hidden = true;
	}

	@Override
	public void onAttach()
	{
		
	}

	@Override
	public void onDetach()
	{
		
	}

	public void setCursor(Enums.cursors normal)
	{
		type = normal;
		if(type == Enums.cursors.NORMAL)
			cursor.setSprite("cursor.normal");
		else if(type == Enums.cursors.GRAB || type == Enums.cursors.ITEM)
			cursor.setSprite("cursor.grab");
		else if(type == Enums.cursors.LOOK)
			cursor.setSprite("cursor.look");
		else if(type == Enums.cursors.MOP)
			cursor.setSprite("cursor.mop");
		else if(type == Enums.cursors.WALK)
			cursor.setSprite("cursor.walk");
	}

	public Enums.cursors getCursor()
	{
		return type;
	}

	public void setItem(Item itemInHand)
	{
		this.itemInHand = itemInHand;
	}

}
