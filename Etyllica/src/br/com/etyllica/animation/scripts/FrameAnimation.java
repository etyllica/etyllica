package br.com.etyllica.animation.scripts;

import br.com.etyllica.layer.AnimatedLayer;

public class FrameAnimation extends SingleIntervalAnimation {
		
	protected AnimatedLayer target;
	
	public FrameAnimation(long time) {
		super(time);
	}
	
	public FrameAnimation(AnimatedLayer target) {
		super(target.getSpeed()*target.getFrames());
		
		setTarget(target);
		this.endless = true;
		
	}
	
	public void setTarget(AnimatedLayer target) {
		
		this.target = target;
		
		this.startValue = 0;
		this.endValue = target.getFrames();
		
		this.restart();
	}	
		
	@Override
	protected void update(double value) {
				
		target.animate((int)(value%target.getFrames()));
	}

}
