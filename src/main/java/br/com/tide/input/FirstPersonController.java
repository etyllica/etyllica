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
	 * @return mouse offset from center;
	 */
	public PointInt2D updateMouse(GeometricLayer layer, PointerEvent event) {
		int mx = event.getX();
		int my = event.getY();
		
		int invert = invertedVertically ? -1: 1;
		
		double dx = (layer.getW()/2-mx);
		double dy = (layer.getH()/2-my);

		angleY = dx*sensitivity;
		angleX = dy*sensitivity * invert;
		
		//TODO Infinite rotate in Y
		System.out.println("dx: "+dx);
		System.out.println("ly: "+layer.getY());
		
		System.out.println("my: "+my);
		System.out.println("Ax: "+angleX);
			
		updateMouse(layer, dx, dy);
		
		return offset;
	}

	protected void updateMouse(GeometricLayer layer, double dx, double dy) {
		
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		
		boolean needUpdate = false;
		
		if(dx<-360) {
			int offset = 360+(int)dx;
			x = layer.getX()+layer.getW()/2+offset;
			
			needUpdate = true;
		} else if(dx>360) {
			int offset = 360-(int)dx;
			x = layer.getX()+layer.getW()/2+offset;
			
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
