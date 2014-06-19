package br.com.etyllica.layer;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

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
	 * Scale factor
	 */
	protected double scale = 1;
		
	/**
     * if layer is visible
     */
	protected boolean visible = true;
	
	protected List<Layer> children;

	public Layer() {
		super();
	}
	
	public Layer(int x, int y){
		super();
		
		this.x = x;
		this.y = y;
	}
	
	public Layer(int x, int y, int w, int h){
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
	
	/**
	 * 
	 * @param offset
	 */
	public void setOffsetAngle(double offset) {
		setAngle(angle+offset);
	}
	
	public double getScale() {
		return scale;
	}

	/**
	 * 
	 * @param scale
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * 
	 * @param visible
	 */
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
	public boolean onMouse(int mx, int my) {
		
		if(angle == 0) {

			return colideRect(x, y, w, h);

		} else {

			AffineTransform transformer = AffineTransform.getRotateInstance(angle, x, y);

			Point2D a = new Point2D.Double(x, y);
			Point2D b = new Point2D.Double(x+w,y);
			Point2D c = new Point2D.Double(x+w,y+h);
			Point2D d = new Point2D.Double(x,y+h);

			transformer.transform(a,a);
			transformer.transform(b,b);
			transformer.transform(c,c);
			transformer.transform(d,d);

			Polygon p = new Polygon();
			p.addPoint((int)a.getX(),(int)a.getY());
			p.addPoint((int)b.getX(),(int)b.getY());
			p.addPoint((int)c.getX(),(int)c.getY());
			p.addPoint((int)d.getX(),(int)d.getY());

			return p.contains(x, y);

		}
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphic g, AffineTransform transform) {
		// TODO Auto-generated method stub
	}
		
	public List<Layer> getChildren() {
		return children;
	}

	public void setChildren(List<Layer> children) {
		this.children = children;
	}

	public void addChild(Layer layer){
		
		if(children==null){
			children = new ArrayList<Layer>();
		}
		
		children.add(layer);
	}

}
