package br.com.etyllica.theme.plurality;

import java.awt.Color;
import java.awt.Polygon;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Panel;

public class TitleArrow extends Panel {

	private Color baseColor = new Color(0xF8, 0x1A, 0x27);
	
	private final int size = 28;
	
	private Polygon upperLeftArrow;
	
	private Polygon lowerLeftArrow;
	
	private Polygon upperRightArrow;
	
	private Polygon lowerRightArrow;
	
	public TitleArrow(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		upperLeftArrow = new Polygon();
		
		lowerLeftArrow = new Polygon();
		
		upperRightArrow = new Polygon();
		
		lowerRightArrow = new Polygon();
		
		createArrows();
		
	}
	
	private void createArrows() {
		
		createUpperLeftArrow();
		
		createLowerLeftArrow();
		
		createUpperRightArrow();
		
		createLowerRightArrow();
		
	}
		
	@Override
	public void draw(Graphic g) {
		
		g.setColor(baseColor);

		g.setBasicStroke(1);

		g.fillPolygon(upperLeftArrow);
		
		g.fillPolygon(lowerLeftArrow);
		
		g.fillPolygon(upperRightArrow);
		
		g.fillPolygon(lowerRightArrow);

	}
	
	private void createUpperLeftArrow() {

		upperLeftArrow.reset();
		
		upperLeftArrow.addPoint((int)(x+size*0.60), (int)(y+size*0.05));
		
		//Round Lower Left
		upperLeftArrow.addPoint(x, (int)(y+size*0.95));
		upperLeftArrow.addPoint((int)(x+size*0.05), y+size);
		
		upperLeftArrow.addPoint((int)(x+size*0.875), y+size);
		
		upperLeftArrow.addPoint((int)(x+size*0.875), (int)(y+size*0.5));
		
		upperLeftArrow.addPoint((int)(x+size*0.75), (int)(y+size*0.45));
		
		//Round Upper Right
		upperLeftArrow.addPoint(x+size, (int)(y-size*0.05));
		upperLeftArrow.addPoint((int)(x+size-size*0.05), y);
		
		//Close polygon
		upperLeftArrow.addPoint((int)(x+size*0.65), y);
	}
	
	private void createLowerLeftArrow() {
		
		lowerLeftArrow.reset();
		
		lowerLeftArrow.addPoint((int)(x+size*0.60), (int)(y+h-size*0.05));
		
		//Round Lower Left
		lowerLeftArrow.addPoint(x, (int)(y+h-size*0.95));
		lowerLeftArrow.addPoint((int)(x+size*0.05), y+h-size);
		
		lowerLeftArrow.addPoint((int)(x+size*0.875), y+h-size);
		
		lowerLeftArrow.addPoint((int)(x+size*0.875), (int)(y+h-size*0.5));
		
		lowerLeftArrow.addPoint((int)(x+size*0.75), (int)(y+h-size*0.45));
		
		//Round Upper Right
		lowerLeftArrow.addPoint(x+size, (int)(y+h-size*0.05));
		lowerLeftArrow.addPoint((int)(x+size-size*0.05), y+h);
		
		//Close polygon
		lowerLeftArrow.addPoint((int)(x+size*0.65), y+h);
	}
	
	private void createUpperRightArrow() {
		
		upperRightArrow.reset();
		
		upperRightArrow.addPoint((int)(x+w-size*0.60), (int)(y+size*0.05));
		
		//Round Lower Left
		upperRightArrow.addPoint(x+w, (int)(y+size*0.95));
		upperRightArrow.addPoint((int)(x+w-size*0.05), y+size);
		
		upperRightArrow.addPoint((int)(x+w-size*0.875), y+size);
		
		upperRightArrow.addPoint((int)(x+w-size*0.875), (int)(y+size*0.5));
		
		upperRightArrow.addPoint((int)(x+w-size*0.75), (int)(y+size*0.45));
		
		//Round Upper Right
		upperRightArrow.addPoint(x+w-size, (int)(y-size*0.05));
		upperRightArrow.addPoint((int)(x+w-size+size*0.05), y);
		
		//Close polygon
		upperRightArrow.addPoint((int)(x+w-size*0.65), y);
		
	}
	
	private void createLowerRightArrow() {
		
		lowerRightArrow.reset();
		
		lowerRightArrow.addPoint((int)(x+w-size*0.60), (int)(y+h-size*0.05));
		
		//Round Lower Left
		lowerRightArrow.addPoint(x+w, (int)(y+h-size*0.95));
		lowerRightArrow.addPoint((int)(x+w-size*0.05), y+h-size);
		
		lowerRightArrow.addPoint((int)(x+w-size*0.875), y+h-size);
		
		lowerRightArrow.addPoint((int)(x+w-size*0.875), (int)(y+h-size*0.5));
		
		lowerRightArrow.addPoint((int)(x+w-size*0.75), (int)(y+h-size*0.45));
		
		//Round Upper Right
		lowerRightArrow.addPoint(x+w-size, (int)(y+h-size*0.05));
		lowerRightArrow.addPoint((int)(x+w-size+size*0.05), y+h);
		
		//Close polygon
		lowerRightArrow.addPoint((int)(x+w-size*0.65), y+h);
		
	}
	
}
