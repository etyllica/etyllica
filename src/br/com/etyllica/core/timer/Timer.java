package br.com.etyllica.core.timer;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Timer {

	private long tempoAnterior;
	private long speed;
	private boolean stopped = false;

	public Timer(int delay){
		tempoAnterior = 0;
		this.speed = delay;
	}

	public Timer(){
		tempoAnterior = 0;
		speed = 100;
	}

	public long getTime() {

		//return System.nanoTime();
		return System.currentTimeMillis();
	}

	public boolean passou(){

		if(!stopped){

			long tempoAtual = getTime();

			if(tempoAtual-tempoAnterior>=speed){

				tempoAnterior = tempoAtual;

				return true;
			}
			
		}

		return false;

	}

	public boolean isParado() {
		return stopped;
	}

	public void setParado(boolean parado) {
		this.stopped = parado;
	}

	public long getVelocidade() {
		return speed;
	}

	public void setVelocidade(long velocidade) {
		this.speed = velocidade;
	}
		
}