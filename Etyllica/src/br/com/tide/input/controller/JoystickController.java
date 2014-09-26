package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;
import br.com.tide.input.controller.Controller;

public class JoystickController extends Controller {

	private int centerX = KeyEvent.TSK_JOYSTICK_CENTER_X;

	private int centerY = KeyEvent.TSK_JOYSTICK_CENTER_Y;
	
	private boolean rightPressed = false;
	
	private boolean leftPressed = false;

	public JoystickController(ControllerListener listener) {
		super(listener);

		upButton = KeyEvent.TSK_JOYSTICK_UP;

		downButton = KeyEvent.TSK_JOYSTICK_DOWN;

		leftButton = KeyEvent.TSK_JOYSTICK_LEFT;

		rightButton = KeyEvent.TSK_JOYSTICK_RIGHT;

		ButtonA = KeyEvent.TSK_JOYSTICK_BUTTON_3;

		ButtonB = KeyEvent.TSK_JOYSTICK_BUTTON_1;

		ButtonC = KeyEvent.TSK_JOYSTICK_BUTTON_2;

		startButton = KeyEvent.TSK_JOYSTICK_BUTTON_8;

		selectButton = KeyEvent.TSK_JOYSTICK_BUTTON_7;

	}

	public void handleEvent(KeyEvent event) {

		if(event.isKeyDown(rightButton)) {
			listener.onRightButtonPressed();
			rightPressed = true;
		}
		
		if(event.isKeyDown(leftButton)) {
			listener.onLeftButtonPressed();
			leftPressed = true;
		}

		if(event.isKeyDown(centerX)) {
			if(rightPressed) {
				listener.onRightButtonReleased();
				rightPressed = false;
			}

			if(leftPressed) {
				listener.onLeftButtonReleased();
				leftPressed = false;
			}
		}


		if(event.isKeyDown(upButton)) {
			listener.onUpButtonPressed();
		}else if(event.isKeyDown(centerY)) {
			listener.onUpButtonReleased();
		}

		if(event.isKeyDown(downButton)) {
			listener.onDownButtonPressed();
		}else if(event.isKeyDown(centerY)) {
			listener.onDownButtonReleased();
		}

		if(event.isKeyDown(ButtonA)) {
			listener.onAButtonPressed();
		}else if(event.isKeyUp(ButtonA)) {
			listener.onAButtonReleased();
		}

		if(event.isKeyDown(ButtonB)) {
			listener.onBButtonPressed();
		}else if(event.isKeyUp(ButtonB)) {
			listener.onBButtonReleased();
		}

		if(event.isKeyDown(ButtonC)) {
			listener.onCButtonPressed();
		}else if(event.isKeyUp(ButtonC)) {
			listener.onCButtonReleased();
		}		
	}
}
