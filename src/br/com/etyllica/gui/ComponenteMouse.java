package br.com.etyllica.gui;

import br.com.etyllica.nucleo.controle.Mouse;

public abstract class ComponenteMouse extends ComponenteGui{

	protected Mouse mouse;
	protected int acionado;
	protected String rotulo;
	
	public ComponenteMouse() {
		this(0,0);
	}
	public ComponenteMouse(int x, int y) {
		super(x,y);
		mouse = controle.getMouse();
		acionado = 0;
	}
	
	public int getAcionado(){
		return acionado;
	}
	
	public void setAcionado(int acionado){
		this.acionado = acionado;
	}
	
	public String getRotulo() {
		return rotulo;
	}
	
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	

}
