package br.com.etyllica.camada;

public class CamadaEstatica {
	
	protected String caminho = "";
	
	protected int xLimite = 0;
	protected int yLimite = 0;

	public CamadaEstatica(String caminho){
		this.caminho = caminho;
	}
	public String getCaminho(){
		return caminho;
	}
	
	public void igualaImagem(String caminho) {
		this.caminho = caminho;
	}

	public int getXLimite() {
		return xLimite;
	}

	public void setXLimite(int limite) {
		xLimite = limite;
	}

	public int getYLimite() {
		return yLimite;
	}

	public void setYLimite(int limite) {
		yLimite = limite;
	}
	public void setCoordLimite(int xLimite , int yLimite) {
		this.xLimite = xLimite;
		this.yLimite = yLimite;
	}
}
