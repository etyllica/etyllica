package br.com.etyllica.commons.animation.script;

import br.com.etyllica.layer.Layer;


public class HorizontalMovementScript extends SingleIntervalAnimation {
	
	public HorizontalMovementScript(Layer target) {
		super(target);
	}
	
	public HorizontalMovementScript(long delay, long time) {
		super(delay, time);
	}
		
	public HorizontalMovementScript(Layer target, long time) {
		super(target, time);
	}

	@Override
	protected void update(double value) {
		target.setX((int)value);
	}

}
