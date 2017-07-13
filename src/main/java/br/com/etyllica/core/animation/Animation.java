package br.com.etyllica.core.animation;

import br.com.etyllica.commons.layer.Layer;
import br.com.etyllica.core.animation.script.LayerAnimation;

public class Animation {

	public static LayerAnimation animate(Layer layer) {
		LayerAnimation script = new LayerAnimation(layer);
		return script;
	}
		
}
