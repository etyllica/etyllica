package br.com.etyllica.core.input;

import br.com.etyllica.core.input.joystick.Joystick;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;

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
	
	private Keyboard teclado;
	
	private Joystick joystick;
		
	public HIDController(){
		
		mouse = new Mouse(0,0);
		
		teclado = new Keyboard();
		
		joystick = new Joystick();

	}

	public Mouse getMouse() {
		return mouse;
	}
	
	public Keyboard getTeclado() {
		return teclado;
	}

	public Joystick getJoystick() {
		return joystick;
	}	

}
