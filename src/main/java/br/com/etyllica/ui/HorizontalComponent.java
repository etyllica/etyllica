package br.com.etyllica.ui;

import br.com.etyllica.layer.ImageLayer;

public abstract class HorizontalComponent extends View {

	protected ImageLayer layer;
	
	protected int startPart = 0;
	protected int endPart = 0;
	
	public HorizontalComponent(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
}
