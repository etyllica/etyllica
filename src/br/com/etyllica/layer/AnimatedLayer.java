package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AnimatedLayer extends ImageLayer{

	protected int xTile = 0;
	protected int yTile = 0;

	protected boolean once = false;
	protected boolean stopped = true;
	protected boolean oscilate = false;
	protected boolean animaEmX = true;

	protected boolean lockOnce = false;

	private int inc = 1;

	private int loop = 0;

	protected int frames = 1;
	protected int currentFrame = 0;

	protected int speed;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public AnimatedLayer(int x, int y){
		this(x, y, 0, 0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xTile
	 * @param yTile
	 * @param path
	 */
	public AnimatedLayer(int x, int y, int xTile, int yTile, String path){
		super(x,y,path);
		this.xTile = xTile;
		this.yTile = yTile;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xTile
	 * @param yTile
	 */
	public AnimatedLayer(int x, int y, int xTile, int yTile){
		super(x,y);
		this.xTile = xTile;
		this.yTile = yTile;
	}

	protected void resetAnimation(){
		xImage = 0;
		yImage = 0;
		currentFrame = 0;
	}

	public void setAnimaEmX(boolean animaX){
		animaEmX = animaX;
	}

	public int getXTile(){
		return xTile;
	}

	public int getYTile(){
		return yTile;
	}

	/**
	 * 
	 * @param xTile
	 */
	public void setXTile(int xTile){
		this.xTile = xTile;
	}

	/**
	 * 
	 * @param yTile
	 */
	public void setYTile(int yTile){
		this.yTile = yTile;
	}

	/**
	 * 
	 * @param xTile
	 * @param yTile
	 */
	public void setTileCoordinates(int xTile, int yTile){
		setXTile(xTile);
		setYTile(yTile);
	}

	public void animate(){

		preAnima();
		
		/*if(!lockOnce){

			timer.setParado(false);

			if(timer.passou()){
				
			}
		}*/

		stopped = false;
	}

	public void desAnima(){
		//Stop
	}	

	public void animaOnce(){

		visible = true;
		lockOnce = false;
		once = true;
		stopped = false;

		currentFrame = 0;

		if(animaEmX){
			xImage = 0;
		}else{
			yImage = 0;
		}

	}

	public void preAnima(){

		if((currentFrame < frames-1)&&(currentFrame >= 0)){

			if(oscilate){
				inc = -inc;
			}

			currentFrame+=inc;
		}
		else{

			if(once){
				visible = false;
				lockOnce = true;
				//stopped = true;
				setXImage(xTile*currentFrame);
				return;
			}

			if(!oscilate){
				currentFrame = 0;
			}else{
				currentFrame+=inc;
			}

			loop++;

		}

		if(!stopped){

			if(animaEmX){
				setXImage(xTile*currentFrame);
			}
			else{
				setYImage(yTile*currentFrame);
			}

		}
	}
	
	@Override
	public int centralizeX(int xInicial, int xFinal)
	{
		int x;
		x = (((xInicial+xFinal)/2)-(getXTile()/2));
		setX(x);
		return x;
	}

	@Override
	public int centralizeY(int yInicial, int yFinal)
	{
		int y;
		y = (((yInicial+yFinal)/2)-(getYTile()/2));
		setY(y);
		return y;
	}

	@Override
	public boolean colideRetangular(Layer b)
	{
		if(b.getX() + b.getW() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getH() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}

	public boolean colideRetangular(AnimatedLayer b)
	{
		if(b.getX() + b.getXTile() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getYTile() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}

	public boolean colideCircular(AnimatedLayer b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getXTile()/2 + xTile/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean colideCircular(Layer b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getW()/2 + xTile/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	@Override
	public void draw(Graphic g){

		if(visible){

			if(opacity<255){
				g.setOpacity(opacity);
			}

			AffineTransform transform = new AffineTransform();

			if(angle!=0){
				transform.concatenate(AffineTransform.getRotateInstance(Math.toRadians(angle),x+xTile/2, y+yTile/2));
			}

			if(scale!=1){

				double sw = xTile*scale;
				double sh = yTile*scale;

				double dx = sw/2-xTile/2;
				double dy = sh/2-yTile/2;

				transform.translate(x-xTile/2-dx, y-yTile/2-dy);

				AffineTransform scaleTransform = new AffineTransform();

				scaleTransform.translate(xTile/2, yTile/2);
				scaleTransform.scale(scale,scale);
				scaleTransform.translate(-x, -y);

				transform.concatenate(scaleTransform);

			}

			g.setTransform(transform);
			
			g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+xTile,y+yTile,
					xImage,yImage,xImage+xTile,yImage+yTile, null );
			g.resetTransform();

			if(opacity<255){
				g.resetOpacity();
			}

		}


	}

	/**
	 * 
	 * @param stopped
	 */
	public void setStopped(boolean stopped){
		this.stopped = stopped;
	}

	public boolean isStopped(){
		return stopped;
	}
	

	/**
	 * Set Number of Frames
	 * 
	 * @param frames
	 */
	public void setFrames(int frames){
		this.frames = frames;
	}

	public int getFrames(){
		return frames;
	}

	public boolean getAnimaEmX(){
		return animaEmX;
	}

	public int getLoop(){
		return loop;
	}

	public void setLockOnce(boolean lockOnce) {
		this.lockOnce = lockOnce;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}