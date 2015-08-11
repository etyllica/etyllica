package br.com.abby.core.light;

import br.com.abby.linear.AimPoint;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Lamp extends AimPoint {
	
	protected float intensity;
	
	public Lamp(double x, double y, double z) {
		super(x,y,z);
	}
	
	public float getIntensity() {
		return intensity;
	}
}
