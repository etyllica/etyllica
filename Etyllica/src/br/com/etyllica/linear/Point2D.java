package br.com.etyllica.linear;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point2D extends java.awt.geom.Point2D {

	protected String name = "";
	
	protected double x = 0.0;
	
	protected double y = 0.0;
	
	protected int color = 0x000000;//Black Hex Value

	public Point2D(double x, double y, int color) {
		this(x,y);
		this.color = color;
	}

	public Point2D(double x, double y, String name) {
		this(x,y);
		this.name = name;
	}

	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setOffsetX(double x) {
		this.x += x;
	}

	public void setOffsetY(double y) {
		this.y += y;
	}
	
	public double angle(Point2D point) {

		return angle(point.getX(), point.getY());
		
	}
	
	public double angle(double px, double py) {
				
		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return angleInDegrees;
		
	}
	
}
