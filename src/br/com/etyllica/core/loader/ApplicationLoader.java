package br.com.etyllica.core.loader;

import br.com.etyllica.core.application.Application;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class ApplicationLoader extends Thread{
	
	Application m = null;

	public ApplicationLoader(Application m){
		this.m = m;
	}

	public void run(){
		if(m.getCarregando()<100){
			m.load();
		}
	}
	
	public int getLoaded(){
		return m.getCarregando();
	}
	
	public String getCarregandoFrase(){
		return m.getCarregandoFrase();
	}

}
