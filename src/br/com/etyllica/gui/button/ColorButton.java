package br.com.etyllica.gui.button;

import java.awt.Color;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ColorButton extends Button{

	private Color color = Color.BLACK;
	
	public ColorButton(int x, int y, int w, int h, Color color) {
		super(x, y, w, h);
		this.color = color;
	}
	
	public ColorButton(int x, int y, int w, int h) {
		this(x, y, w, h,Color.BLACK);
	}
	
	protected void drawLabel(Theme theme, Grafico g){

		g.setColor(color);
		
		int width = w/2;

		g.getGraphics().fill3DRect(x+w/2-width/2, y+h/2-width/2, width, width, true);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

}
