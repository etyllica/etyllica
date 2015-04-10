package br.com.tide.input;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.layer.GeometricLayer;
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
	
	protected int MAX_UP_ANGLE = -45;
	protected int MIN_UP_ANGLE = 45;
	
	protected boolean invertedVertically = false;
	
	protected int sensitivity = 2;
		
	/**
	 * Vertical Angle
	 */
	protected double angleX = 0;
	
	/**
	 * Horizontal Angle
	 */
	protected double angleY = 0;
	
	private boolean validY = true;
	private int lastValidY = 0;
	
	/**
	 * Update mouse (aim) logic
	 * @param w - Game window's width
	 * @param h - Game window's height
	 * @param event - The mouse event
	 */
	public void updateMouse(GeometricLayer window, PointerEvent event) {
		int mx = event.getX();
		int my = event.getY();
		
		int invert = invertedVertically ? -1: 1;
				
		int dx = window.getW()/2-mx;
		int dy = window.getH()/2-my;
		
		Point point = MouseInfo.getPointerInfo().getLocation();
		
		angleY = dx*sensitivity;
		updateMouseX(window, point, dx);
		
		updateMouseY(window, point, dy);
		angleX = dy*sensitivity * invert;
	}

	protected boolean updateMouseX(GeometricLayer window, Point point, double dx) {
		
		int x = point.x;
		int y = point.y;
		
		boolean needUpdate = false;
		
		if(dx<-360) {
			int offset = 360+(int)dx;
			x = window.getX()+window.getW()/2+offset;
			
			needUpdate = true;
		} else if(dx>360) {
			int offset = 360-(int)dx;
			x = window.getX()+window.getW()/2+offset;
			
			needUpdate = true;
		}
				
		if(needUpdate) {
			moveMouse(x, y);
		}
		
		return needUpdate;
	}
	
	protected boolean updateMouseY(GeometricLayer window, Point point, double dy) {
				
		int x = point.x;
		int y = point.y;
		
		boolean needUpdate = false;
	
		if(dy < -MAX_UP_ANGLE) {
			needUpdate = true;
			dy = -MAX_UP_ANGLE;
			
			if(validY) {
				validY = false;				
			}			
		} else if (dy > MIN_UP_ANGLE) {
			needUpdate = true;
			dy = MIN_UP_ANGLE;
			
			if(validY) {
				validY = false;				
			}			
		} else {
			validY = true;
			lastValidY = y;
		}
		
		if(needUpdate) {
			moveMouse(x, lastValidY);			
		}
		
		return needUpdate;
	}
	
	private void moveMouse(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getAngleX() {
		return angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public boolean isInvertedVertically() {
		return invertedVertically;
	}

	public void setInvertedVertically(boolean invertedVertically) {
		this.invertedVertically = invertedVertically;
	}

	public int getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(int sensitivity) {
		this.sensitivity = sensitivity;
	}	
	
}
