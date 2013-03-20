package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
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
	public boolean colisionRotated(int mx, int my){

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
		int leftX = px - w / 2;
		int rightX = px + w / 2;
		int topY = py + 2*yPivot - h / 2;
		int bottomY = py +2*yPivot + h / 2;

		return (leftX <= rotatedX && rotatedX <= rightX && topY <= rotatedY && rotatedY <= bottomY);
	}

	@Override	
	public void draw(Grafico g){
		if(visible){

			if(angle==0){
				g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w, y+h,
						xImage,yImage,xImage+w,yImage+h, null );
			}else{

				AffineTransform reset = g.getTransform();

				AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(angle),x+xPivot, y+yPivot);

				g.setTransform(transform);
				g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w, y+h,
						xImage,yImage,xImage+w,yImage+h, null );
				g.setTransform(reset);
			}
		}
	}
}
