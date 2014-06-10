package br.com.abby.util;

import br.com.abby.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Lamp extends Point3D{
	
	protected float intensity;
	
	public Lamp(double x, double y, double z){
		super(x,y,z);
	}
	
	public float getIntensidade(){
		return intensity;
	}

}
