package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;

public class FirstPlayerController extends Controller{
		
	public FirstPlayerController(){
		super();
		
		upButton = KeyEvent.TSK_W;
		
		downButton = KeyEvent.TSK_S;
		
		leftButton = KeyEvent.TSK_A;
		
		rightButton = KeyEvent.TSK_D;
		
		aButton = KeyEvent.TSK_H;
		
		bButton = KeyEvent.TSK_J;
		
		cButton = KeyEvent.TSK_K;
		
		startButton = KeyEvent.TSK_F;
		
	}

}
