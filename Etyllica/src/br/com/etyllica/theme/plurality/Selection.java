package br.com.etyllica.theme.plurality;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Panel;

public class Selection extends Panel {

	private Color baseColor = new Color(0xF8, 0x1A, 0x27);
	
	private final int arrowSize = 8;

	private final int negativeOffset = 3;
	
	public Selection(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphic g) {
		
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

		//Flipped Diagonal
		g.drawLine(x-negativeOffset, y-negativeOffset, x-negativeOffset+arrowSize, y-negativeOffset+arrowSize);

		//Diagonal
		g.drawLine(x+arrowSize+negativeOffset, y, x, y+arrowSize+negativeOffset);

		//Horizontal
		g.drawLine(x+arrowSize+negativeOffset, y, x+arrowSize*3, y);

		//Vertical
		g.drawLine(x, y+arrowSize+negativeOffset, x, y+arrowSize*3);

	}
	
	private void drawUpperRightCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x+w+negativeOffset, y-negativeOffset, x+w+negativeOffset-arrowSize, y-negativeOffset+arrowSize);

		//Diagonal
		g.drawLine(x+w-arrowSize-negativeOffset, y, x+w, y+arrowSize+negativeOffset);

		//Horizontal
		g.drawLine(x+w-arrowSize-negativeOffset, y, x+w-arrowSize*3, y);

		//Vertical
		g.drawLine(x+w, y+arrowSize+negativeOffset, x+w, y+arrowSize*3);

	}
	
	private void drawLowerLeftCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x-negativeOffset, y+h+negativeOffset, x-negativeOffset+arrowSize, y+h+negativeOffset-arrowSize);

		//Diagonal
		g.drawLine(x+arrowSize+negativeOffset, y+h, x, y+h-arrowSize-negativeOffset);

		//Horizontal
		g.drawLine(x+arrowSize+negativeOffset, y+h, x+arrowSize*3, y+h);

		//Vertical
		g.drawLine(x, y+h-arrowSize-negativeOffset, x, y+h-arrowSize*3);

	}
	
	private void drawLowerRightCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x+w+negativeOffset, y+h+negativeOffset, x+w+negativeOffset-arrowSize, y+h+negativeOffset-arrowSize);

		//Diagonal
		g.drawLine(x+w-arrowSize-negativeOffset, y+h, x+w, y+h-arrowSize-negativeOffset);

		//Horizontal
		g.drawLine(x+w-arrowSize-negativeOffset, y+h, x+w-arrowSize*3, y+h);

		//Vertical
		g.drawLine(x+w, y+h-arrowSize-negativeOffset, x+w, y+h-arrowSize*3);

	}
		
}
