package br.com.etyllica.core.application;



public abstract class Application extends Context{
		
	public Application(float w, float h) {
		super(w, h);
	}
	
	public Application(float x, float y, float w, float h) {
		super(x, y, w, h);
	}
			
}
