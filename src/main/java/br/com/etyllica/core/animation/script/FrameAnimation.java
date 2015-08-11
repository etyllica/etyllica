package br.com.etyllica.core.animation.script;

import br.com.etyllica.layer.AnimatedLayer;

public class FrameAnimation extends SingleIntervalAnimation {
		
	protected AnimatedLayer target;
	
	public FrameAnimation(long time) {
		super(time);
	}
	
	public FrameAnimation(AnimatedLayer target) {
		super(target.getSpeed()*target.getFrames());
		
		setTarget(target);
		this.repeat = REPEAT_FOREVER;
	}
	
	public void setTarget(AnimatedLayer target) {
		
		this.target = target;
		
		this.startValue = 0;
		this.endValue = target.getFrames();
		
		this.restart();
	}	
		
	@Override
	protected void update(double value) {
				
		target.animateWithFrame((int)(value%target.getFrames()));
	}

}
