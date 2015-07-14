package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class PivotImageLayer extends ImageLayer{

	protected int xPivot = 0;
	protected int yPivot = 0;

	public PivotImageLayer(int x, int y){
		super(x,y);
	}

	public PivotImageLayer(int x, int y, String caminho){
		super(x,y,caminho);

		this.xPivot = w/2;
		this.yPivot = h/2;
	}	

	public void setPivotCoordinates(int xPivot,int yPivot){
		this.xPivot = xPivot;
		this.yPivot = yPivot;
	}

	public int getXPivot(){
		return xPivot;
	}

	public int getYPivot(){
		return yPivot;
	}

	@Override
	public boolean colideRotated(int mx, int my){
		//Pivot Point of rotation
		int px = x+xPivot;
		int py = y+yPivot;

		double c = Math.cos(Math.toRadians(-angle));
		double s = Math.sin(Math.toRadians(-angle));

		// UNrotate the point depending on the rotation of the rectangle
		double rotatedX = px + c * (mx - px) - s * (my - py);
		double rotatedY = py + s * (mx - px) + c * (my - py);

		// perform a normal check if the new point is inside the 
		// bounds of the UNrotated rectangle
		int leftX = px - xPivot;
		int rightX = px - xPivot + w;
		int topY = py - yPivot;
		int bottomY = py - yPivot + h;

		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	@Override
	public AffineTransform getTransform() {
		
		AffineTransform transform = null;
		
		if(angle!=0) {
			
			transform = new AffineTransform();
			
			transform = AffineTransform.getRotateInstance(Math.toRadians(angle),x+xPivot, y+yPivot);			
		}

		if(scaleX != 1 || scaleY != 1) {

			if(transform == null) {
				transform = new AffineTransform();
			}
			
			double sw = w*scaleX;
			double sh = h*scaleY;

			double dx = sw/2-w/2;
			double dy = sh/2-h/2;

			transform.translate(x-w/2-dx, y-h/2-dy);

			AffineTransform scaleTransform = new AffineTransform();

			scaleTransform.translate(w/2, h/2);
			scaleTransform.scale(scaleX, scaleY);
			scaleTransform.translate(-x, -y);

			transform.concatenate(scaleTransform);

		}

		return transform;
	}
}
