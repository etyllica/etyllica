package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class VerticalAnimationScript extends AnimationScript{

	private int startValue = 0;
	
	private int endValue = 0;
		
	public VerticalAnimationScript(long time) {
		super(time);
	}
	
	public VerticalAnimationScript(Layer target, long time) {
		super(target, time);
	}
	
	public void setInterval(int startValue, int endValue){
		this.startValue = startValue;
		this.endValue = endValue;
	}
	
	@Override
	public void animate(long now) {
		
		long timeElapsed = now-startedAt-delay;
		
		float factor = (float)timeElapsed/time;

		double value = startValue+(endValue-startValue)*factor;
		
		target.setY((int)value);
		
	}

}
