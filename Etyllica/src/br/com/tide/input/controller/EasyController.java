package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;

public class EasyController extends Controller {

	public EasyController(ControllerListener listener) {
		super(listener);

		upButton = KeyEvent.TSK_UP_ARROW;

		downButton = KeyEvent.TSK_DOWN_ARROW;

		leftButton = KeyEvent.TSK_LEFT_ARROW;

		rightButton = KeyEvent.TSK_RIGHT_ARROW;

		ButtonA = KeyEvent.TSK_Z;

		ButtonB = KeyEvent.TSK_X;

		ButtonC = KeyEvent.TSK_C;

		startButton = KeyEvent.TSK_ENTER;

		startButton = KeyEvent.TSK_SPACE;

	}

}
