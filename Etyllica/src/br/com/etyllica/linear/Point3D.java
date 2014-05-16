package br.com.etyllica.linear;

public class Point3D {

	protected double x;
	
	protected double y;
	
	protected double z;
	
	public Point3D(){
		this(0,0,0);
	}
	
	public Point3D(double x, double y, double z){
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

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void setCoordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
		
}
