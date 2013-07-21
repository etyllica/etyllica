package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class HorizontalAnimationScript extends AnimationScript{
		
	public HorizontalAnimationScript(long time){
		super(time);
	}
	
	public HorizontalAnimationScript(long delay, long time){
		super(delay, time);
	}
	
	public HorizontalAnimationScript(Layer target, long time){
		super(target, time);
	}

	@Override
	protected void update(double value){
		target.setX((int)value);
	}

}
