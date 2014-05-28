package br.com.luvia.util;

import br.com.luvia.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CameraGL extends Point3D {
	
	private Point3D target;
	
	public CameraGL(double x, double y, double z) {
		super(x, y, z);
		target = new Point3D(0,0,0);
	}
	
	public Point3D getTarget(){
		return target;
	}
	
	public void setTarget(Point3D target){
		this.target = target;
	}
	
	public void setTarget(double x, double y, double z){
		target.setCoordinates(x, y, z);
	}	
	
}
