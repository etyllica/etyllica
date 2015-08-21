package br.com.etyllica.linear;

import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.util.EtyllicaMath;

public class Ellipse {

	protected Point2D center;
	
	protected int w = 1;
	protected int h = 1;
	
	protected double angle = 0;
	
	public Ellipse(int x, int y) {
		super();
		this.center = new Point2D(x, y);
	}
	
	public Ellipse(int x, int y, int w, int h) {
		this(x, y);
		this.w = w;
		this.h = h;
	}

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public boolean contains(double px, double py) {
		double p = EtyllicaMath.sqr(Math.cos(angle)*(px-center.getX())+Math.sin(angle)*(py-center.getY()))/(w*w);
		double q = EtyllicaMath.sqr(Math.sin(angle)*(px-center.getX())-Math.cos(angle)*(py-center.getY()))/(h*h);
		
		return (p + q <= 1);
	}
			
}
