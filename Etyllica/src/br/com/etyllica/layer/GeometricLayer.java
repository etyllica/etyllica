package br.com.etyllica.layer;

import br.com.etyllica.core.Movable;

public class GeometricLayer implements Movable {

	/**
     * x position of a Layer
     */
	protected int x = 0;
	
	/**
     * y position of a Layer
     */
	protected int y = 0;
	
	/**
     * Layer's width
     */
	protected int w = 0;
	
	/**
     * Layer's height
     */
	protected int h = 0;
	
	public GeometricLayer() {
		super();
	}
	
	public GeometricLayer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public GeometricLayer(int x, int y, int w, int h) {
		super();
		
		setBounds(x, y, w, h);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	public void setBounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setCoordinates(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * 
	 * @param offsetX
	 */
	public void setOffsetX(int offsetX) {
		setX(this.x+offsetX);
	}
	
	/**
	 * 
	 * @param offsetY
	 */
	public void setOffsetY(int offsetY) {
		setY(this.y+offsetY);
	}

	/**
	 * 
	 * @param offsetX
	 * @param offsetY
	 */
	public void setOffset(int offsetX, int offsetY) {
		setOffsetX(offsetX);
		setOffsetY(offsetY);
	}
	
	/*
	 * Centralization Methods
	 */
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void centralize(int x, int y, int w, int h) {
		centralizeX(x,x+w);
		centralizeY(y,y+h);
	}

	/**
	 * 
	 * @param layer
	 */
	public void centralize(Layer layer) {
		centralizeX(layer);
		centralizeY(layer);
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void centralizeX(Layer layer) {
		centralizeX(layer.getX(),layer.getX()+layer.getW());
	}
	
	/**
	 * 
	 * @param startX
	 * @param endX
	 * @return
	 */
	public int centralizeX(int startX, int endX)
	{
		int x = (((startX+endX)/2)-(getW()/2));
		setX(x);
		
		return x;
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void centralizeY(Layer layer) {
		centralizeY(layer.getY(),layer.getY()+layer.getH());
	}
	
	/**
	 * 
	 * @param startY
	 * @param endY
	 * @return
	 */
	public int centralizeY(int startY, int endY)
	{
		int y = (((startY+endY)/2)-(getH()/2));
		setY(y);
		
		return y;
	}
	
	/*
	 * Colision Methods
	 */
	
	/**
	 * 
	 * @param bx
	 * @param by
	 * @param bw
	 * @param bh
	 * @return
	 */
	public boolean colideRect(int bx, int by, int bw, int bh) {

		if(bx + bw < getX())	return false;
		if(bx > getX() + getW())		return false;

		if(by + bh < getY())	return false;
		if(by > getY() + getH())		return false;

		return true;

	}
	
	/**
	 * 
	 * @param px
	 * @param py
	 * @return
	 */
	public boolean colideRectPoint(int px, int py) {
		
		if((px<getX())||(px>getX() + getW())) {
			return false;
		}
		
		if((py<getY())||(py>getY() + getH())) {
			return false;
		}

		return true;
	}
	
	/**
	 * 
	 * @param bx
	 * @param by
	 * @param bw
	 * @param bh
	 * @return
	 */
	public boolean colideCircleCircle(int bx, int by, int bw, int bh)
	{
		int xdiff = bx - x;
		int ydiff = by - y;

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = bw/2 + w/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0) {
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @param px
	 * @param py
	 * @return
	 */
	public boolean colideCirclePoint(int px, int py) {
		
		int cx = x+w/2;
		int cy = y+h/2;
		
		int dx = (px - cx) * (px - cx);
		int dy = (py - cy) * (py - cy);
		
		int radius = w/2;
		
		double distance = Math.sqrt((double)(dx + dy));

		return (distance <= radius);
	}
	
	public boolean colideIsometric(int px, int py) {

		int my = this.getH()/2;
		int mx = this.getW()/2;

		int x = px-this.getX();
		int y = py-this.getY();

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;
		else
			return true;

	}
	
}
