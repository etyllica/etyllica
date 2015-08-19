package br.com.etyllica.core.animation.script;

import br.com.etyllica.core.animation.LayerAnimation;
import br.com.etyllica.layer.Layer;

public abstract class SingleIntervalAnimation extends LayerAnimation {
		
	protected double startValue = 0;
	protected double endValue = 0;
	
	public SingleIntervalAnimation(Layer target) {
		super(target);
	}
	
	public SingleIntervalAnimation(long time) {
		super(time);
	}
	
	public SingleIntervalAnimation(long delay, long time) {
		super(delay, time);
	}

	public SingleIntervalAnimation(Layer target, long time) {
		super(target, time);		
	}
	
	public SingleIntervalAnimation(Layer target, long delay, long time) {
		super(delay, time);
		
		setTarget(target);
	}
	
	public void calculate(double x) {
		double value = interpolator.factor(startValue, endValue, x);
		update(value);
	}
	
	public SingleIntervalAnimation from(double value) {
		startValue = value;
		return this;
	}
	
	public SingleIntervalAnimation to(double value) {
		endValue = value;
		return this;
	}
	
	protected abstract void update(double value);
	
	public void setInterval(double startValue, double endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
		
		//Update for the first value
		if (target != null) {
			calculate(0);
		}
	}	
}
