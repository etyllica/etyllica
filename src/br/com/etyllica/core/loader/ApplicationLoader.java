package br.com.etyllica.core.loader;

import br.com.etyllica.core.application.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader extends Thread{
	
	Application m = null;

	public ApplicationLoader(Application m){
		this.m = m;
	}

	public void run(){
		if(m.getLoading()<100){
			m.load();
		}
	}
	
	public int getLoaded(){
		return m.getLoading();
	}
	
	public String getLoadingPhrase(){
		return m.getLoadingPhrase();
	}

}
