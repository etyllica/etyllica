package br.com.etyllica.core.animation.script;

import br.com.etyllica.layer.Layer;


public class FadeOutAnimation extends OpacityAnimation {
	
	{
		startValue = 255;
		endValue = 0;
	}
	
	public FadeOutAnimation(long time){
		super(time);				
	}
	
	public FadeOutAnimation(long delay, long time){
		super(delay, time);
	}
	
	public FadeOutAnimation(Layer target, long time){
		super(target, time);
	}
	
	public FadeOutAnimation(Layer target, long delay, long time){
		super(target, delay, time);
	}
	
	public FadeOutAnimation(Layer target) {
		super(target);
	}

	@Override
	public void setTarget(Layer target) {
		super.setTarget(target);
		target.setOpacity(255);
	}
	
	@Override
	protected void update(double value){
		target.setOpacity((int)value);
	}	

}
