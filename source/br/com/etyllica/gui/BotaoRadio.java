package br.com.etyllica.gui;


import br.com.etyllica.camada.Camada;

public class BotaoRadio extends BotaoBool{

	//Camada labelOnm;
	Camada labelFixa;

	boolean check = false;

	public BotaoRadio(int x, int y, String normal, String labelOnm){
		super(x,y,normal);

		label = new Camada(labelOnm);
		label.centraliza(this);

		labelFixa = this.label;

	}
	
	public BotaoRadio(int x, int y, String normal, String labelOnm, String labelFixa){
		this(x,y,normal,labelOnm);

		this.labelFixa = new Camada(labelFixa);
		this.labelFixa.centraliza(this);
	}

	public void desenha(){
		g.desenha(this);
		
		if(onMouse)
			g.desenha(label);
		
		if(check)
			g.desenha(labelFixa);
	}
	
	public int gerencia(){

		if(mouse.sobMouse(this)){
			
			onMouse = true;
			
			if(mouse.getPressionado()>0){
				acionado = mouse.getPressionado();
				marca();
			}
			
		}
		else{
			onMouse = false;
			acionado = 0;
		}		
		
		return acionado;
	}
	
	public void desmarca(){
		check = false;
	}
	
	public void marca(){
		check = true;
	}


}
