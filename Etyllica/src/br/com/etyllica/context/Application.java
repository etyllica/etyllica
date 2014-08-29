package br.com.etyllica.context;

import br.com.etyllica.core.event.GUIEvent;
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
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}	
}
