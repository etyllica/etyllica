package br.com.etyllica.context.load;

import br.com.etyllica.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class DefaultLoadApplication extends Application implements LoadApplication {

	protected String phrase = "Loading...";
	
	protected String percent = "0%";
	
	public DefaultLoadApplication(int w, int h) {
		super(w,h);
	}
	
	public DefaultLoadApplication(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

}