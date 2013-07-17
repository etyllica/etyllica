package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;


public class VerticalAnimationScript extends AnimationScript{

	private int startValue = 0;
	
	private int endValue = 0;
		
	public VerticalAnimationScript(long time) {
		super(time);
	}
	
	public void setInterval(int startValue, int endValue){
		this.startValue = startValue;
		this.endValue = endValue;
	}
	
	@Override
	public void animate(long now) {
		
		long timeElapsed = now-startedAt;
		
		float factor = (float)timeElapsed/time;

		int value = startValue+(int)((endValue-startValue)*factor);
		
		target.setY(value);		
		
	}

}
