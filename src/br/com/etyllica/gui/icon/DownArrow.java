package br.com.etyllica.gui.icon;

public class DownArrow extends PolygonalIcon{

	public DownArrow(int x, int y, int size){
		super(x, y, size);
				
	}	
	
	protected void initPolygon(int x, int y){
		polygon.reset();
		
		polygon.addPoint(x, y);
		polygon.addPoint(x+size, y);
		polygon.addPoint(x+size/2, y+size);
	}

}
