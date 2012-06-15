package br.com.etyllica.nucleo.midia;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ThreadPlayer extends Thread{

	Player player;

	boolean tocando = true;

	public ThreadPlayer(InputStream stream) {

		try {
			player = new Player(stream);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run(){

		while(tocando){
			try {
				player.play();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//player.close();
			tocando = false;
		}

	}
	public void para(){
		//Alguma sugest�o melhor? 
		player.close();
		tocando = false;
	}

	public boolean isTocando(){
		return tocando;
	}

}
