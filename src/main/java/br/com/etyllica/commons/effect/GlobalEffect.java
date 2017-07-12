package br.com.etyllica.commons.effect;

import br.com.etyllica.commons.animation.script.SingleIntervalAnimation;
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