package br.com.etyllica.core.input.joystick;

import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.layer.Layer;

public class TouchJoystick {

	private static final int DEFAULT_RADIUS = 120/2;
	private static final int DEFAULT_JOYSTICK_RADIUS = 50/2;
	
	private Layer area;
	private Layer joystick;
	private boolean active = false;

	private Point2D center;
	private Point2D joyPosition;
	
	private int radius = DEFAULT_RADIUS;
	private int joystickRadius = DEFAULT_JOYSTICK_RADIUS;
	
	private double angle = 0;
	private double sensitivityX = 0;
	private double sensitivityY = 0;
	
	public TouchJoystick(int x, int y) {
		this(x, y, DEFAULT_RADIUS);
	}
	
	public TouchJoystick(int x, int y, int radius) {
		super();
		area = new Layer(x, y, radius*2, radius*2);

		int cx = area.getX()+radius;
		int cy = area.getY()+radius;

		center = new Point2D(cx, cy);
		joyPosition = new Point2D(cx, cy);

		joystick = new Layer(cx-joystickRadius, cy-joystickRadius, joystickRadius*2, joystickRadius*2);
	}
	
	public Layer getArea() {
		return area;
	}

	public Layer getJoystick() {
		return joystick;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void updateMouse(PointerEvent event) {

		int mx = event.getX();
		int my = event.getY();

		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			if(!active) {
				if(joystick.colideCirclePoint(mx, my)) {
					setActive(true);
				}
			} else {
				angle = center.angle(mx, my);
				double radAngle = Math.toRadians(angle);
				sensitivityX = Math.cos(radAngle);
				sensitivityY = Math.sin(radAngle);
				
				updateJoystickPosition(mx, my);
				fixValues();
			}

		} else if(event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
			//Release Joystick
			if(active) {
				setActive(false);
				resetJoystick();
				sensitivityX = 0;
				sensitivityY = 0;
			}
		}		
	}

	private void updateJoystickPosition(int mx, int my) {
		int jx = mx-joystickRadius;
		int jy = my-joystickRadius;

		joyPosition.setLocation(mx, my);
		double dist = joyPosition.distance(center);

		if(dist < radius) {
			joystick.setCoordinates(jx, jy);					
		} else {
			double lx = sensitivityX*radius;
			double ly = sensitivityY*radius;
			
			joystick.setCoordinates((int)(center.getX()+lx-joystickRadius), (int)(center.getY()+ly-joystickRadius));
		}
	}

	private void fixValues() {
		sensitivityY = -sensitivityY;
		if(angle < 0) {
			angle = -angle;
		} else {
			angle = 180+(180-angle);
		}
	}

	private void resetJoystick() {
		joystick.setCoordinates((int)center.getX()-joystickRadius, (int)center.getY()-joystickRadius);
	}

	public double getSensitivityX() {
		return sensitivityX;
	}

	public double getSensitivityY() {
		return sensitivityY;
	}

	public double getAngle() {
		return angle;
	}

	public boolean isActive() {
		return active;
	}

}

