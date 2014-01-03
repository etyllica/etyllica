package examples.etyllica.tutorial12;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
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
	public void draw(Graphic g) {
		hello.draw(g);
	}
	

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_RIGHT)){
			hello.setOffsetX(10);
		}
		
		else if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_LEFT)){
			hello.setOffsetX(-10);
		}
		
		if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_UP)){
			hello.setOffsetY(-10);
		}
		
		else if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_DOWN)){
			hello.setOffsetY(10);
		}
		
		if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_BUTTON_1)){
			hello.setOffsetAngle(10);
		}
		
		if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_BUTTON_7)){
			hello.setScale(1.5);
		}
		if(event.isKeyDown(KeyEvent.TSK_JOYSTICK_BUTTON_8)){
			hello.setScale(1);
		}
				
		// TODO Auto-generated method stub
		return null;
	}
	

}
