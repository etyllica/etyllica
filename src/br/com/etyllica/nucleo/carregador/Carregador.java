package br.com.etyllica.nucleo.carregador;

import br.com.etyllica.nucleo.sessao.MiniSessao;


public class Carregador extends Thread{
	
	MiniSessao m = null;

	public Carregador(MiniSessao m){
		this.m = m;
	}

	public void run(){
		if(m.getCarregando()<100)
			m.carrega();
	}
	
	public int getLoaded(){
		return m.getCarregando();
	}

}
