package br.com.tide.input;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.tide.input.controller.Controller;

/**
 * First Player controller based on: 
 * http://www.gamedev.net/page/resources/_/technical/game-programming/building-a-first-person-shooter-part-14-mouse-inputs-r3274
 */
public class FirstPersonController extends Controller {

	protected int turnLeftButton = KeyEvent.TSK_LEFT_ARROW;
	
	protected int turnRightButton = KeyEvent.TSK_RIGHT_ARROW;
	
	protected int goForwardButton = KeyEvent.TSK_UP_ARROW;
	
	protected int goBackwardButton = KeyEvent.TSK_DOWN_ARROW;
	
	protected int MAX_UP_ANGLE = 80;
	protected int MIN_UP_ANGLE = -80;
	
	protected boolean inverted = false;
	
	protected int sensitivity = 2;
	
	protected double angleX = 0;
	protected double angleY = 0;
	
	/**
	 * Update mouse (aim) logic
	 * @param w - Game window's width
	 * @param h - Game window's height
	 * @param event - The mouse event
	 */
	public void updateMouse(int w, int h, PointerEvent event) {
		int mx = event.getX();
		int my = event.getY();
		
		int invert = inverted ? -1: 1;
		
		int dx = w/2-mx;
		int dy = h/2-my;

		//The best to do is increment values (+=) and move mouse to center
		angleY = dx*sensitivity;
		angleX = dy*sensitivity * invert;
				
		angleX = clampAngle(angleX, 90);				
	}
	
	private double clampAngle(double angle, int limit) {
		
		if(angle<-limit) {
			return -limit;
		} else if(angle>limit) {
			return limit;
		}
		
		return angle; 
	}

	public double getAngleX() {
		return angleX;
	}

	public double getAngleY() {
		return angleY;
	}
	
}
