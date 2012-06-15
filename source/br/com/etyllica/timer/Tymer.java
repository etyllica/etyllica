package br.com.etyllica.timer;


public class Tymer {

	private long tempoAnterior;
	private long velocidade;
	private boolean parado = false;


	public Tymer(){
		tempoAnterior = 0;
		velocidade = 100;
	}

	public long getTime() {

		//return System.nanoTime();
		return System.currentTimeMillis();
	}

	public boolean passou(){

		if(!parado){

			long tempoAtual = getTime();

			if(tempoAtual-tempoAnterior>=velocidade){

				tempoAnterior = tempoAtual;

				return true;
			}
			
		}

		return false;

	}

	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}

	public long getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(long velocidade) {
		this.velocidade = velocidade;
	}
		
}