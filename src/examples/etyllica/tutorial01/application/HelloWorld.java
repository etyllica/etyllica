package examples.etyllica.tutorial01.application;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class HelloWorld extends Application{

	public HelloWorld(int w, int h) {
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
	public GUIEvent updateKeyboard(KeyboardEvent event) {
			
		// TODO Auto-generated method stub
		return null;
	}
	

}
