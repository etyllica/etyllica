package br.com.etyllica.layer;

import br.com.etyllica.commons.math.EtyllicaMath;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.linear.Rectangle;
import br.com.etyllica.loader.image.ImageLoader;

/**
 * 
 * @author yuripourre
 *
 */

public class ImageLayer extends StaticLayer {

	protected int srcX = 0;
	protected int srcY = 0;
	protected int srcW = 0;
	protected int srcH = 0;

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
		srcW = w;
		srcH = h;
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
		srcW = w;
		srcH = h;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param srcX
	 * @param srcY
	 * @param path
	 */
	public ImageLayer(int x, int y, int w, int h, int srcX, int srcY, String path) {
		super(x,y,w,h,path);
		this.srcX = srcX;
		this.srcY = srcY;
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
		this(0, 0, path);
	}
	
	public ImageLayer(String path, boolean absolute) {
		this(0, 0, path, absolute);
	}

	public ImageLayer(int x, int y, String path, boolean absolute) {
		super(path, absolute);
		setCoordinates(x, y);
	}

	public int getSrcX() {
		return srcX;
	}

	/**
	 * 
	 * @param srcX
	 */
	public void setSrcX(int srcX) {
		this.srcX = srcX;
	}

	public int getSrcY() {
		return srcY;
	}

	/**
	 * 
	 * @param srcX
	 * @param srcY
	 */
	public void setImageCoordinates(int srcX, int srcY) {
		this.srcX = srcX;
		this.srcY = srcY;
	}

	public boolean colideRetangular(int bx, int by, int bw, int bh) {
		return colideRectRect(bx, by, bw, bh);
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
		simpleDraw(g, x, y, utilWidth(), utilHeight());
	}

	public void simpleDraw(Graphics g, int x, int y, int w, int h) {
		if (path.isEmpty()) {
			return;
		}
		g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x + w, y + h,
				srcX, srcY, srcX + w, srcY + h, null );
	}
	
	public boolean onMouse(Mouse mouse) {
		return colideRectPoint(mouse.getX(), mouse.getY());
	}

	public void clone(ImageLayer b) {
		this.w = b.w;
		this.h = b.h;

		this.srcX = b.srcX;
		this.srcY = b.srcY;
		this.srcW = b.srcW;
		this.srcH = b.srcH;
		
		this.scaleX = b.scaleX;
		this.scaleY = b.scaleY;
		this.angle = b.angle;

		this.path = b.path;
	}
}
