package br.com.etyllica.core.input;

import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;

public interface HIDController {
	Mouse getMouse();
	Keyboard getKeyboard();
}
