package br.com.etyllica.core;

import br.com.etyllica.context.Application;


public interface Engine {

	public void init();
	
	public void draw();
	
	public abstract Application startApplication();
	
}
