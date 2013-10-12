package br.com.etyllica.linear.vector;

public class Vector2D {

	protected double x;
	protected double y;
	
	public Vector2D(double x, double y){
		super();
		this.x = x;
		this.y = y;
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
	
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
