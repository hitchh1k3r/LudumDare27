package com.hitchh1k3rsguide.ld27.utilities;

public class Vec2DAngle implements I2DVector
{

	private double angle, magnitude;

	public Vec2DAngle(double angle, double magnitude)
	{
		this.angle = angle;
		this.magnitude = magnitude;
	}

	public Vec2DAngle(I2DVector copyMe)
	{
		this.angle = copyMe.getAngle();
		this.magnitude = copyMe.getMagnitude();
	}

	@Override
	public double getDX()
	{
		return magnitude*Math.cos(angle);
	}

	@Override
	public double getDY()
	{
		return magnitude*Math.sin(angle);
	}

	@Override
	public double getAngle()
	{
		return angle;
	}

	@Override
	public double getMagnitude()
	{
		return magnitude;
	}

	@Override
	public double getSquareMagnitude()
	{
		return magnitude * magnitude;
	}

	@Override
	public double getDotProduct(I2DVector vector)
	{
		return magnitude * vector.getMagnitude() * Math.cos(Math.abs(angle - vector.getAngle()));
	}

	@Override
	public void scale(double scalar)
	{
		magnitude *= scalar;
	}

	@Override
	public void addVector(I2DVector other)
	{
		double x = getDX() + other.getDX();
		double y = getDY() + other.getDY();
		setDX(x);
		setDX(y);
	}

	@Override
	public void subtractVector(I2DVector other)
	{
		Vec2DPoint inverseOther = new Vec2DPoint(other);
		inverseOther.scale(-1);
		addVector(inverseOther);
	}

	@Override
	public void rotateVector(double angle)
	{
		this.angle += angle;
		while(this.angle < 0)
			this.angle += 2*Math.PI;
		while(this.angle > 2*Math.PI)
			this.angle -= 2*Math.PI;
	}

	@Override
	public void setDX(double x)
	{
		setPoint(x, getDY());
	}

	@Override
	public void setDY(double y)
	{
		setPoint(getDX(), y);
	}

	@Override
	public void setAngle(double angle)
	{
		this.angle = angle;
	}

	@Override
	public void setMagnitude(double magnitude)
	{
		this.magnitude = magnitude;
	}

	@Override
	public void setPoint(double x, double y)
	{
		setPoint(new Vec2DPoint(x, y));
	}

	@Override
	public void setPoint(Vec2DPoint point)
	{
		this.angle = point.getAngle();
		this.magnitude = point.getMagnitude();
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof I2DVector))
			return false;
		I2DVector other = (I2DVector)obj;
	    return (angle == other.getAngle() && magnitude == other.getMagnitude());
	}

}
