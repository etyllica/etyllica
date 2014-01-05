package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class OpacityAnimation extends AnimationScript{
		
	public OpacityAnimation(long time){
		super(time);
	}
	
	public OpacityAnimation(long delay, long time){
		super(delay, time);
	}
	
	public OpacityAnimation(Layer target, long time){
		super(target, time);
	}
	
	public OpacityAnimation(Layer target, long delay, long time){
		super(target, delay, time);
	}
	
	@Override
	protected void update(double value){
		target.setOpacity((int)value);
	}	

}
