package examples.etyllica.joystick;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class JoystickExample extends Application{

	public JoystickExample(int w, int h) {
		super(w, h);
	}

	private ImageLayer hello;

	@Override
	public void load() {
		hello = new ImageLayer(200,100,"hello.png");
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		hello.draw(g);
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_RIGHT)) {
			hello.offsetX(10);
		}
		if(event.isKeyDown(KeyEvent.VK_LEFT)) {
			hello.offsetX(-10);
		}
		
		if(event.isKeyDown(KeyEvent.VK_JOYSTICK_RIGHT)) {
			hello.offsetX(10);
		}
		
		else if(event.isKeyDown(KeyEvent.VK_JOYSTICK_LEFT)) {
			hello.offsetX(-10);
		}
		
		if(event.isKeyDown(KeyEvent.VK_JOYSTICK_UP)) {
			hello.offsetY(-10);
		}
		
		else if(event.isKeyDown(KeyEvent.VK_JOYSTICK_DOWN)) {
			hello.offsetY(10);
		}
		
		if(event.isKeyDown(KeyEvent.VK_JOYSTICK_BUTTON_1)) {
			hello.setOffsetAngle(10);
		}
		
		if(event.isKeyDown(KeyEvent.VK_JOYSTICK_BUTTON_7)) {
			hello.setScale(1.5);
		}
		if(event.isKeyDown(KeyEvent.VK_JOYSTICK_BUTTON_8)) {
			hello.setScale(1);
		}
	}
	
}
