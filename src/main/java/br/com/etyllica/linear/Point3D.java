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

	public Point3D(Point3D point) {
		this(point.x, point.y, point.z);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void offsetX(double offset) {
		this.x += offset;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void offsetY(double offset) {
		this.y += offset;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void offsetZ(double offset) {
		this.z += offset;
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

	public double angleXY(Point3D point) {
		return angleXY(point.getX(), point.getY());
	}
	
	public double angleXY(double px, double py) {
		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

		return angleInDegrees;
	}
	
	public double angleXZ(Point3D point) {
		return angleXZ(point.getX(), point.getZ());
	}
	
	public double angleXZ(double px, double pz) {
		double deltaX = px - x;
		double deltaZ = pz - z;

		double angleInDegrees = Math.atan2(deltaZ, deltaX) * 180 / Math.PI;

		return angleInDegrees;
	}
	
	public double angleYZ(Point3D point) {
		return angleYZ(point.getX(), point.getZ());
	}
	
	public double angleYZ(double py, double pz) {
		double deltaY = py - y;
		double deltaZ = pz - z;

		double angleInDegrees = Math.atan2(deltaZ, deltaY) * 180 / Math.PI;

		return angleInDegrees;
	}

	protected double angle(double x, double px, double y, double py) {

		double deltaX = px - x;
		double deltaY = py - y;

		double angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI;

		return angleInDegrees;		
	}
	
}
