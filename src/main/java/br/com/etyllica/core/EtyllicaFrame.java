package br.com.etyllica.core;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.GUIEvent;


public interface EtyllicaFrame {

	public void init();
	
	public void draw();
	
	public Application startApplication();
	
	public void updateSuperEvent(GUIEvent event);
	
}
