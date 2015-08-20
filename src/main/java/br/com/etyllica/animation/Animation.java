package br.com.etyllica.animation;

import br.com.etyllica.core.animation.LayerAnimation;
import br.com.etyllica.layer.Layer;

public class Animation {

	public static LayerAnimation animate(Layer layer) {
		LayerAnimation script = new LayerAnimation(layer);
		return script;
	}
		
}
