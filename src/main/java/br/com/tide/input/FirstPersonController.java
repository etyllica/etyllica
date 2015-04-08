package br.com.tide.input;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.linear.PointInt2D;
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
	
	protected boolean invertedVertically = false;
	
	protected int sensitivity = 2;
	
	private PointInt2D offset = new PointInt2D();
	
	/**
	 * Vertical Angle
	 */
	protected double angleX = 0;
	
	/**
	 * Horizontal Angle
	 */
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
		
		int invert = invertedVertically ? -1: 1;
		
		int dx = w/2-mx;
		int dy = h/2-my;

		//The best to do is increment values (+=) and move mouse to center
		angleY += dx*sensitivity;
		angleX += dy*sensitivity * invert;
				
		angleX = clampAngle(angleX, 90);	
	}

	protected void updateMouse(GeometricLayer window, double dx, double dy) {
		
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		
		boolean needUpdate = false;
		
		//Clamp angle Y (Horizontal)
		if(dx<-360) {
			int offset = 360+(int)dx;
			x = window.getX()+window.getW()/2+offset;
			
			needUpdate = true;
		} else if(dx>360) {
			int offset = 360-(int)dx;
			x = window.getX()+window.getW()/2+offset;
			
			needUpdate = true;
		}
		
		//Clamp angle X (Vertical)
		if(dy<-90) {
			y = (int) (window.getY()+mouseYfromAngle(window, -90));
			needUpdate = true;
		} else if(dy>90) {
			y = (int) (window.getY()+mouseYfromAngle(window, 90));
			needUpdate = true;
		}
		
		if(needUpdate) {
			try {
				Robot robot = new Robot();
				robot.mouseMove(x, y);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private double mouseYfromAngle(GeometricLayer window, double angle) {
				
		int invert = invertedVertically ? -1: 1;
		
		double value = ((angle/sensitivity)+window.getH()/2)*invert;
		
		return value;
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
