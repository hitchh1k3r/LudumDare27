package com.hitchh1k3rsguide.ld27.utilities;

public class Vec2DPoint implements I2DVector
{

	private double x, y;

	public Vec2DPoint(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2DPoint(I2DVector copyMe)
	{
		this.x = copyMe.getDX();
		this.y = copyMe.getDY();
	}

	@Override
	public double getDX()
	{
		return x;
	}

	@Override
	public double getDY()
	{
		return y;
	}

	@Override
	public double getDotProduct(I2DVector other)
	{
		return (x * y) + (other.getDX() * other.getDY());
	}

	@Override
	public double getSquareMagnitude()
	{
		return (x *x) + (y * y);
	}

	@Override
	public double getAngle()
	{
		return Math.atan2(y, x);
	}

	@Override
	public double getMagnitude()
	{
		return Math.sqrt(getSquareMagnitude());
	}

	@Override
	public void scale(double scalar)
	{
		x *= scalar;
		y *= scalar;
	}

	@Override
	public void addVector(I2DVector vector)
	{
		x += vector.getDX();
		y += vector.getDY();
	}

	@Override
	public void subtractVector(I2DVector vector)
	{
		x -= vector.getDX();
		y -= vector.getDY();
	}

	@Override
	public void rotateVector(double angle)
	{
		double cos = Math.cos(angle);
		double sin = Math.cos(angle);
		double rX = (x * cos) - (y * sin);
		double rY = (y * sin) + (y * cos);
		x = rX;
		y = rY;
	}

	@Override
	public void setDX(double x)
	{
		this.x = x;
	}

	@Override
	public void setDY(double y)
	{
		this.y = y;
	}

	@Override
	public void setAngle(double angle)
	{
		this.x = Math.cos(angle);
		this.y = Math.sin(angle);
	}

	@Override
	public void setMagnitude(double magnitude)
	{
		scale(magnitude/getMagnitude());
	}

	@Override
	public void setPoint(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void setPoint(Vec2DPoint point)
	{
		this.x = point.x;
		this.y = point.y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof I2DVector))
			return false;
		I2DVector other = (I2DVector)obj;
	    return (x == other.getDX() && y == other.getDY());
	}

}
