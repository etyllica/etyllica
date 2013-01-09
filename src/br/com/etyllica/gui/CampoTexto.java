package br.com.etyllica.gui;

import java.awt.Insets;

import javax.swing.JTextField;

public class CampoTexto extends JTextField{

	private static final long serialVersionUID = 1L;

	public CampoTexto(){
		this(16);	    
	}

	public CampoTexto(int maxlen){
		setOpaque(false);
		setMargin(new Insets(0,4,0,4));  
		setDocument(new TextBox(maxlen));
	}

}
