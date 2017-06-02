package br.com.etyllica.ui.icon;

import java.awt.Color;
import java.awt.Polygon;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.label.Icon;

public abstract class PolygonalIcon extends Icon {

	protected float size;
	
	protected Polygon polygon = new Polygon();
	
	public PolygonalIcon(int x, int y) {
		super(x,y);
		this.size = 12;
		initPolygon(x, y);
	}

	public PolygonalIcon(int x, int y, float size){
		super(x,y);
		this.size = size;
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
	public void draw(Graphics g) {
		//TODO Change to Theme's Colors
		g.setColor(Color.WHITE);
		
		g.fillPolygon(polygon);
		
		g.setColor(Color.BLACK);
		
		g.drawPolygon(polygon);
	}	
	
}