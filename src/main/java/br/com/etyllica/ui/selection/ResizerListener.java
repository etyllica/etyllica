package br.com.etyllica.ui.selection;

import br.com.etyllica.layer.Layer;

public interface ResizerListener {
	void onResize(ResizerEvent event, int index, Layer layer, Layer old);
}
