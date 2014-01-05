package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;


public class FadeOutAnimation extends OpacityAnimation{
		
	public FadeOutAnimation(long time){
		super(time);
		
		startValue = 255;
		endValue = 0;
		
	}
	
	public FadeOutAnimation(long delay, long time){
		super(delay, time);
		
		startValue = 255;
		endValue = 0;
	}
	
	public FadeOutAnimation(Layer target, long time){
		super(target, time);
		
		startValue = 255;
		endValue = 0;
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
