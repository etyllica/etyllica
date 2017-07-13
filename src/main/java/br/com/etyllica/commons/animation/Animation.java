package br.com.etyllica.commons.animation;

import br.com.etyllica.commons.layer.Layer;

public class Animation {

	public static LayerAnimation animate(Layer layer) {
		LayerAnimation script = new LayerAnimation(layer);
		return script;
	}
		
}
