package br.com.etyllica.core.loader;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.LoadApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader{
	
	private Application m = null;
	private LoadApplication load;
	
	private boolean locked = true;

	public ApplicationLoader(Application m, LoadApplication load){
		this.m = m;
		this.load = load;
	}

	public void run(){
		
		locked = true;
		
		while(m.getLoading()<100){
			load.setText(m.getLoadingPhrase(), m.getLoading());
		}
		
		locked = false;	
	}

	public boolean isLocked() {
		return locked;
	}
	
}