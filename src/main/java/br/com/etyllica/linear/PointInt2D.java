package br.com.etyllica.linear;

public class PointInt2D {

	protected int x = 0;
	
	protected int y = 0;
	
	public PointInt2D() {
		super();
	}
	
	public PointInt2D(int x, int y) {
		super();
		
		setLocation(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setLocation(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
