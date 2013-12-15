package br.com.etyllica.gui.icon;

public class DownArrow extends PolygonalIcon{

	public DownArrow(float x, float y, float size){
		super(x, y, size);
				
	}	
	
	protected void initPolygon(float x, float y){
		polygon.reset();
		
		polygon.addPoint((int)x, (int)y);
		polygon.addPoint((int)(x+size), (int)y);
		polygon.addPoint((int)(x+size/2), (int)(y+size));
	}

}
