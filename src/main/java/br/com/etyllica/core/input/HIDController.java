package br.com.etyllica.core.input;

import br.com.etyllica.awt.core.input.AWTKeyboard;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.loader.JoystickLoader;

/**
 * 
 * HIDController have the input controls, like mouse, keyboard and joystick.
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class HIDController{

	private Mouse mouse;
	
	private Keyboard keyboard;
	
	private JoystickLoader joystick;
			
	public HIDController(InputKeyListener listener){
		
		mouse = new Mouse(0,0);		
		
		keyboard = new AWTKeyboard(listener);
		keyboard.init();
		
		joystick = JoystickLoader.getInstance();
		joystick.setListener(listener);

	}

	public Mouse getMouse() {
		return mouse;
	}
	
	public Keyboard getKeyboard() {
		return keyboard;
	}

}
