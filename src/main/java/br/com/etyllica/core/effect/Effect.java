package br.com.etyllica.core.effect;

import br.com.etyllica.core.animation.AnimationModule;
import br.com.etyllica.core.animation.script.FrameAnimation;
import br.com.etyllica.layer.AnimatedLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class Effect extends AnimatedLayer {

	private boolean active = false;
	
	private FrameAnimation script = new FrameAnimation(this);
	
	public Effect(int x, int y, int xTile, int yTile) {
		super(x,y,xTile,yTile);
		
		setVisible(false);
	}
	
	public Effect(int x, int y, int xTile, int yTile, String path) {
		super(x, y, xTile, yTile, path);
		
		setVisible(false);
	}
	
	@Override
	protected void notifyAnimationFinishListener(long now) {
		super.notifyAnimationFinishListener(now);
		
		setVisible(false);
		active = false;
	}
	
	@Override
	public void animate(long now) {
		if(!active)
			return;
		
		super.animate(now);
	}
	
	public void startEffect() {
		script.setTarget(this);
		active = true;
		
		setVisible(true);
		restartAnimation();

		AnimationModule.getInstance().add(script);
	}
	
}