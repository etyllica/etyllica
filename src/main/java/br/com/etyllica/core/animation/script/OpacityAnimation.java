package br.com.etyllica.core.animation.script;

import br.com.etyllica.commons.layer.Layer;


public class OpacityAnimation extends SingleIntervalAnimation {
	
	public OpacityAnimation(Layer target) {
		super(target);
	}
	
	public OpacityAnimation(long time) {
		super(time);
		setInterval(0, 255);
	}
	
	public OpacityAnimation(long delay, long time) {
		super(delay, time);
		setInterval(0, 255);
	}
	
	public OpacityAnimation(Layer target, long time) {
		super(target, time);
		setInterval(0, 255);
	}
	
	public OpacityAnimation(Layer target, long delay, long time) {
		super(target, delay, time);
		setInterval(0, 255);
	}
	
	@Override
	protected void update(double value) {
		target.setOpacity((int)value);
	}

}
