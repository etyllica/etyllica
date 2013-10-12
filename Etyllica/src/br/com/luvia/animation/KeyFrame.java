package br.com.luvia.animation;

public class KeyFrame {

	private double x = 0;
	
	private double y = 0;
	
	private double z = 0;

	public KeyFrame(double x, double y, double z){
		
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
	
	public void setOffsetX(double x) {
		this.x += x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setOffsetY(double y) {
		this.y += y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void setOffsetZ(double z) {
		this.z += z;
	}
		
}
