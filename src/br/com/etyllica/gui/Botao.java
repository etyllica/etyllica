package br.com.etyllica.gui;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.camada.CamadaEstatica;

public class Botao extends BotaoBool{
	
	protected String normal;
	protected String sobMouse;
	protected String click;

	protected String som = null;
	protected boolean tocou = false;

	public Botao(int x, int y, String normal){
		super(x,y,normal);
		this.normal = normal;
		this.sobMouse = normal;
		this.click = normal;
		
	}
	public Botao(int x, int y, String normal, String sobMouse){
		this(x,y,normal);
		this.sobMouse = sobMouse;
		this.click = sobMouse;
		
	}
	public Botao(int x, int y, String normal, String sobMouse, Camada label){
		this(x,y,normal,sobMouse);
		setLabel(label);
	}
	public Botao(int x, int y, String normal, String sobMouse, String click){
		this(x,y,normal,sobMouse);
		this.click = click;
	}

	public Botao(int x, int y, CamadaEstatica normal,
			CamadaEstatica sobMouse) {
		this(x,y,normal.getCaminho(),sobMouse.getCaminho());
		this.xLimite = normal.getXLimite();
		this.yLimite = normal.getYLimite();
	}

	public Botao(int x, int y, CamadaEstatica normal,
			CamadaEstatica sobMouse, CamadaEstatica click) {
		this(x,y,normal.getCaminho(),sobMouse.getCaminho(),click.getCaminho());
		this.xLimite = normal.getXLimite();
		this.yLimite = normal.getYLimite();
	}

	public void setSom(String som){
		this.som = som;
	}

	public void desenha(){		
		g.desenha(this);
		if(label!=null)
			g.desenha(label);
	}
	
	public int gerencia(){

		return gerencia(false);
		
	}
	
	private void acionaBotao(){
		igualaImagem(click);
	}
	
	public int gerencia(boolean evento){

			if(mouse.getPressionado()>0){

				if(mouse.sobMouse(this)){
					
					acionado = mouse.getPressionado();
					acionaBotao();
					
					mouse.desPressiona();
					
				}
			}
			else{
				
				if(mouse.sobMouse(this)){
					
					igualaImagem(sobMouse);
					
					if(!tocou){
						if(som!=null){
							midia.tocaSom(som);
						}
						tocou = true;
					}
					
					if(!evento){
						
						acionado = -1;
					
					}else{
						
						//acionado = 10;
						acionado = 1;
						acionaBotao();
						
					}
				}
				else{
					igualaImagem(normal);
					tocou = false;
					acionado = -2;
				}

			}

			return acionado;
		
	}
	
	public void setX(int x){
		this.x = x;
		
		centralizaLabel();		
	}
	
	public void setY(int y){
		this.y = y;
		
		centralizaLabel();		
	}
	
	public void setCoordenadas(int x, int y){
		this.x = x;
		this.y = y;
		
		centralizaLabel();
	}
	
	public void centralizaX(){
		setX((g.getLargura()/2)-xLimite/2);		
	}

	private void centralizaLabel(){
		if(label!=null)
			label.centraliza(this);
	}

}
