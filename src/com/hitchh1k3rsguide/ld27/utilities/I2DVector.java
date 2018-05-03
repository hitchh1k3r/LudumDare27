package com.hitchh1k3rsguide.ld27.utilities;

public interface I2DVector
{

	public double getDX();
	public double getDY();
	public double getAngle();
	public double getMagnitude();
	public double getSquareMagnitude();
	public double getDotProduct(I2DVector vector);
	public void scale(double scalar);
	public void addVector(I2DVector vector);
	public void subtractVector(I2DVector vector);
	public void rotateVector(double angle);
	public void setPoint(double x, double y);
	public void setPoint(Vec2DPoint point);
	public void setDX(double x);
	public void setDY(double y);
	public void setAngle(double angle);
	public void setMagnitude(double magnitude);

}
