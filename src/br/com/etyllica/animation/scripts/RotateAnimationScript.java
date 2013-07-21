package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class RotateAnimationScript extends AnimationScript{
		
	public RotateAnimationScript(long time){
		super(time);
	}
	
	public RotateAnimationScript(long delay, long time){
		super(delay, time);
	}
	
	public RotateAnimationScript(Layer target, long time){
		super(target, time);
	}
		
	@Override
	public void update(double value){
		target.setAngle(value);		
	}	

}
