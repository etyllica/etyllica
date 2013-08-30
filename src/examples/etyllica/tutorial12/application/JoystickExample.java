package examples.etyllica.tutorial12.application;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;

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
	public void draw(Grafico g) {
		hello.draw(g);
	}
	

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		
		if(event.getPressed(Tecla.JOYSTICK_RIGHT)){
			hello.setOffsetX(10);
		}
		
		else if(event.getPressed(Tecla.JOYSTICK_LEFT)){
			hello.setOffsetX(-10);
		}
		
		if(event.getPressed(Tecla.JOYSTICK_UP)){
			hello.setOffsetY(-10);
		}
		
		else if(event.getPressed(Tecla.JOYSTICK_DOWN)){
			hello.setOffsetY(10);
		}
		
		if(event.getPressed(Tecla.JOYSTICK_BUTTON_1)){
			hello.setOffsetAngle(10);
			
		}
				
		// TODO Auto-generated method stub
		return null;
	}
	

}
