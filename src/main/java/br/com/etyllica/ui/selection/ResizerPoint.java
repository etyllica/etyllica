package br.com.etyllica.ui.selection;

import br.com.etyllica.commons.event.MouseState;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.layer.Layer;

public class ResizerPoint extends Layer {

	private MouseState state;
	
	public ResizerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	//Draw with half size
	public void draw(Graphics g) {
		draw(g, 0, 0);
	}
	
	public void draw(Graphics g, int offsetX, int offsetY) {
		int sw = (int)(utilWidth()*getScaleX());
		int sh = (int)(utilHeight()*getScaleY());
				
		int oX = (int)(utilWidth()*(1-getScaleX()))/2;
		int oY = (int)(utilHeight()*(1-getScaleY()))/2;
		
		g.fillRect(getX()+oX+offsetX, getY()+oY+offsetY, sw, sh);
	}

	public MouseState getState() {
		return state;
	}

	public void setState(MouseState state) {
		this.state = state;
	}
	
}
