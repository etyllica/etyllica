package examples.etyllica.control;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.core.input.joystick.TouchJoystick;

public class ControllerExample extends Application {

	private TouchJoystick joystick;

	public ControllerExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		joystick = new TouchJoystick(36, 110);
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		drawTouchJoystick(g);

		g.setColor(Color.WHITE);
		g.drawStringShadow("X: "+Double.toString(joystick.getSensitivityX()), 40, 60);
		g.drawStringShadow("Y: "+Double.toString(joystick.getSensitivityY()), 40, 80);
		g.drawStringShadow("A: "+Double.toString(joystick.getAngle()), 40, 100);
	}
	
	public void drawTouchJoystick(Graphics g) {
		g.setAlpha(50);
		g.setColor(Color.BLACK);
		g.fillOval(joystick.getArea());

		if(joystick.isActive()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.YELLOW);
		}

		g.fillOval(joystick.getJoystick());
		g.resetOpacity();
	}

	@Override
	public void updateMouse(PointerEvent event) {
		joystick.updateMouse(event);
	}

}

