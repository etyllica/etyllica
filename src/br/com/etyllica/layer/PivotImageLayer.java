package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class PivotImageLayer extends ImageLayer{

	protected int xPivot;
	protected int yPivot;
	
	public PivotImageLayer(int x, int y){
		super(x,y);
		
		xPivot = 0;
		yPivot = 0;
	}
	
	public PivotImageLayer(int x, int y, String caminho){
		super(x,y,caminho);
		
		xPivot = 0;
		yPivot = 0;
	}
	
	public PivotImageLayer(){
		super();
		
		xPivot = 0;
		yPivot = 0;
	}
	
	public void setPivotCoordinates(int xPivot,int yPivot){
		this.xPivot = xPivot;
		this.yPivot = yPivot;
	}
	public int getXPivot(){
		//return x+xPivot;
		return xPivot;
	}
	public int getYPivot(){
		//return y+yPivot;
		return yPivot;
	}
	
	@Override
	public void draw(Grafico g){
		if(visible){
			g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+w,y+h,
					xImage,yImage,xImage+w,yImage+h, null );

		}		
	}
}
