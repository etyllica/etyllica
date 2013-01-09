package br.com.etyllica.nucleo.controle;

import java.awt.event.KeyListener;



public interface Pad extends KeyListener{

	public boolean getTecla(Tecla ptecla);
	
	public boolean getTeclaOnce(Tecla ptecla);

	public boolean getTecla(int indice);	

	public boolean getTeclaOnce(int indice);
	
	public void reset();

}
