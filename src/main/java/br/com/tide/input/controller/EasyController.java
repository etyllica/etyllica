package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;

public class EasyController extends Controller {

	public EasyController(ControllerListener listener) {
		super(listener);

		upButton = KeyEvent.VK_UP;

		downButton = KeyEvent.VK_DOWN;

		leftButton = KeyEvent.VK_LEFT;

		rightButton = KeyEvent.VK_RIGHT;

		ButtonA = KeyEvent.VK_Z;

		ButtonB = KeyEvent.VK_X;

		ButtonC = KeyEvent.VK_C;
		
		ButtonX = KeyEvent.VK_SHIFT_LEFT;

		ButtonY = KeyEvent.VK_CTRL_LEFT;

		ButtonZ = KeyEvent.VK_ALT_LEFT;

		startButton = KeyEvent.VK_ENTER;

		selectButton = KeyEvent.VK_SPACE;

	}

}
