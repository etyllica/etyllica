package br.com.etyllica.effects;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.core.DrawableComponent;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class GlobalEffect extends Layer implements DrawableComponent{

	protected AnimationScript script;
	
	public GlobalEffect(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public AnimationScript getScript() {
		return script;
	}

}