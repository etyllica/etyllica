package br.com.etyllica.layer;

import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.collision.HitBox;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.linear.Rectangle;
import br.com.etyllica.loader.image.ImageLoader;
import br.com.etyllica.util.EtyllicaMath;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLayer extends StaticLayer {

	protected int xImage = 0;
	protected int yImage = 0;

	protected HitBox colisionArea = null;

	public ImageLayer() {
		super();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public ImageLayer(int x, int y) {
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
	public ImageLayer(int x, int y, int w, int h) {
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
	public ImageLayer(int x, int y, int w, int h, String path) {
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
	public ImageLayer(int x, int y, int w, int h, int xImagem, int yImagem, String path) {
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
	public ImageLayer(int x, int y, String path) {
		super(path);
		setCoordinates(x, y);
	}

	/**
	 * 
	 * @param path
	 */
	public ImageLayer(String path) {
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

	public HitBox getColisionArea() {
		return colisionArea;
	}

	/**
	 * 
	 * @param colisionArea
	 */
	public void setColisionArea(HitBox colisionArea) {
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

	public boolean colideRetangular(int bx, int by, int bw, int bh) {
		return colideRect(bx, by, bw, bh);
	}

	public boolean colideRetangular(Layer b) {
		return colideRetangular(b.getX(), b.getY(), b.getW(), b.getH());
	}

	private boolean colideRectangle(Rectangle rect, Rectangle rect2, int rect2x, int rect2y) {
		if(rect2x+rect2.getX() + rect2.getW() < x+rect.getX())	return false;
		if(rect2x+rect2.getX() > x+rect.getX() + rect.getW())		return false;

		if(rect2y+rect2.getY() + rect2.getH() < y+rect.getY())	return false;
		if(rect2y+rect2.getY() > y+rect.getY() + rect.getH())		return false;

		return true;	
	}

	private boolean colideHitBox(Rectangle hitBox, int rect2x, int rect2y) {

		for(Rectangle rect: colisionArea.getAreas()) {

			if(colideRectangle(rect, hitBox, rect2x, rect2y)) {
				return true;
			}

		}

		return false;
	}

	public boolean colideAreaHitBox(AnimatedLayer b) {
		Rectangle rect2 = new Rectangle(0, 0, b.getTileW(), b.getTileH());
		return colideHitBox(rect2,b.getX(), b.getY());
	}

	public boolean colideAreaHitBox(ImageLayer b) {
		Rectangle rect2 = new Rectangle(0, 0,b.getW(),b.getH());
		return colideHitBox(rect2,b.getX(),b.getY());
	}

	public boolean colideHitBoxes(ImageLayer b) {

		for(Rectangle rect: colisionArea.getAreas()) {
			for(Rectangle rect2: b.getColisionArea().getAreas()) {
				if(colideRectangle(rect, rect2, b.getX(), b.getY())) {
					return true;
				}
			}
		}

		return false;
	}

	//Based on code at: http://developer.coronalabs.com/code/checking-if-point-inside-rotated-rectangle
	public boolean colideRotated(int mx, int my) {
		
		double sx = EtyllicaMath.mod(scaleX);
		double sy = EtyllicaMath.mod(scaleY);
		
		int halfWidth = (int)((w*sx)/2);
		int halfHeight = (int)((h*sy)/2);
		
		//Pivot Point of rotation
		int px = x+halfWidth;
		int py = y+halfHeight;

		double c = Math.cos(angle);
		double s = Math.sin(angle);

		// UNrotate the point depending on the rotation of the rectangle
		double rotatedX = px + c * (mx - px) - s * (my - py);

		double rotatedY = py + s * (mx - px) + c * (my - py);

		// perform a normal check if the new point is inside the 
		// bounds of the UNrotated rectangle
		int leftX = px - halfWidth;
		int rightX = px + halfWidth;
		int topY = py - halfHeight;
		int bottomY = py + halfHeight;

		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	@Override
	public void draw(Graphics g) {
		draw(g, 0, 0);
	}
	
	public void draw(Graphics g, int offsetX, int offsetY) {
	
		if(!visible) {
			return;
		}

		if(opacity < 0xff) {
			g.setOpacity(opacity);
		}

		g.setTransform(getTransform(offsetX, offsetY));
		simpleDraw(g);
		g.resetTransform();
		
		if(opacity < 0xff) {
			g.resetOpacity();
		}
	}

	@Override
	public void simpleDraw(Graphics g) {
		simpleDraw(g, x, y);
	}

	public void simpleDraw(Graphics g, int x, int y) {
		g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+utilWidth(), y+utilHeight(),
				xImage,yImage,xImage+utilWidth(),yImage+utilHeight(), null );
	}
	
	public boolean onMouse(Mouse mouse) {
		return CollisionDetector.colideRectPoint(this, mouse.getX(), mouse.getY());
	}

	public void clone(ImageLayer b) {
		this.w = b.w;
		this.h = b.h;

		this.xImage = b.xImage;
		this.yImage = b.yImage;
		
		this.scaleX = b.scaleX;
		this.scaleY = b.scaleY;
		this.angle = b.angle;

		this.path = b.path;
	}

}
