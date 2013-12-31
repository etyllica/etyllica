package br.com.etyllica.layer;

public class GeometricLayer {

	/**
     * x position of a Layer
     */
	protected float x = 0;
	
	/**
     * y position of a Layer
     */
	protected float y = 0;
	
	/**
     * Layer's width
     */
	protected float w = 0;
	
	/**
     * Layer's height
     */
	protected float h = 0;
	
	public GeometricLayer(){
		super();
	}
	
	public GeometricLayer(float x, float y){
		super();
		this.x = x;
		this.y = y;
	}
	
	public GeometricLayer(float x, float y, float w, float h){
		super();
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
	
	public void setBounds(int x, int y, int w, int h){
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
	public void setCoordinates(float x, float y){
		setX(x);
		setY(y);
	}
	
	/**
	 * 
	 * @param offsetX
	 */
	public void setOffsetX(float offsetX){
		setX(this.x+offsetX);
	}
	
	/**
	 * 
	 * @param offsetY
	 */
	public void setOffsetY(float offsetY){
		setY(this.y+offsetY);
	}

	/**
	 * 
	 * @param offsetX
	 * @param offsetY
	 */
	public void setOffset(float offsetX, float offsetY){
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
	public void centralize(float x, float y, float w, float h){
		centralizeX(x,x+w);
		centralizeY(y,y+h);
	}

	/**
	 * 
	 * @param layer
	 */
	public void centralize(Layer layer){
		centralizeX(layer);
		centralizeY(layer);
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void centralizeX(Layer layer){
		centralizeX(layer.getX(),layer.getX()+layer.getW());
	}
	
	/**
	 * 
	 * @param startX
	 * @param endX
	 * @return
	 */
	public float centralizeX(float startX, float endX)
	{
		float x = (((startX+endX)/2)-(getW()/2));
		setX(x);
		
		return x;
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void centralizeY(Layer layer){
		centralizeY(layer.getY(),layer.getY()+layer.getH());
	}
	
	/**
	 * 
	 * @param startY
	 * @param endY
	 * @return
	 */
	public float centralizeY(float startY, float endY)
	{
		float y = (((startY+endY)/2)-(getH()/2));
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
	public boolean colideRect(float bx, float by, float bw, float bh){

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
	public boolean colideRectPoint(float px, float py){
		
		if((px<x)||(px>x + w)){
			return false;
		}
		
		if((py<y)||(py>y + h)){
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
	public boolean colideCircleCircle(float bx, float by, float bw, float bh)
	{
		float xdiff = bx - x;
		float ydiff = by - y;

		float dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		float r_sum_sq = bw/2 + w/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0){
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
	public boolean colideCirclePoint(float px, float py){
		
		float cx = x+w/2;
		float cy = y+h/2;
		
		float dx = (px - cx) * (px - cx);
		float dy = (py - cy) * (py - cy);
		
		float radius = w/2;
		
		double distance = Math.sqrt((double)(dx + dy));

		return (distance <= radius);
	}
	
	public boolean colideIsometric(float px, float py){

		float my = this.getH()/2;
		float mx = this.getW()/2;

		float x = px-this.getX();
		float y = py-this.getY();

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;
		else
			return true;

	}
	
}
