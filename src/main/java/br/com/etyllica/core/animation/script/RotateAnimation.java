package br.com.etyllica.core.animation.script;

import br.com.etyllica.layer.Layer;


public class RotateAnimation extends SingleIntervalAnimation {
		
	public RotateAnimation(long time) {
		super(time);
	}
	
	public RotateAnimation(long delay, long time) {
		super(delay, time);
	}
	
	public RotateAnimation(Layer target, long time) {
		super(target, time);
	}
	
	public RotateAnimation(Layer target) {
		super(target);
	}
		
	@Override
	public void update(double value) {
		target.setAngle(value);		
	}
}
