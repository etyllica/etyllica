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
		
		loading = 10;
		
		hello = new ImageLayer(200,100,"hello.png");
				
		loading = 100;
		
	}

	@Override
	public void draw(Graphic g) {
		hello.draw(g);		
		
		if(right){
			hello.setOffsetX(1);
		}
		if(left){
			hello.setOffsetX(-1);
		}
		
		if(down){
			hello.setOffsetY(1);
		}
		if(up){
			hello.setOffsetY(-1);
		}
	}
	

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean up = false;
	private boolean down = false;
	
	private boolean right = false;
	private boolean left = false;
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)){
			right = true;
		}
		if(event.isKeyUp(KeyEvent.TSK_RIGHT_ARROW)){
			right = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)){
			left = true;
		}
		if(event.isKeyUp(KeyEvent.TSK_LEFT_ARROW)){
			left = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)){
			up = true;
		}
		if(event.isKeyUp(KeyEvent.TSK_UP_ARROW)){
			up = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_DOWN_ARROW)){
			down = true;
		}
		if(event.isKeyUp(KeyEvent.TSK_DOWN_ARROW)){
			down = false;
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	

}
