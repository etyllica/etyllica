package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class ScaleAnimationScript extends AnimationScript{
		
	public ScaleAnimationScript(long time) {
		super(time);
	}
	
	public ScaleAnimationScript(long delay, long time) {
		super(delay, time);
	}
	
	public ScaleAnimationScript(Layer target, long time) {
		super(target, time);
	}
		
	@Override
	public void animate(long now) {
		
		long timeElapsed = now-startedAt-delay;
		
		float factor = (float)timeElapsed/time;

		double value = startValue+(endValue-startValue)*factor;
		
		target.setScale(value);
		
	}	

}
