package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;

public abstract class SingleIntervalAnimation extends AnimationScript {

	protected Layer target;
	
	protected double startValue = 0;
	
	protected double endValue = 0;
	
	public SingleIntervalAnimation(long time) {
		super(time);
	}

	public SingleIntervalAnimation(long delay, long time) {
		super(delay, time);
	}

	public SingleIntervalAnimation(Layer target, long time) {
		super(time);
		
		setTarget(target);
	}
	
	public SingleIntervalAnimation(Layer target, long delay, long time) {
		super(delay, time);
		
		setTarget(target);
	}
	
	public void calculate(double factor) {
		
		double value = startValue+(endValue-startValue)*factor;

		update(value);
		
	}
	
	protected abstract void update(double value);
	
	public void setInterval(double startValue, double endValue) {
		this.startValue = startValue;
		this.endValue = endValue;
		
		calculate(0);
	}

	public Layer getTarget() {
		return target;
	}

	public void setTarget(Layer target) {
		this.target = target;
	}
	
}