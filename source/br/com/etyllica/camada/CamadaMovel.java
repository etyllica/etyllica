package br.com.etyllica.camada;

import java.util.TimerTask;
import java.util.Timer;

public class CamadaMovel extends CamadaAnimacao {

	private Timer tymerMovel;
	protected int velocidadeMovel;
	protected boolean imovel;
	
	protected int alvoX;
	protected int alvoY;
	
	protected int px;
	protected int py;
	
	protected int incrementoX;
	protected int incrementoY;
	
	
	public CamadaMovel(int x, int y, int xTile,int yTile){
		super(x,y,xTile,yTile);
		px = 0;
		py = 0;
		
		imovel = true;
		
		velocidadeMovel = 50;
		tymerMovel = null;
	}


	public void movimenta(){
		movimenta(x-incrementoX,y-incrementoY);
	}
	public void movimenta(int alvoX, int alvoY){
		imovel = false;
		
		this.alvoX = alvoX;
		this.alvoY = alvoY;
		
		if(tymerMovel == null)
			moviment();
	}
	
	protected void moviment(){
		tymerMovel = new Timer();
		tymerMovel.scheduleAtFixedRate(new Movimentacao(), velocidadeMovel, velocidadeMovel);
	}

	class Movimentacao extends TimerTask {
		public void run() {
			preMovimenta();
		}
	}
	
	public void preMovimenta(){
		
		//O ideal é fazer em relação a X e a Y
		
		if(alvoX!=x+px)
			setOffsetX(incrementoX);
		else{
			incrementoX = 0;
		}

		if(alvoY!=y+py)
			setOffsetY(incrementoY);
		else{
			incrementoY = 0;	
		}
		
		
		if((alvoX!=x+px)&&(alvoY!=y+py)){
			imovel = true;
		}
		
		
	}
	
	public void setIncrementoX(int incrementoX) {
		this.incrementoX = incrementoX;
	}

	public void setIncrementoY(int incrementoY) {
		this.incrementoY = incrementoY;
	}
	
	public void setVelocidadeMovel(int velocidadeMovel) {
		this.velocidadeMovel = velocidadeMovel;
	}
	public int getIncrementoX(){
		return incrementoX;
	}
	public int getIncrementoY(){
		return incrementoY;
	}
	
	public void desMovimenta(){
		desMoviment();
	}
	protected void desMoviment(){
		imovel = true;
		tymerMovel.cancel();
		tymerMovel.purge();
	}
	
	public boolean getImovel(){
		return imovel;
	}

}
