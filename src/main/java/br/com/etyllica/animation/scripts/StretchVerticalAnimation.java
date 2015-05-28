package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;


public class StretchVerticalAnimation extends SingleIntervalAnimation {
		
	private int originalY;
	
	public StretchVerticalAnimation(long time) {
		super(time);
	}
	
	public StretchVerticalAnimation(long delay, long time) {
		super(delay, time);
	}
	
	public StretchVerticalAnimation(Layer target, long time) {
		super(target, time);
	}
	
	@Override
	public void setTarget(Layer target) {
		super.setTarget(target);
		originalY = target.getY();
	}
		
	@Override
	public void update(double value) {
		target.setScaleY(value);
		target.setY((int)(originalY-(target.getH()/2)*value));
	}	

}
