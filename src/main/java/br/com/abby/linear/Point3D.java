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
						
		color = Color.BLACK;
	}
	
	public Point3D(br.com.etyllica.linear.Point3D point) {
		this(point.getX(), point.getY(), point.getZ());			
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
	

	public Point3D distantPoint(Point3D target, double distance) {
		
		double deltaX = x-target.getX();
		double deltaY = y-target.getY();
		double deltaZ = z-target.getZ();
		
		double dist = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
		
		deltaX /= dist;
		deltaY /= dist;
		deltaZ /= dist;
				
		Point3D c = new Point3D(x-distance*deltaX, y-distance*deltaY, z-distance*deltaZ);

		return c;
	}

	@Override
	public String toString() {
		return x+" , "+y+" , "+z;
	}
	
}
