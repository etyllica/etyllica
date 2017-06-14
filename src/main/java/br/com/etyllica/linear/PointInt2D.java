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
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		PointInt2D other = (PointInt2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
