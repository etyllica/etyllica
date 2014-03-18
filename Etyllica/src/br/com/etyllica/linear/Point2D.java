package br.com.etyllica.linear;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point2D extends java.awt.geom.Point2D {

	protected String name;
	
	protected double x;
	
	protected double y;
	
	protected int cor;

	public Point2D(double x, double y, int color) {
		this.x = x;
		this.y = y;
		this.cor = color;
		this.name = "";
	}

	public Point2D(double x, double y, String nome) {
		this.x = x;
		this.y = y;
		this.name = nome;
		this.cor = 0;
	}

	public Point2D(double x, double y) {
		this(x,y,"");
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
		
		//From Peter O.'s Answer 
		//http://stackoverflow.com/questions/7586063/how-to-calculate-the-angle-between-two-points-relative-to-the-horizontal-axis
		
		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return angleInDegrees;
	}
	
}
