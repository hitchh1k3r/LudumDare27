package com.hitchh1k3rsguide.ld27.graphics;

public class TextRenderer
{

	public static SpriteFromSpriteSheet text = new SpriteFromSpriteSheet(SpriteSheet.font, "", 0, 0, 8, 8);

	public static void renderText(String message, Color color, int x, int y, int width)
	{
		int ox = x;
		String[] words = message.toLowerCase().split(" ");
		for(String word : words)
		{
			if(width != 0 && (x-ox)+(word.length()*9) > width)
			{
				y += 9;
				x = ox;
			}
			for(int l = 0; l < word.length(); ++l)
			{
				char letter = word.charAt(l);
				if(letter == '\n')
				{
					x = ox;
					y += 9;
				}
				else
				{
					color.BLACK.applyColorGL();
					text.setSprite("char."+letter);
					text.drawAt(x+1, y+1);
					color.applyColorGL();
					text.setSprite("char."+letter);
					text.drawAt(x, y);
					x += 9;
				}
			}
			x += 4;
		}
		color.WHITE.applyColorGL();
	}

}
