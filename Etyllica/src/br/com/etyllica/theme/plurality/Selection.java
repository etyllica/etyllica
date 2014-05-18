package br.com.etyllica.theme.plurality;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Panel;

public class Selection extends Panel {

	private Color baseColor = new Color(0xF8, 0x1A, 0x27);
	
	private final int size = 8;

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
		g.drawLine(x-negativeOffset, y-negativeOffset, x-negativeOffset+size, y-negativeOffset+size);

		//Diagonal
		g.drawLine(x+size+negativeOffset, y, x, y+size+negativeOffset);
		
		g.drawLine(x+size+negativeOffset+3, y, x, y+size+negativeOffset+3);

		//Horizontal
		g.drawLine(x+size+negativeOffset, y, x+size*3, y);

		//Vertical
		g.drawLine(x, y+size+negativeOffset, x, y+size*3);

	}
	
	private void drawUpperRightCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x+w+negativeOffset, y-negativeOffset, x+w+negativeOffset-size, y-negativeOffset+size);

		//Diagonal
		g.drawLine(x+w-size-negativeOffset, y, x+w, y+size+negativeOffset);
		
		g.drawLine(x+w-size-negativeOffset-3, y, x+w, y+size+negativeOffset+3);

		//Horizontal
		g.drawLine(x+w-size-negativeOffset, y, x+w-size*3, y);

		//Vertical
		g.drawLine(x+w, y+size+negativeOffset, x+w, y+size*3);

	}
	
	private void drawLowerLeftCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x-negativeOffset, y+h+negativeOffset, x-negativeOffset+size, y+h+negativeOffset-size);

		//Diagonal
		g.drawLine(x+size+negativeOffset, y+h, x, y+h-size-negativeOffset);
		
		g.drawLine(x+size+negativeOffset+3, y+h, x, y+h-size-negativeOffset-3);

		//Horizontal
		g.drawLine(x+size+negativeOffset, y+h, x+size*3, y+h);

		//Vertical
		g.drawLine(x, y+h-size-negativeOffset, x, y+h-size*3);

	}
	
	private void drawLowerRightCorner(int x, int y, int w, int h, Graphic g) {

		//Flipped Diagonal
		g.drawLine(x+w+negativeOffset, y+h+negativeOffset, x+w+negativeOffset-size, y+h+negativeOffset-size);

		//Diagonal
		g.drawLine(x+w-size-negativeOffset, y+h, x+w, y+h-size-negativeOffset);
		
		g.drawLine(x+w-size-negativeOffset-3, y+h, x+w, y+h-size-negativeOffset-3);

		//Horizontal
		g.drawLine(x+w-size-negativeOffset, y+h, x+w-size*3, y+h);

		//Vertical
		g.drawLine(x+w, y+h-size-negativeOffset, x+w, y+h-size*3);

	}
		
}
