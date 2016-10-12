package br.com.etyllica.core.engine;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;


public interface EtyllicaFrame {

	public void init();
	
	public void draw();
	
	public Application startApplication();
	
	public void updateSuperEvent(GUIEvent event);
	
}
