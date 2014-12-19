package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.Layer;


public class FadeInAnimation extends OpacityAnimation{
	
	{
		startValue = 0;
		endValue = 255;
	}
	
	public FadeInAnimation(long time){
		super(time);
	}
	
	public FadeInAnimation(long delay, long time){
		super(delay, time);
	}
	
	public FadeInAnimation(Layer target, long time){
		super(target, time);
	}
	
	public FadeInAnimation(Layer target, long delay, long time){
		super(target, delay, time);
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
