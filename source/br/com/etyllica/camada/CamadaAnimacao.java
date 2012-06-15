package br.com.etyllica.camada;

import br.com.etyllica.timer.Tymer;


public class CamadaAnimacao extends Camada{
	
	protected int xTile = 0;
	protected int yTile = 0;

	protected boolean once = false;
	protected boolean parado = false;
	protected boolean oscilar = false;
	protected boolean animaEmX = true;

	protected boolean lockOnce = false;

	private int inc = 1;

	private int loop = 0;

	protected int numeroFrames = 1;
	protected int frameAtual = 0;

	protected Tymer tymerAnimacao = new Tymer();

	public CamadaAnimacao(int x, int y){
		this(x, y, 0, 0);
	}

	public CamadaAnimacao(int x, int y, int xTile, int yTile, String caminho){
		super(x,y,caminho);
		this.xTile = xTile;
		this.yTile = yTile;
	}

	public CamadaAnimacao(int x, int y, int xTile, int yTile){
		super(x,y);
		this.xTile = xTile;
		this.yTile = yTile;
	}


	protected void reset(){
		xImagem = 0;
		yImagem = 0;
		frameAtual = 0;
	}

	public void setAnimaEmX(boolean animaX){
		animaEmX = animaX;
	}

	public int getXTile(){
		return xTile;
	}
	public int getYTile(){
		return yTile;
	}
	public void setXTile(int xTile){
		this.xTile = xTile;
	}
	public void setYTile(int yTile){
		this.yTile = yTile;
	}

	public void setCoordTiles(int xTile, int yTile){
		setXTile(xTile);
		setYTile(yTile);
	}

	public void setNumeroFrames(int numeroFrames){
		this.numeroFrames = numeroFrames;
	}

	public void anima(){
		anim();
	}
	protected void anim(){

		if(!lockOnce){

			tymerAnimacao.setParado(false);

			if(tymerAnimacao.passou()){
				preAnima();
			}
		}
	}
	
	public void desAnima(){
		desanim();
		//tymerAnimacao();
	}
	protected void desanim(){
		tymerAnimacao.setParado(true);
	}

	public void animaOnce(){
		
		if(animaEmX){
			xImagem = 0;
		}else{
			yImagem = 0;
		}			
		
		once = true;		
		lockOnce = false;
	}

	public void preAnima(){

		if((frameAtual < numeroFrames-1)&&(frameAtual >= 0)){

			if(oscilar){
				inc *= -1;
			}

			frameAtual+=inc;
		}
		else{

			if(once){
				lockOnce = true;
				setXImagem(xTile*frameAtual);
				return;
			}
			
			if(!oscilar)
				frameAtual = 0;
			else
				frameAtual+=inc;

			loop++;

		}

		if(!animaEmX){
			setYImagem(yTile*frameAtual);
		}
		else{
			setXImagem(xTile*frameAtual);
		}
	}

	public void setVelocidadeAnimacao(int velocidadeAnimacao){
		this.tymerAnimacao.setVelocidade(velocidadeAnimacao);
	}

	public void centraliza(Camada b){
		centralizaX(b);
		centralizaY(b);
	}
	public void centralizaX(Camada b){
		centralizaX(b.getX(),b.getX()+b.getXLimite());
	}
	public int centralizaX(int xInicial, int xFinal)
	{
		int x;
		x = (((xInicial+xFinal)/2)-(getXTile()/2));
		setX(x);
		return x;
	}
	public void centralizaY(Camada b){
		centralizaY(b.getY(),b.getY()+b.getYLimite());
	}
	public int centralizaY(int yInicial, int yFinal)
	{
		int y;
		y = (((yInicial+yFinal)/2)-(getYTile()/2));
		setY(y);
		return y;
	}
	public boolean colideRetangular(Camada b)
	{
		if(b.getX() + b.getXLimite() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getYLimite() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}
	public boolean colideRetangular(CamadaAnimacao b)
	{
		if(b.getX() + b.getXTile() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getYTile() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}

	public boolean colideCircular(CamadaAnimacao b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getXTile()/2 + xTile/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}
	public boolean colideCircular(Camada b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getXLimite()/2 + xTile/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	public boolean getParado(){
		return parado;
	}
	public int getNumeroFrames(){
		return numeroFrames;
	}
	public boolean getAnimaEmX(){
		return animaEmX;
	}
	public int getLoop(){
		return loop;
	}

}