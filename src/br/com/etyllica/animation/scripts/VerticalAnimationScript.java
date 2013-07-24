package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class VerticalAnimationScript extends AnimationScript{
		
	public VerticalAnimationScript(long time){
		super(time);
	}
	
	public VerticalAnimationScript(Layer target, long time){
		super(target, time);
	}
		
	@Override
	protected void update(double value){
		target.setY((int)value);
	}

}
