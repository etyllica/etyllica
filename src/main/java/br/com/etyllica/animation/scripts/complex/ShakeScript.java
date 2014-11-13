package br.com.etyllica.animation.scripts.complex;

import br.com.etyllica.animation.scripts.RepeatedScript;
import br.com.etyllica.layer.Layer;

public abstract class ShakeScript extends RepeatedScript {

	protected int strength = 10;
	
	public ShakeScript(Layer target, long time) {
		super(target, time);		
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
