package br.com.etyllica.nucleo.midia;


public class SomWav{ 

	private String caminho = null;

	private boolean tocando = false; 
	
	public SomWav(String wavfile) { 
		caminho = wavfile;
		tocando = false;
	}  

	public String getCaminho(){
		return caminho;
	}
	
	public boolean isTocando(){
		return tocando;
	}
	
} 
