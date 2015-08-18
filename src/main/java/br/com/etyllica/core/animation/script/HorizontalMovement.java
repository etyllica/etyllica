package br.com.etyllica.core.animation.script;

import br.com.etyllica.layer.Layer;


public class HorizontalMovement extends SingleIntervalAnimation {
		
	public HorizontalMovement(long delay, long time) {
		super(delay, time);
	}
	
	public HorizontalMovement(Layer target, long time) {
		super(target, time);
	}

	@Override
	protected void update(double value) {
		target.setX((int)value);
	}

}
