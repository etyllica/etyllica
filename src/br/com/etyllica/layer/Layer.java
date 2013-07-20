package br.com.etyllica.layer;

import br.com.etyllica.core.event.PointerEvent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Layer {

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
	
	/**
	 * Angle in degrees
	 */
	protected double angle = 0;
	
	/**
	 * Scale factor
	 */
	protected double scale = 1;
	
	/**
     * if layer is visible
     */
	protected boolean visible = true;
		
	public Layer(){
		super();	
	}
	
	public Layer(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Layer(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	
	public void setBounds(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void setCoordinates(int x, int y){
		setX(x);
		setY(y);
	}
	
	public void setOffsetX(int offsetX){
		setX(x+offsetX);
	}
	public void setOffsetY(int offsetY){
		setY(y+offsetY);
	}

	public void setOffset(int offsetX, int offsetY){
		setOffsetX(offsetX);
		setOffsetY(offsetY);
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setOffsetAngle(double offset) {
		setAngle(angle+offset);
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;	
	}
	
	public boolean isVisible(){
		return visible;
	}

	/**
     * Method to turn a Layer visible
     */
	public void show(){
		visible = true;
	}
	
	/**
     * Method to turn a Layer invisible
     */
	public void hide(){
		visible = false;
	}
	
	public void swapVisible(){
		
		visible = !visible;
		
	}
	
	public boolean colideRect(int bx, int by, int bw, int bh){

		if(bx + bw < getX())	return false;
		if(bx > getX() + getW())		return false;

		if(by + bh < getY())	return false;
		if(by > getY() + getH())		return false;

		return true;

	}
	
	public boolean colideCircleCircle(int bx, int by, int bw, int bh)
	{
		int xdiff = bx - x;
		int ydiff = by - y;

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = bw/2 + w/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}
	
	public boolean colideCirclePoint(int px, int py){
		
		int cx = x+w/2;
		int cy = y+h/2;
		
		int dx = (px - cx) * (px - cx);
		int dy = (py - cy) * (py - cy);
		
		int radius = w/2;
		
		double distance = Math.sqrt((double)(dx + dy));

		return (distance <= radius);
	}
	
	public boolean onMouse(PointerEvent event) {
		
		return onMouse(event.getX(), event.getY());
				
	}
	
	public boolean onMouse(int mx, int my) {
		return colideRect(mx, my, 1, 1);
	}
	
}
