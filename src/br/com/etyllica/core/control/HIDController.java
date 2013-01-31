package br.com.etyllica.core.control;

import br.com.etyllica.core.control.keyboard.Keyboard;
import br.com.etyllica.core.control.mouse.Mouse;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class HIDController{

	private Mouse mouse;
	
	private Keyboard teclado;
	
	
	public HIDController(){
		
		mouse = new Mouse(0,0);
		
		teclado = new Keyboard();

	}

	public Mouse getMouse() {
		return mouse;
	}
	
	public Keyboard getTeclado() {
		return teclado;
	}
	

}
