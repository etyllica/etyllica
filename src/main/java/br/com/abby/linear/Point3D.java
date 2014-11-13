package br.com.abby.linear;

import java.awt.Color;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Point3D extends br.com.etyllica.linear.Point3D {
	
	protected Color color;
	
	public Point3D() {
		super();
	}
	
	public Point3D(double x, double y) {
		super(x, y, 0);
	}
	public Point3D(double x, double y, double z) {
		super(x, y, z);
						
		color = Color.WHITE;
	}

	public void setCoordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoordinates(double x, double y, double z){
		setCoordinates(x, y);
		this.z = z;
	}

	public void setOffsetX(double x) {
		this.x += x;
	}

	public void setOffsetY(double y) {
		this.y += y;
	}
	
	public void setOffsetZ(double z) {
		this.z += z;
	}
		
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString(){
		return x+" , "+y+" , "+z;
	}

}
