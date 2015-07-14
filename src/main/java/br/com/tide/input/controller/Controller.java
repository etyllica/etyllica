package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;

public class Controller {
	
	protected ControllerListener listener;
	
	protected int upButton = KeyEvent.VK_UP;
	
	protected int downButton = KeyEvent.VK_DOWN;
	
	protected int leftButton = KeyEvent.VK_LEFT;
	
	protected int rightButton = KeyEvent.VK_RIGHT;
	
	protected int ButtonA = KeyEvent.VK_Z;
	
	protected int ButtonB = KeyEvent.VK_X;
	
	protected int ButtonC = KeyEvent.VK_C;
	
	protected int ButtonX = KeyEvent.VK_V;
	
	protected int ButtonY = KeyEvent.VK_B;
	
	protected int ButtonZ = KeyEvent.VK_N;
	
	protected int startButton = KeyEvent.VK_ENTER;
	
	protected int selectButton = KeyEvent.VK_SPACE;
	
	public Controller(){
		super();
	}
	
	public Controller(ControllerListener listener) {
		super();
		
		this.listener = listener;
	}

	public int getUpButton() {
		return upButton;
	}

	public void setUpButton(int upButton) {
		this.upButton = upButton;
	}

	public int getDownButton() {
		return downButton;
	}

	public void setDownButton(int downButton) {
		this.downButton = downButton;
	}

	public int getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(int leftButton) {
		this.leftButton = leftButton;
	}

	public int getRightButton() {
		return rightButton;
	}

	public void setRightButton(int rightButton) {
		this.rightButton = rightButton;
	}

	public int getButtonA() {
		return ButtonA;
	}

	public void setButtonA(int buttonA) {
		ButtonA = buttonA;
	}

	public int getButtonB() {
		return ButtonB;
	}

	public void setButtonB(int buttonB) {
		ButtonB = buttonB;
	}

	public int getButtonC() {
		return ButtonC;
	}

	public void setButtonC(int buttonC) {
		ButtonC = buttonC;
	}

	public int getStartButton() {
		return startButton;
	}

	public void setStartButton(int startButton) {
		this.startButton = startButton;
	}
	
	public void handleEvent(KeyEvent event) {

		if(event.isKeyDown(rightButton)) {
			listener.onRightButtonPressed();
		}else if(event.isKeyUp(rightButton)) {
			listener.onRightButtonReleased();
		}

		if(event.isKeyDown(leftButton)) {
			listener.onLeftButtonPressed();
		}else if(event.isKeyUp(leftButton)) {
			listener.onLeftButtonReleased();
		}

		if(event.isKeyDown(upButton)) {
			listener.onUpButtonPressed();
		}else if(event.isKeyUp(upButton)) {
			listener.onUpButtonReleased();
		}

		if(event.isKeyDown(downButton)) {
			listener.onDownButtonPressed();
		}else if(event.isKeyUp(downButton)) {
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

	public ControllerListener getListener() {
		return listener;
	}
		
}
