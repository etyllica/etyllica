package br.com.etyllica.effects;

import br.com.etyllica.core.animation.script.SingleIntervalAnimation;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class GlobalEffect extends Layer {

	protected SingleIntervalAnimation script;
	
	public GlobalEffect(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public SingleIntervalAnimation getScript() {
		return script;
	}

}