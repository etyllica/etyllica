package br.com.etyllica.core.input.controller;

import br.com.etyllica.core.event.KeyEvent;

public class EasyController extends Controller{
		
	public EasyController(){
		super();
		
		upButton = KeyEvent.TSK_UP_ARROW;
		
		downButton = KeyEvent.TSK_DOWN_ARROW;
		
		leftButton = KeyEvent.TSK_LEFT_ARROW;
		
		rightButton = KeyEvent.TSK_RIGHT_ARROW;
		
		aButton = KeyEvent.TSK_Z;
		
		bButton = KeyEvent.TSK_X;
		
		cButton = KeyEvent.TSK_C;
		
		startButton = KeyEvent.TSK_ENTER;
		
	}

}
