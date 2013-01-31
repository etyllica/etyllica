package br.com.etyllica.layer;

import br.com.etyllica.core.event.DeviceType;
import br.com.etyllica.core.event.Event;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Layer {

	protected int x = 0;
	protected int y = 0;
	protected int w = 0;
	protected int h = 0;
	
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
	
	public void setVisible(boolean visible){
		this.visible = visible;	
	}
	
	public boolean isVisible(){
		return visible;
	}

	public void show(){
		visible = true;
	}
	
	public void hide(){
		visible = false;
	}
	
	public void swapVisible(){
		
		if(visible){
			visible = false;
		}
		else{
			visible = true;
		}
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
	
	public boolean onMouse(Event event) {
		
		if(event.getDevice()==DeviceType.MOUSE){
			return onMouse(event.getX(), event.getY());
		}
		
		return false;
		
	}
	
	public boolean onMouse(int mx, int my) {
		return colideRect(mx, my, 1, 1);
	}
	
}
