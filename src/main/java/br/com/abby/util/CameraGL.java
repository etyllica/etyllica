package br.com.abby.util;

import br.com.abby.linear.ColoredPoint3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CameraGL extends ColoredPoint3D {
	
	private ColoredPoint3D target;
	
	public CameraGL(double x, double y, double z) {
		super(x, y, z);
		target = new ColoredPoint3D(0,0,0);
	}
	
	public ColoredPoint3D getTarget(){
		return target;
	}
	
	public void setTarget(ColoredPoint3D target){
		this.target = target;
	}
	
	public void setTarget(double x, double y, double z){
		target.setCoordinates(x, y, z);
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
