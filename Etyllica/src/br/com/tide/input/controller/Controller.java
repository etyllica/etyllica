package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;

public class Controller {

	protected int upButton = KeyEvent.TSK_UP_ARROW;
	
	protected int downButton = KeyEvent.TSK_DOWN_ARROW;
	
	protected int leftButton = KeyEvent.TSK_LEFT_ARROW;
	
	protected int rightButton = KeyEvent.TSK_RIGHT_ARROW;
	
	protected int aButton = KeyEvent.TSK_Z;
	
	protected int bButton = KeyEvent.TSK_X;
	
	protected int cButton = KeyEvent.TSK_C;
	
	protected int startButton = KeyEvent.TSK_ENTER;
	
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

	public int getaButton() {
		return aButton;
	}

	public void setaButton(int aButton) {
		this.aButton = aButton;
	}

	public int getbButton() {
		return bButton;
	}

	public void setbButton(int bButton) {
		this.bButton = bButton;
	}

	public int getcButton() {
		return cButton;
	}

	public void setcButton(int cButton) {
		this.cButton = cButton;
	}

	public int getStartButton() {
		return startButton;
	}

	public void setStartButton(int startButton) {
		this.startButton = startButton;
	}
		
}
