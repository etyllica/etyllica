package br.com.etyllica.core.context;

import java.awt.Component;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;



public abstract class Application extends Context {
		
	public Application(int w, int h) {
		super(w, h);
	}
	
	public Application(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {
		
	}

	@Override
	public void updateMouse(PointerEvent event) {
		
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	public Component getComponent() {
		return parent.getComponent();
	}
	
}
