package com.hitchh1k3rsguide.ld27.graphics;

import org.lwjgl.opengl.GL11;

public class Color
{

	double red, blue, green, alpha;

	public static final Color BLACK = new Color(0., 0., 0.);
	public static final Color WHITE = new Color(1., 1., 1.);
	public static final Color GRAY = new Color(0.5, 0.5, 0.5);
	public static final Color DARKGRAY = new Color(0.25, 0.25, 0.25);
	public static final Color LIGHTGRAY = new Color(0.75, 0.75, 0.75);
	public static final Color RED = new Color(1., 0., 0.);
	public static final Color GREEN = new Color(0., 1., 0.);
	public static final Color BLUE = new Color(0., 0., 1.);
	public static final Color YELLOW = new Color(1., 1., 0.);
	public static final Color ORANGE = new Color(1., 0.5, 0.);
	public static final Color DARKBLUE = new Color(0., 0., 0.5);

	public Color(int red, int green, int blue)
	{
		this.red = (double)red/255;
		this.blue = (double)blue/255;
		this.green = (double)green/255;
		this.alpha = 1;
	}

	public Color(int red, int green, int blue, int alpha)
	{
		this.red = (double)red/255;
		this.blue = (double)blue/255;
		this.green = (double)green/255;
		this.alpha = (double)alpha/255;
	}

	public Color(double red, double green, double blue)
	{
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = 1;
	}

	public Color(double red, double green, double blue, double alpha)
	{
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = alpha;
	}

	public Color(Color copyMe)
	{
		red = copyMe.red;
		green = copyMe.green;
		blue = copyMe.blue;
		alpha = copyMe.alpha;
	}

	public int getRedInt()
	{
		return (int) Math.round(red*255);
	}

	public int getBlueInt()
	{
		return (int) Math.round(blue*255);
	}

	public int getGreenInt()
	{
		return (int) Math.round(green*255);
	}

	public double getRedDouble()
	{
		return red;
	}

	public double getBlueDouble()
	{
		return blue;
	}

	public double getGreenDouble()
	{
		return green;
	}

	public void applyColorGL()
	{
		if(alpha == 1.)
			GL11.glColor3d(red, green, blue);
		else
			GL11.glColor4d(red, green, blue, alpha);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Color))
			return false;
		Color other = (Color)obj;
	    return (red == other.red && blue == other.blue && green == other.green && alpha == other.alpha);
	}

	public void setAlpha(double d)
	{
		alpha = d;
	}

}