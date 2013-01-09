package br.com.etyllica.nucleo.sessao;


public interface MiniSessao{
	public void carrega();		
	public void desenha();
	public MiniSessao gerencia();
	
	public int getCarregando();
	
	public void setVariaveis(MapaSessao variaveis);
	
}
