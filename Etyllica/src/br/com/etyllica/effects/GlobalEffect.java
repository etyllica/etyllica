package br.com.etyllica.effects;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class GlobalEffect extends Layer {

	protected AnimationScript script;
	
	public GlobalEffect(float x, float y, float w, float h){
		super(x,y,w,h);
	}
	
	public AnimationScript getScript() {
		return script;
	}

}