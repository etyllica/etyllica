package examples.etyllica.tutorial01;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
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
			
		// TODO Auto-generated method stub
		return null;
	}
	

}
