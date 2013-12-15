package br.com.etyllica.core.input;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

public interface InputListener {

	public void updateKeyEvent(KeyEvent event);
	
	public void updateMouseEvent(PointerEvent event);
	
}
