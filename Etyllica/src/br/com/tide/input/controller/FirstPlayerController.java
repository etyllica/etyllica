package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;

public class FirstPlayerController extends Controller {
		
	public FirstPlayerController(ControllerListener listener) {
		super(listener);
		
		upButton = KeyEvent.TSK_W;
		
		downButton = KeyEvent.TSK_S;
		
		leftButton = KeyEvent.TSK_A;
		
		rightButton = KeyEvent.TSK_D;
		
		ButtonA = KeyEvent.TSK_H;
		
		ButtonB = KeyEvent.TSK_J;
		
		ButtonC = KeyEvent.TSK_K;
		
		startButton = KeyEvent.TSK_F;
		
		selectButton = KeyEvent.TSK_G;
		
	}

}
