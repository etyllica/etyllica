package br.com.etyllica.gui;

import br.com.etyllica.camada.Camada;

public class BotaoBool extends ComponenteMouse{

	protected Camada label;

	protected boolean onMouse = false;

	public BotaoBool(int x, int y, String normal){
		super(x,y);

		Camada aux = g.novaCamada(normal);
		xLimite = aux.getXLimite();
		yLimite = aux.getYLimite();
		igualaImagem(normal);

		label = aux;

	}

	public BotaoBool(int x, int y, String normal, String acionado){
		this(x,y,normal);

		this.label = new Camada(acionado);
		this.label.centraliza(this);
	}

	public void desenha(){
		g.desenha(this);
		if(onMouse)
			g.desenha(label);
	}
	public int gerencia(boolean evento){

		if(mouse.sobMouse(this)){
			onMouse = true;
			if(mouse.getPressionado()>0){
				acionado = mouse.getPressionado();
				mouse.desPressiona();

			}
		}
		else{
			onMouse = false;
		}

		return acionado;
	}

	public void setLabel(Camada label){
		this.label = label;

		//this.label.igualaImagem(g.getImagem(label));

		this.label.centraliza(this);

	}

}
