package br.com.luvia.util;

import br.com.etyllica.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Camera extends Point3D {

	private Point3D alvo;
	
	public Camera(double x, double y, double z){
		super(x, y, z);
		alvo = new Point3D(1,0,0);
	}
	
	public Point3D getTarget(){
		return alvo;
	}
	public void setTarget(double x, double y, double z){
		alvo.setCoordinates(x, y, z);
	}
	
}
