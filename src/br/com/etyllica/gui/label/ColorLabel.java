package br.com.etyllica.gui.label;

import java.awt.Color;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Label;

public class ColorLabel extends Label{

	public ColorLabel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	private Color color;
	
	@Override
	public void draw(Grafico g) {
		
		g.setColor(color);
		g.fill3DRect(x, y, w, h,true);
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}