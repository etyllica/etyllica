package br.com.etyllica.awt.core.input;

import br.com.etyllica.commons.event.KeyEventListener;
import br.com.etyllica.core.input.HIDController;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.input.JoystickHandler;

/**
 * 
 * HIDController have the input controls, like mouse, keyboard and joystick.
 * 
 * @author yuripourre
 *
 */

public class AWTController implements HIDController {

	private Mouse mouse;
	
	private Keyboard keyboard;
	
	private JoystickHandler joystick;
			
	public AWTController(KeyEventListener listener) {
		
		mouse = new Mouse(0,0);
		
		keyboard = new AWTKeyboard(listener);
		keyboard.init();
		
		joystick = JoystickHandler.getInstance();
		joystick.setListener(listener);
	}

	public Mouse getMouse() {
		return mouse;
	}
	
	public Keyboard getKeyboard() {
		return keyboard;
	}

}
