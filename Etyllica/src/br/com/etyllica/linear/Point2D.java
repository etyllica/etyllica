package br.com.etyllica.linear;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point2D {

	protected String name = "";
	
	protected double x = 0.0;
	
	protected double y = 0.0;
	
	protected int color = 0x000000;//Black Hex Value

	public Point2D() {
		super();
		setLocation(0, 0);
	}
	
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
		setLocation(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setOffset(double x, double y) {
		setOffsetX(x);
		setOffsetY(y);
	}
	
	public void setOffsetX(double x) {
		this.x += x;
	}

	public void setOffsetY(double y) {
		this.y += y;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public void rotate(double cx, double cy, double degreeAngle) {
		
		double angle = Math.toRadians(degreeAngle);
		
		double nx = cx + (x-cx)*Math.cos(angle) - (y-cy)*Math.sin(angle);
		
		double ny = cy + (x-cx)*Math.sin(angle) + (y-cy)*Math.cos(angle);
		
		x = nx;
		y = ny;
	}
	
	public static boolean isRightTurn(Point2D a, Point2D b, Point2D c) {
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x) > 0;
	}

	public double distance(Point2D point) {
		
		return distance(point.getX(), point.getY());
	}
	
	public double distance(double px, double py) {
		
		double difX = px - this.x;
		double difY = py - this.y;
		
		double dist = Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));
				
		return dist;
	}
	
	public static Point2D clone(Point2D point) {
		return new Point2D(point.getX(), point.getY());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point2D other = (Point2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
