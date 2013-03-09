package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.timer.Timer;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class AnimatedImageLayer extends ImageLayer{

	protected int xTile = 0;
	protected int yTile = 0;

	protected boolean once = false;
	protected boolean stopped = true;
	protected boolean oscilar = false;
	protected boolean animaEmX = true;

	protected boolean lockOnce = false;

	private int inc = 1;

	private int loop = 0;

	protected int numeroFrames = 1;
	protected int frameAtual = 0;

	protected Timer tymerAnimacao = new Timer();

	public AnimatedImageLayer(int x, int y){
		this(x, y, 0, 0);
	}

	public AnimatedImageLayer(int x, int y, int xTile, int yTile, String caminho){
		super(x,y,caminho);
		this.xTile = xTile;
		this.yTile = yTile;
	}

	public AnimatedImageLayer(int x, int y, int xTile, int yTile){
		super(x,y);
		this.xTile = xTile;
		this.yTile = yTile;
	}


	protected void reset(){
		xImage = 0;
		yImage = 0;
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

		stopped = false;
	}

	public void desAnima(){
		desanim();
		//tymerAnimacao();
	}
	protected void desanim(){
		tymerAnimacao.setParado(true);
	}

	public void animaOnce(){

		visible = true;
		lockOnce = false;
		once = true;
		stopped = false;

		frameAtual = 0;

		if(animaEmX){
			xImage = 0;
		}else{
			yImage = 0;
		}

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
				visible = false;
				lockOnce = true;
				//stopped = true;
				setXImage(xTile*frameAtual);
				return;
			}

			if(!oscilar){
				frameAtual = 0;
			}else{
				frameAtual+=inc;
			}

			loop++;

		}

		if(!stopped){

			if(animaEmX){

				setXImage(xTile*frameAtual);
			}
			else{
				setYImage(yTile*frameAtual);
			}
			
		}
	}

	public void setVelocidadeAnimacao(int velocidadeAnimacao){
		this.tymerAnimacao.setVelocidade(velocidadeAnimacao);
	}

	@Override
	public int centralizaX(int xInicial, int xFinal)
	{
		int x;
		x = (((xInicial+xFinal)/2)-(getXTile()/2));
		setX(x);
		return x;
	}

	@Override
	public int centralizaY(int yInicial, int yFinal)
	{
		int y;
		y = (((yInicial+yFinal)/2)-(getYTile()/2));
		setY(y);
		return y;
	}

	@Override
	public boolean colideRetangular(ImageLayer b)
	{
		if(b.getX() + b.getW() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getH() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}

	public boolean colideRetangular(AnimatedImageLayer b)
	{
		if(b.getX() + b.getXTile() < getX())	return false;
		if(b.getX() > getX() + getXTile())		return false;

		if(b.getY() + b.getYTile() < getY())	return false;
		if(b.getY() > getY() + getYTile())		return false;

		return true;
	}

	public boolean colideCircular(AnimatedImageLayer b)
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

	@Override
	public boolean colideCircular(ImageLayer b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getW()/2 + xTile/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	@Override
	public void draw(Grafico g){
		if(visible){
			g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+xTile,y+yTile,
					xImage,yImage,xImage+xTile,yImage+yTile, null );
		}
	}

	public void setStopped(boolean stopped){
		this.stopped = stopped;
	}
	
	public boolean isStopped(){
		return stopped;
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

	public void setLockOnce(boolean lockOnce) {
		this.lockOnce = lockOnce;
	}

}