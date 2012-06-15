package br.com.etyllica.camada;

public class CamadaPivot extends Camada{

	int xPivot;
	int yPivot;
	
	public CamadaPivot(int x, int y, String caminho){
		super(x,y,caminho);
		
		xPivot = 0;
		yPivot = 0;
	}
	
	public CamadaPivot(){
		super();
		
		xPivot = 0;
		yPivot = 0;
	}
	
	public void setCoordenadasPivot(int xPivot,int yPivot){
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
}
