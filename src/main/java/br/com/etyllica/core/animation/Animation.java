package br.com.etyllica.core.animation;

import br.com.etyllica.layer.Layer;

public class Animation {

	public static LayerAnimation animate(Layer layer) {
		LayerAnimation script = new LayerAnimation(layer);
		return script;
	}
		
}
