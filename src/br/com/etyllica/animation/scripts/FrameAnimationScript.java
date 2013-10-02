package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.AnimatedLayer;

public class FrameAnimationScript extends AnimationScript{
		
	protected AnimatedLayer target;
	
	public FrameAnimationScript(long time){
		super(time);
	}
	
	public FrameAnimationScript(AnimatedLayer target){
		super(target.getSpeed());
		this.target = target;		
		this.endless = true;
	}
		
	@Override
	protected void update(double value){
		target.animate();
	}

}
