package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class HorizontalMovement extends AnimationScript{
		
	public HorizontalMovement(long time){
		super(time);
	}
	
	public HorizontalMovement(long delay, long time){
		super(delay, time);
	}
	
	public HorizontalMovement(Layer target, long time){
		super(target, time);
	}

	@Override
	protected void update(double value){
		target.setX((int)value);
	}

}
