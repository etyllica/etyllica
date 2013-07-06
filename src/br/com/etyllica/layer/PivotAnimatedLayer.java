package br.com.etyllica.layer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class PivotAnimatedLayer extends AnimatedLayer{

	private int xPivot;
	private int yPivot;
	
	public PivotAnimatedLayer(int xTile, int yTile) {
		this(0, 0, xTile, yTile);
	}
	
	public PivotAnimatedLayer(int x, int y, int xTile, int yTile) {
		super(x, y, xTile, yTile);
	}

	public void setXPivot(int xPivot){
		this.xPivot = xPivot;
	}
	
	public void setYPivot(int yPivot){
		this.yPivot = yPivot;
	}
	
	public void setCoordenadasPivot(int xPivot,int yPivot){
		this.xPivot = xPivot;
		this.yPivot = yPivot;
	}
	
	public int getXPivot(){
		return xPivot;
	}
	
	public int getYPivot(){
		return yPivot;
	}
	
}
