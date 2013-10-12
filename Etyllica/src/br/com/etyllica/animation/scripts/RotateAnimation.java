package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class RotateAnimation extends AnimationScript{
		
	public RotateAnimation(long time){
		super(time);
	}
	
	public RotateAnimation(long delay, long time){
		super(delay, time);
	}
	
	public RotateAnimation(Layer target, long time){
		super(target, time);
	}
		
	@Override
	public void update(double value){
		target.setAngle(value);		
	}	

}
