package br.com.etyllica.core.input;

import br.com.etyllica.core.event.KeyEvent;

public interface InputKeyListener {

	public void updateKeyEvent(KeyEvent event);
		
	public void updateJoystickEvent(KeyEvent event);
	
}
