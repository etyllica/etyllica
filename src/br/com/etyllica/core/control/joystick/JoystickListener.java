package br.com.etyllica.core.control.joystick;

import java.util.EventListener;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public interface JoystickListener extends EventListener {

	public void moveAxisX(int value);
	public void moveAxisY(int value);
	public void buttonPressed(int button);
	public void buttonReleased(int button);
	
}
