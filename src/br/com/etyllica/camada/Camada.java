package br.com.etyllica.camada;

import java.awt.Image;

import br.com.etyllica.linear.Retangulo;

public class Camada extends CamadaEstatica{

	protected int x = 0;
	protected int y = 0;

	protected int xImagem = 0;
	protected int yImagem = 0;

	protected boolean aparecendo = true;
	protected double angulo = 0;

	protected AreaColisao areaColisao = null;

	public Camada(){
		super("");
	}

	public Camada(int x, int y){
		this();
		this.x = x;
		this.y = y;
	}

	public Camada(int x, int y, int xLimite, int yLimite){
		super("");
		this.x = x;
		this.y = y;
		this.xLimite = xLimite;
		this.yLimite = yLimite;
	}

	public Camada(int x, int y, int xLimite, int yLimite, String caminho){
		super(caminho);
		this.x = x;
		this.y = y;
		this.xLimite = xLimite;
		this.yLimite = yLimite;
	}

	public Camada(int x, int y, String caminho){
		super(caminho);
		setCoordenadas(x, y);
	}

	public Camada (String caminho){
		this(0,0,caminho);
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	public int getXImagem() {
		return xImagem;
	}
	public void setXImagem(int imagem) {
		xImagem = imagem;
	}
	public int getYImagem() {
		return yImagem;
	}
	public void setYImagem(int imagem) {
		yImagem = imagem;
	}

	public void setAparecendo(boolean aparecendo)
	{
		this.aparecendo = aparecendo;	
	}
	public boolean getAparecendo()
	{
		return aparecendo;	
	}
	public void mudaAparecendo()
	{
		if(aparecendo){
			aparecendo = false;
		}
		else{
			aparecendo = true;
		}
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public AreaColisao getAreaColisao() {
		return areaColisao;
	}

	public void setAreaColisao(AreaColisao areaColisao) {
		this.areaColisao = areaColisao;
	}

	public void setCoordenadas(int x , int y) {
		this.x = x;
		this.y = y;
	}

	public void setOffsetX(int offsetX){
		x+=offsetX;
	}
	public void setOffsetY(int offsetY){
		y+=offsetY;
	}

	public void setOffset(int offsetX, int offsetY){
		setOffsetX(offsetX);
		setOffsetY(offsetY);
	}

	public void setCoordImagem(int xImagem , int yImagem) {
		this.xImagem = xImagem;
		this.yImagem = yImagem;
	}	


	public void centraliza(int x, int y, int xLimite, int yLimite){
		centralizaX(x,x+xLimite);
		centralizaY(y,y+yLimite);
	}

	public void centraliza(Camada cam){
		centralizaX(cam);
		centralizaY(cam);
	}
	public void centralizaX(Camada b){
		centralizaX(b.getX(),b.getX()+b.getXLimite());
	}
	public int centralizaX(int xInicial, int xFinal)
	{
		int x;
		x = (((xInicial+xFinal)/2)-(getXLimite()/2));
		setX(x);
		return x;
	}
	public void centralizaY(Camada b){
		centralizaY(b.getY(),b.getY()+b.getYLimite());
	}
	public int centralizaY(int yInicial, int yFinal)
	{
		int y;
		y = (((yInicial+yFinal)/2)-(getYLimite()/2));
		setY(y);
		return y;
	}


	public boolean colideRetangular(int bx, int by, int bxLimite, int byLimite){

		if(bx + bxLimite < getX())	return false;
		if(bx > getX() + getXLimite())		return false;

		if(by + byLimite < getY())	return false;
		if(by > getY() + getYLimite())		return false;

		return true;

	}


	public boolean colideRetangular(Camada b)
	{
		return colideRetangular(b.getX(), b.getY(), b.getXLimite(), b.getYLimite());
	}

	public boolean colideCircular(Camada b)
	{
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getXLimite()/2 + getXLimite()/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}
	
	private boolean colideRetangulo(Retangulo rect, Retangulo rect2, int rect2x, int rect2y){
				
		if(rect2x+rect2.getX() + rect2.getW() < x+rect.getX())	return false;
		if(rect2x+rect2.getX() > x+rect.getX() + rect.getW())		return false;

		if(rect2y+rect2.getY() + rect2.getH() < y+rect.getY())	return false;
		if(rect2y+rect2.getY() > y+rect.getY() + rect.getH())		return false;

		return true;	
	}

	private boolean colideAreaRetangulo(Retangulo rect2, int rect2x, int rect2y){
		
		for(Retangulo rect: areaColisao.getArea()){
			
			if(colideRetangulo(rect, rect2, rect2x, rect2y)){
				return true;
			}
			
		}

		return false;
	}
	
	public boolean colideAreaCamada(CamadaAnimacao b){
		Retangulo rect2 = new Retangulo(0, 0, b.getXTile(), b.getYTile());
		return colideAreaRetangulo(rect2,b.getX(), b.getY());
	}
	
	public boolean colideAreaCamada(Camada b){
		Retangulo rect2 = new Retangulo(0, 0,b.getXLimite(),b.getYLimite());
		return colideAreaRetangulo(rect2,b.getX(),b.getY());
	}
	
	public boolean colideAreas(Camada b){

		for(Retangulo rect: areaColisao.getArea()){
			
			for(Retangulo rect2: b.getAreaColisao().getArea()){
				
				if(colideRetangulo(rect, rect2, b.getX(), b.getY())){
					return true;
				}
				
			}

		}

		return false;
	}


	public void igualaImagem(Image img) {
		xLimite = img.getWidth(null);
		yLimite = img.getHeight(null);
	}

	public void igualaImagem(Camada cam) {
		igualaImagem(cam.getCaminho());
		xLimite = cam.getXLimite();
		yLimite = cam.getYLimite();
	}

}
