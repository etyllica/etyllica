package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;


public class VerticalMovementScript extends SingleIntervalAnimation {
		
	public VerticalMovementScript(long time) {
		super(time);
	}
	
	public VerticalMovementScript(Layer target, long time) {
		super(target, time);
	}
		
	@Override
	protected void update(double value) {
		target.setY((int)value);
	}

}
