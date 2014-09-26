package br.com.etyllica.effects;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.animation.scripts.FrameAnimation;
import br.com.etyllica.layer.AnimatedLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Effect extends AnimatedLayer {

	private FrameAnimation script = new FrameAnimation(this);
	
	public Effect(int x, int y, int xTile, int yTile) {
		super(x,y,xTile,yTile);
		
		setVisible(false);
		script.setRepeat(AnimationScript.REPEAT_FOREVER);
	}
	
	public Effect(int x, int y, int xTile, int yTile, String path) {
		super(x, y, xTile, yTile, path);
		
		setVisible(false);
		script.setRepeat(AnimationScript.REPEAT_FOREVER);
	}
	
	@Override
	protected void notifyAnimationFinishListener(long now) {
		super.notifyAnimationFinishListener(now);
		
		setVisible(false);
	}
	
	public void startEffect() {

		script.setTarget(this);
				
		setVisible(true);
		
		restartAnimation();		
		
		AnimationHandler.getInstance().add(script);
		
	}
	
}