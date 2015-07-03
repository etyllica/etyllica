package br.com.abby.util;

import br.com.abby.linear.ColoredPoint3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Lamp extends ColoredPoint3D{
	
	protected float intensity;
	
	public Lamp(double x, double y, double z){
		super(x,y,z);
	}
	
	public float getIntensidade(){
		return intensity;
	}

}
