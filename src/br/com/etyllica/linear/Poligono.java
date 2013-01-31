package br.com.etyllica.linear;

import java.awt.Polygon;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Poligono extends Polygon{

	private static final long serialVersionUID = -2340920005262100745L;
	
	private int x;
	private int y;
	
	public Poligono(){
		super();
		x = 0;
		y = 0;
	}
	public Poligono(int x, int y){
		super();
		this.x = x;
		this.y = y;
		
		this.addPoint(x, y);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		translate(x-this.x, 0);
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		translate(0,y-this.y);
		this.y = y;
	}
	
	public void setOffsetX(int x){
		translate(x, 0);
		this.x+=x;
	}
	
	public void setOffsetY(int y){
		translate(0, y);
		this.y+=y;
	}
	
	public void setOffset(int x, int y){
		translate(x, y);
		this.x+=x;
		this.y+=y;
	}
	

}
