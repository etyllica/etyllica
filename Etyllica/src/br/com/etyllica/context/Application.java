package br.com.etyllica.context;



public abstract class Application extends Context{
		
	public Application(int w, int h) {
		super(w, h);
	}
	
	public Application(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
			
}
