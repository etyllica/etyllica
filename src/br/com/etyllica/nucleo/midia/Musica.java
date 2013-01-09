package br.com.etyllica.nucleo.midia;

public class Musica{

	private String caminho;
	private int tamanho;

	public Musica(String caminho){
		this(caminho,0);
		
	}
	public Musica(String caminho, int tamanho){
		this.caminho = caminho;
		this.tamanho = tamanho;
	}
	
	public String getCaminho(){
		return caminho;
	}
	
	public int getTamanho(){
		return tamanho;
	}
	public void setTamanho(int tamanho){
		this.tamanho = tamanho;
	}

}
