package br.com.tide.input.controller;

import br.com.etyllica.core.event.KeyEvent;
import br.com.tide.input.ControllerListener;

public class FirstPlayerController extends Controller {
		
	public FirstPlayerController(ControllerListener listener) {
		super(listener);
		
		upButton = KeyEvent.VK_W;
		
		downButton = KeyEvent.VK_S;
		
		leftButton = KeyEvent.VK_A;
		
		rightButton = KeyEvent.VK_D;
		
		ButtonA = KeyEvent.VK_H;
		
		ButtonB = KeyEvent.VK_J;
		
		ButtonC = KeyEvent.VK_K;
		
		startButton = KeyEvent.VK_F;
		
		selectButton = KeyEvent.VK_G;
		
	}

}
