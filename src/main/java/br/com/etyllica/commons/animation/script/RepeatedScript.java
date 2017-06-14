package br.com.etyllica.commons.animation.script;

import br.com.etyllica.layer.Layer;

public abstract class RepeatedScript extends AnimationScript {

	protected Layer target;
	
	protected int repeatTimes = 1;
		
	public RepeatedScript(Layer target, long time) {
		super(time);
		
		this.target = target;
	}

	public void repeat(int times) {
		repeatTimes = times;
	}
	
}
