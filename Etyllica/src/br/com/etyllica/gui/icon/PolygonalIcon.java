package br.com.etyllica.gui.icon;

import java.awt.Color;
import java.awt.Polygon;

import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.label.Icon;

public abstract class PolygonalIcon extends Icon{

	protected int size;
	
	protected Polygon polygon;
	
	public PolygonalIcon(int x, int y){
		super(x,y);
		this.size = 12;
		polygon = new Polygon();
		initPolygon(x, y);	
	}
	
	public PolygonalIcon(int x, int y, int size){
		super(x,y);
		this.size = size;
		polygon = new Polygon();
		initPolygon(x, y);
	}
	
	@Override
	public void setX(int x){
		this.x = x;
		initPolygon(this.x, y);
	}
	
	@Override
	public void setY(int y){
		this.y = y;
		initPolygon(x, this.y);
	}
	
	protected abstract void initPolygon(int x, int y);
	
	@Override
	public void draw(Graphic g) {
		g.setColor(Color.WHITE);
		//g.setColor(Color.BLUE);
		
		g.fillPolygon(polygon);
		
		g.setColor(Color.BLACK);
		
		g.drawPolygon(polygon);
	}	
	
}