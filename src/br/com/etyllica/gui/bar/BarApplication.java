package br.com.etyllica.gui.bar;

import br.com.etyllica.core.application.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class BarApplication extends Application{

	protected boolean onMouse = false;

	public BarApplication(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
}

