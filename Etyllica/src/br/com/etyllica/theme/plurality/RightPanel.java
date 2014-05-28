package br.com.etyllica.theme.plurality;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.theme.ThemeManager;

public class RightPanel extends Panel {
	
	private int size = 8;

	private int negativeOffset = 3;
	
	private int greatOffset;
	
	private double scaleSize = 3.2;
	
	public RightPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		greatOffset = size*6;
		
	}
	
	@Override
	public void draw(Graphic g) {
		
		Color baseColor = ThemeManager.getInstance().getTheme().getBaseColor();		
		
		g.setColor(baseColor);

		g.setBasicStroke(1);

		drawThinCorners(g);

	}

	private void drawThinCorners(Graphic g) {

		drawUpperLeftCorner(x, y, w, h, g);
		
		drawUpperRightCorner(x, y, w, h, g);
		
		drawLowerLeftCorner(x, y, w, h, g);
		
		drawLowerRightCorner(x, y, w, h, g);

	}

	private void drawUpperLeftCorner(int x, int y, int w, int h, Graphic g) {
		
		int anchorX = (int)(x+size*scaleSize-negativeOffset);
		int anchorY = (int)(y+size*scaleSize+negativeOffset);
		
		//Diagonal
		g.drawLine(anchorX, y, x, anchorY);
		
		g.drawLine(anchorX+3, y, x, anchorY+3);

		//Horizontal
		g.drawLine(anchorX, y, anchorX+greatOffset, y);

		//Vertical
		g.drawLine(x, anchorY, x, anchorY+h/2-greatOffset+size);
				
	}
	
	private void drawUpperRightCorner(int x, int y, int w, int h, Graphic g) {
				
		int anchorX = (int)(x+w-size-negativeOffset);
		int anchorY = (int)(y+size+negativeOffset);
		
		//Diagonal
		g.drawLine(anchorX, y, x+w, anchorY);
		
		g.drawLine(anchorX-3, y, x+w, anchorY+3);

		//Horizontal
		g.drawLine(anchorX, y, x+w-size*3, y);

		//Vertical
		g.drawLine(x+w, anchorY, x+w, anchorY+h/2-greatOffset+size);

	}
	
	private void drawLowerLeftCorner(int x, int y, int w, int h, Graphic g) {

		//Diagonal
		g.drawLine(x+size+negativeOffset, y+h, x, y+h-size-negativeOffset);
		
		g.drawLine(x+size+negativeOffset+3, y+h, x, y+h-size-negativeOffset-3);

		//Horizontal
		g.drawLine(x+size+negativeOffset, y+h, x+size*3, y+h);

		//Vertical
		g.drawLine(x, y+h-size-negativeOffset, x, y+h-size*3);

	}
	
	private void drawLowerRightCorner(int x, int y, int w, int h, Graphic g) {
		
		int anchorX = (int)(x+w-size*scaleSize-negativeOffset);
		int anchorY = (int)(y+h-size*scaleSize-negativeOffset);
		
		//Diagonal
		g.drawLine(anchorX, y+h, x+w, anchorY);
		
		g.drawLine(anchorX-3, y+h, x+w, anchorY-3);

		//Horizontal
		g.drawLine(anchorX, y+h, x+greatOffset, y+h);

		//Vertical
		g.drawLine(x+w, anchorY, x+w, anchorY-h/2+greatOffset-size);		

	}
	
}
