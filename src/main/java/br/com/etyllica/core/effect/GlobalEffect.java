package br.com.etyllica.core.effect;

import br.com.etyllica.core.animation.script.SingleIntervalAnimation;
import br.com.etyllica.commons.layer.Layer;

/**
 * 
 * @author yuripourre
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