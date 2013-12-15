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

public class PivotImageLayer extends ImageLayer{

	protected float xPivot = 0;
	protected float yPivot = 0;

	public PivotImageLayer(float x, float y){
		super(x,y);
	}

	public PivotImageLayer(float x, float y, String caminho){
		super(x,y,caminho);

		this.xPivot = w/2;
		this.yPivot = h/2;
	}	

	public void setPivotCoordinates(float xPivot,float yPivot){
		this.xPivot = xPivot;
		this.yPivot = yPivot;
	}

	public float getXPivot(){
		return xPivot;
	}

	public float getYPivot(){
		return yPivot;
	}

	@Override
	public boolean colisionRotated(int mx, int my){

		//Pivot Point of rotation
		float px = x+xPivot;
		float py = y+yPivot;

		double c = Math.cos(Math.toRadians(-angle));
		double s = Math.sin(Math.toRadians(-angle));

		// UNrotate the point depending on the rotation of the rectangle
		double rotatedX = px + c * (mx - px) - s * (my - py);
		double rotatedY = py + s * (mx - px) + c * (my - py);

		// perform a normal check if the new point is inside the 
		// bounds of the UNrotated rectangle
		float leftX = px - xPivot;
		float rightX = px - xPivot + w;
		float topY = py - yPivot;
		float bottomY = py - yPivot + h;

		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	@Override	
	public void draw(Graphic g){
		
		if(visible){
			
			if(opacity<255){
				g.setOpacity(opacity);
			}

			AffineTransform transform = new AffineTransform();

			if(angle!=0){

				transform = AffineTransform.getRotateInstance(Math.toRadians(angle),x+xPivot, y+yPivot);

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

			g.setTransform(transform);
			
			g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w, y+h,
					xImage,yImage,xImage+w,yImage+h, null );

			g.resetTransform();

			if(opacity<255){
				g.resetOpacity();
			}
			
		}
	}
}
