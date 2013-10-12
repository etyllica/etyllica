package br.com.etyllica.gui.icon;

public class UpArrow extends PolygonalIcon{

	public UpArrow(int x, int y, int size){
		super(x, y, size);
				
	}	
	
	protected void initPolygon(int x, int y){
		polygon.reset();
		
		polygon.addPoint(x+size/2, y);
		polygon.addPoint(x, y+size);
		polygon.addPoint(x+size, y+size);
	}

}
