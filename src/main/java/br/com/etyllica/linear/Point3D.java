package br.com.etyllica.linear;

public class Point3D {

	protected double x;
	
	protected double y;
	
	protected double z;
	
	public Point3D() {
		this(0,0,0);
	}
	
	public Point3D(double x, double y, double z) {
		super();
		
		this.x = x;
		this.y = y;
		this.z = z;				
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void offsetX(double offset) {
		this.x += x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void offsetY(double offset) {
		this.y += y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void offsetZ(double offset) {
		this.z += z;
	}

	public void setCoordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double angle(int px, int py) {
		return angle(x, px, y, py);		
	}
	
	public double angle(Point2D point) {
		return angle(x, point.getX(), y, point.getY());
	}
		
	public double angle2D(Point3D point) {
		return angle(x, point.getX(), y, point.getY());
	}
		
	protected double angle(double x, double px, double y, double py) {
				
		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		
		return angleInDegrees;		
	}
	
}
