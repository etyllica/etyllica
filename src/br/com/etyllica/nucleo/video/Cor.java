package br.com.etyllica.nucleo.video;

import java.awt.Color;


public class Cor{

	
	private int vermelho;
	private int verde;
	private int azul;

	public Cor(int rgb) {
		vermelho = 0xff0000 & rgb;
		verde = 0x00ff00 & rgb;
		azul = 0x0000ff & rgb;
	}

	public void invert(){
		vermelho = 0xff0000-vermelho;
		verde = 0x00ff00-verde;
		azul = 0x0000ff-azul;
	}
	public int getRGB(){
		return vermelho+verde+azul;
	}
	public Color getColor(){
		return new Color(getRGB());
	}
	public void setRGB(int rgb){
		vermelho = 0xff0000 & rgb;
		verde = 0x00ff00 & rgb;
		azul = 0x0000ff & rgb;
	}
}
