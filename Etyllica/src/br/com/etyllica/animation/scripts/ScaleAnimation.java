package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class ScaleAnimation extends AnimationScript{
		
	public ScaleAnimation(long time){
		super(time);
	}
	
	public ScaleAnimation(long delay, long time){
		super(delay, time);
	}
	
	public ScaleAnimation(Layer target, long time){
		super(target, time);
	}
		
	@Override
	public void update(double value){
		target.setScale(value);		
	}	

}
