package br.com.abby.linear;

import br.com.etyllica.core.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Camera extends ColoredPoint3D {
	
	private Point3D target;
	private Point3D normal;
	
	public Camera(double x, double y, double z) {
		super(x, y, z);
		target = new Point3D(0,0,0);
		normal = new Point3D(0,1,0);
	}
	
	public Point3D getTarget() {
		return target;
	}
		
	public void setTarget(Point3D target) {
		this.target = target;
	}
		
	public void setTarget(double x, double y, double z) {
		target.setCoordinates(x, y, z);
	}
		
	public Point3D getNormal() {
		return normal;
	}

	public void setNormal(Point3D normal) {
		this.normal = normal;
	}

	public double angleXY() {
		return angle(x, target.getX(), y, target.getY());
	}
	
	public double angleXZ() {
		return angle(x, target.getX(), z, target.getZ());
	}
	
	public double angleYZ() {
		return angle(y, target.getY(), z, target.getZ());
	}
}
