package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;

public class Controller {

	protected int upButton = KeyEvent.TSK_UP_ARROW;
	
	protected int downButton = KeyEvent.TSK_DOWN_ARROW;
	
	protected int leftButton = KeyEvent.TSK_LEFT_ARROW;
	
	protected int rightButton = KeyEvent.TSK_RIGHT_ARROW;
	
	protected int ButtonA = KeyEvent.TSK_Z;
	
	protected int ButtonB = KeyEvent.TSK_X;
	
	protected int ButtonC = KeyEvent.TSK_C;
	
	protected int startButton = KeyEvent.TSK_ENTER;
	
	protected int selectButton = KeyEvent.TSK_SPACE;
	
	public Controller(){
		super();
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
		
}
