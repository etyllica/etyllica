package br.com.etyllica.gui.bar;

import br.com.etyllica.core.application.Application;


public abstract class BarApplication extends Application{

	protected boolean onMouse = false;

	public BarApplication(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
}

