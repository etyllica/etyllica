package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;


public class FadeInAnimation extends OpacityAnimation{
		
	public FadeInAnimation(long time){
		super(time);
		
		startValue = 0;
		endValue = 255;
		
	}
	
	public FadeInAnimation(long delay, long time){
		super(delay, time);
		
		startValue = 0;
		endValue = 100;
	}
	
	public FadeInAnimation(Layer target, long time){
		super(target, time);
		
		startValue = 0;
		endValue = 100;
	}
	
	@Override
	public void setTarget(Layer target) {
		super.setTarget(target);
		target.setOpacity(0);
	}
	
	@Override
	protected void update(double value){
		target.setOpacity((int)value);
	}	

}
