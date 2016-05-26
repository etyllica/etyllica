package br.com.etyllica.gui.selection;

import br.com.etyllica.core.event.MouseState;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.Layer;

public class ResizerPoint extends Layer {

	private MouseState state;
	
	public ResizerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	//Draw with half size
	public void draw(Graphics g) {
		int sw = (int)(utilWidth()*getScaleX());
		int sh = (int)(utilHeight()*getScaleY());
				
		int offsetX = (int)(utilWidth()*(1-getScaleX()))/2;
		int offsetY = (int)(utilHeight()*(1-getScaleY()))/2;
		
		g.fillRect(getX()+offsetX, getY()+offsetY, sw, sh);
	}

	public MouseState getState() {
		return state;
	}

	public void setState(MouseState state) {
		this.state = state;
	}
	
}
