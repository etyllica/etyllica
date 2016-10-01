package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Layer extends GeometricLayer implements Drawable {

	/**
	 * Opacity
	 */
	protected int opacity = 255;

	/**
	 * Angle in degrees
	 */
	protected double angle = 0;
	
	/**
	 * Reference point to scale and rotation
	 */
	protected float originX, originY;
	
	/**
	 * Scale factors
	 */
	protected double scaleX = 1, scaleY = 1;

	/**
	 * if layer is visible
	 */
	protected boolean visible = true;

	public Layer() {
		super();
	}

	public Layer(int x, int y) {
		super();

		this.x = x;
		this.y = y;
	}

	public Layer(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	public int getOpacity() {
		return opacity;
	}

	/**
	 * 
	 * @param opacity
	 */
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	public double getAngle() {
		return angle;
	}

	/**
	 * 
	 * @param angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public float getOriginX() {
		return originX;
	}
	
	public float getOriginY() {
		return originY;
	}

	/**
	 * 
	 * @param origin
	 */
	public void setOrigin(float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
	}
	
	public void setOriginCenter() {
		this.originX = utilWidth() / 2;
		this.originY = utilHeight() / 2;
	}

	/**
	 * 
	 * @param offset
	 */
	public void setOffsetAngle(double offset) {
		setAngle(angle+offset);
	}

	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	/**
	 * 
	 * @param scaleX
	 */
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * 
	 * @param scaleY
	 */
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public void setScale(double scale) {
		this.scaleX = scale;
		this.scaleY = scale;
	}

	/**
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;	
	}

	public boolean isVisible() {
		return visible;
	}

	/**
	 * Method to turn a Layer visible
	 */
	public void show() {
		visible = true;
	}

	/**
	 * Method to turn a Layer invisible
	 */
	public void hide() {
		visible = false;
	}

	public void swapVisible() {
		visible = !visible;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean onMouse(PointerEvent event) {
		return onMouse(event.getX(), event.getY());
	}

	/**
	 * 
	 * @param mx
	 * @param my
	 * @return
	 */
	public boolean onMouse(int px, int py) {
		return CollisionDetector.colideRectPoint(this, px, py);
	}

	public AffineTransform getTransform() {
		return getTransform(0, 0);
	}	
	
	public AffineTransform getTransform(float offsetX, float offsetY) {
		
		final float px = getX();
		final float py = getY();
		
		AffineTransform transform = new AffineTransform();
			
		transform.translate(px + originX, py + originY);
				
		// Scale
		if(scaleX != 1 || scaleY != 1) {
			transform.scale(scaleX, scaleY);
		}
		
		// Rotate
		if(angle != 0) {
			transform.rotate(Math.toRadians(angle));
		}

		// Move to origin (centered)
		transform.translate(-px - originX, -py - originY);
		
		return transform;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	public void simpleDraw(Graphics g) {
		// TODO Auto-generated method stub
	}
	
	public void copy(Layer layer) {
		super.copy(layer);
		setOrigin(layer.getOriginX(), layer.getOriginY());
		setScaleX(layer.getScaleX());
		setScaleY(layer.getScaleY());
		setAngle(layer.getAngle());
		setOpacity(layer.getOpacity());
	}
}
