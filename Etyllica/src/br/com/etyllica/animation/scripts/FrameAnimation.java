package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.AnimatedLayer;

public class FrameAnimation extends AnimationScript{
		
	protected AnimatedLayer target;
	
	public FrameAnimation(long time){
		super(time);
	}
	
	public FrameAnimation(AnimatedLayer target){
		super(target.getSpeed()*target.getFrames());
		
		this.startValue = 0;
		this.endValue = target.getFrames();
		
		this.target = target;
		this.endless = true;
	}
		
	@Override
	protected void update(double value){
				
		target.animate((int)(value%target.getFrames()));
	}

}
