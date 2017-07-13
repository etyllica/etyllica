package br.com.etyllica.core.animation.script.complex;

import br.com.etyllica.core.animation.script.RepeatedScript;
import br.com.etyllica.commons.layer.Layer;

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
