package examples.etyllica.basic.input;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class MoveImage extends Application {

	public MoveImage(int w, int h) {
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
	public void draw(Graphics g) {
		hello.draw(g);
	}
	
	@Override
	public void update(long now) {
		
		if(right) {
			hello.offsetX(1);
		}
		if(left) {
			hello.offsetX(-1);
		}
		
		if(down) {
			hello.offsetY(1);
		}
		if(up) {
			hello.offsetY(-1);
		}
	}
	
	private boolean up = false;
	private boolean down = false;
	
	private boolean right = false;
	private boolean left = false;
	
	@Override
	public void updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
			right = true;
		}
		if(event.isKeyUp(KeyEvent.VK_RIGHT_ARROW)) {
			right = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
			left = true;
		}
		if(event.isKeyUp(KeyEvent.VK_LEFT_ARROW)) {
			left = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
			up = true;
		}
		if(event.isKeyUp(KeyEvent.VK_UP_ARROW)) {
			up = false;
		}
		
		if(event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
			down = true;
		}
	
		if(event.isKeyUp(KeyEvent.VK_DOWN_ARROW)) {
			down = false;
		}
	}
}
