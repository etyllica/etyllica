package br.com.etyllica.gui;

import java.awt.Component;
import java.awt.Container;

import br.com.etyllica.nucleo.Gerenciador;

public class PrincipalGui {
	
	Gerenciador app;
	Container p;

	public PrincipalGui(Gerenciador app, Container p){
		this.app = app;
		this.p = p;
	}
	public void adiciona(Component c){
		p.add(c);
		c.setVisible(true);
	}

}
