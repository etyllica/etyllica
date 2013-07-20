package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class HorizontalAnimationScript extends AnimationScript{
		
	public HorizontalAnimationScript(long time) {
		super(time);
	}
	
	public HorizontalAnimationScript(Layer target, long time) {
		super(target, time);
	}
		
	@Override
	public void animate(long now) {
		
		long timeElapsed = now-startedAt-delay;
		
		float factor = (float)timeElapsed/time;

		int value = startValue+(int)((endValue-startValue)*factor);
		
		target.setX(value);		
		
	}	

}
