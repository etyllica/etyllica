package br.com.etyllica.gui;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.nucleo.Componente;
import br.com.etyllica.nucleo.Gerenciador;
import br.com.etyllica.nucleo.controle.PrincipalControle;
import br.com.etyllica.nucleo.midia.Multimidia;
import br.com.etyllica.nucleo.video.Grafico;

public abstract class ComponenteGui extends Camada implements Componente{
	
	protected Grafico g;
	protected PrincipalControle controle;
	protected Multimidia midia;

	public ComponenteGui(int x, int y) {
		super(x,y);
		this.g = Gerenciador.getInstancia().getGrafico();
		this.midia = Gerenciador.getInstancia().getMultimidia();
		this.controle = Gerenciador.getInstancia().getControle();
	}
	
	public ComponenteGui(){
		this(0,0);
	}
	
	public int gerencia(){
		return gerencia(false);
	}
	
	public abstract int gerencia(boolean evento);
}
