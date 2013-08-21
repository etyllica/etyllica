package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.colision.ColisionArea;
import br.com.etyllica.linear.Rectangle;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLayer extends StaticLayer{

	protected int xImage = 0;
	protected int yImage = 0;

	protected ColisionArea colisionArea = null;

	public ImageLayer(){
		super();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ImageLayer(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public ImageLayer(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param path
	 */
	public ImageLayer(int x, int y, int w, int h, String path){
		super(x,y,w,h,path);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param xImagem
	 * @param yImagem
	 * @param path
	 */
	public ImageLayer(int x, int y, int w, int h, int xImagem, int yImagem, String path){
		super(x,y,w,h,path);
		this.xImage = xImagem;
		this.yImage = yImagem;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param path
	 */
	public ImageLayer(int x, int y, String path){
		super(path);
		setCoordinates(x, y);
	}

	/**
	 * 
	 * @param path
	 */
	public ImageLayer(String path){
		this(0,0,path);
	}

	public int getXImage() {
		return xImage;
	}

	/**
	 * 
	 * @param xImage
	 */
	public void setXImage(int xImage) {
		this.xImage = xImage;
	}

	public int getYImage() {
		return yImage;
	}

	/**
	 * 
	 * @param yImage
	 */
	public void setYImage(int yImage) {
		this.yImage = yImage;
	}

	public ColisionArea getColisionArea() {
		return colisionArea;
	}

	/**
	 * 
	 * @param colisionArea
	 */
	public void setColisionArea(ColisionArea colisionArea) {
		this.colisionArea = colisionArea;
	}

	/**
	 * 
	 * @param xImage
	 * @param yImage
	 */
	public void setImageCoordinates(int xImage, int yImage) {
		this.xImage = xImage;
		this.yImage = yImage;
	}	

	public boolean colideRetangular(int bx, int by, int bw, int bh){

		return colideRect(bx, by, bw, bh);

	}

	public boolean colideRetangular(Layer b)
	{
		return colideRetangular(b.getX(), b.getY(), b.getW(), b.getH());
	}

	public boolean colideCircular(Layer b)
	{
		return colideCircular(b.getX(), b.getY(), b.getW(), b.getH());
	}

	public boolean colideCircularPonto(int px, int py){

		int centroX = x+w/2;
		int centroY = y+h/2;

		return (px-centroX)*(px-centroX) + (py - centroY)*(py - centroY) < (w/2)*(w/2);
	}

	public boolean colideCircular(int bx, int by, int bw, int bh)
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

	private boolean colideRetangulo(Rectangle rect, Rectangle rect2, int rect2x, int rect2y){

		if(rect2x+rect2.getX() + rect2.getW() < x+rect.getX())	return false;
		if(rect2x+rect2.getX() > x+rect.getX() + rect.getW())		return false;

		if(rect2y+rect2.getY() + rect2.getH() < y+rect.getY())	return false;
		if(rect2y+rect2.getY() > y+rect.getY() + rect.getH())		return false;

		return true;	
	}

	private boolean colideAreaRetangulo(Rectangle rect2, int rect2x, int rect2y){

		for(Rectangle rect: colisionArea.getArea()){

			if(colideRetangulo(rect, rect2, rect2x, rect2y)){
				return true;
			}

		}

		return false;
	}

	public boolean colideAreaCamada(AnimatedLayer b){
		Rectangle rect2 = new Rectangle(0, 0, b.getXTile(), b.getYTile());
		return colideAreaRetangulo(rect2,b.getX(), b.getY());
	}

	public boolean colideAreaCamada(ImageLayer b){
		Rectangle rect2 = new Rectangle(0, 0,b.getW(),b.getH());
		return colideAreaRetangulo(rect2,b.getX(),b.getY());
	}

	public boolean colideAreas(ImageLayer b){

		for(Rectangle rect: colisionArea.getArea()){

			for(Rectangle rect2: b.getColisionArea().getArea()){

				if(colideRetangulo(rect, rect2, b.getX(), b.getY())){
					return true;
				}

			}

		}

		return false;
	}

	//Based on code http://developer.coronalabs.com/code/checking-if-point-inside-rotated-rectangle
	public boolean colisionRotated(int mx, int my){

		//Pivot Point of rotation
		int px = x+w/2;
		int py = y+h/2;

		double c = Math.cos(angle);

		double s = Math.sin(angle);

		// UNrotate the point depending on the rotation of the rectangle
		double rotatedX = px + c * (mx - px) - s * (my - py);

		double rotatedY = py + s * (mx - px) + c * (my - py);

		// perform a normal check if the new point is inside the 
		// bounds of the UNrotated rectangle
		int leftX = px - w / 2;
		int rightX = px + w / 2;
		int topY = py - h / 2;
		int bottomY = py + h / 2;

		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	public void clone(ImageLayer b){
		this.w = b.w;
		this.h = b.h;

		this.xImage = b.xImage;
		this.yImage = b.yImage;

		this.path = b.path;
	}

	public void draw(Grafico g){
		
		if(visible){

			if(opacity<255){
				g.setOpacity(opacity);
			}
			
			AffineTransform transform = new AffineTransform();

			if(body==null){

				if(angle!=0){
					transform.concatenate(AffineTransform.getRotateInstance(Math.toRadians(angle),x+w/2, y+h/2));
				}
				
			}else{

				int initialX = x+w/2;
				int initialY = y+h/2;
								
				transform.translate(this.body.getTransform().getTranslationX()-initialX, this.body.getTransform().getTranslationY()-initialY);
				transform.concatenate(AffineTransform.getRotateInstance(this.body.getTransform().getRotation(),x+w/2, y+h/2));
				
			}			
						
			if(scale!=1){

				double sw = w*scale;
				double sh = h*scale;

				double dx = sw/2-w/2;
				double dy = sh/2-h/2;

				transform.translate(x-w/2-dx, y-h/2-dy);

				AffineTransform scaleTransform = new AffineTransform();

				scaleTransform.translate(w/2, h/2);
				scaleTransform.scale(scale,scale);
				scaleTransform.translate(-x, -y);

				transform.concatenate(scaleTransform);

			}
			
			g.transform(transform);

			g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w,y+h,
					xImage,yImage,xImage+w,yImage+h, null );
			
			g.resetTransform();

			if(opacity<255){
				g.resetOpacity();
			}		

		}

	}

	public boolean onMouse(Mouse mouse){

		boolean colision = false;

		if(angle==0){
			colision = colideRetangular(mouse.getX(), mouse.getY(), 1, 1);
		}else{
			colision = colisionRotated(mouse.getX(), mouse.getY());
		}

		return colision;
	}
	
}
